package com.jordanbunke.delta_time.scripting.ast.nodes.expression.std_lib.global.min_max;

import com.jordanbunke.delta_time.scripting.ast.collection.ScriptCollection;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.CollectionTypeNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.ScriptVisitor;
import com.jordanbunke.delta_time.scripting.util.TextPosition;
import com.jordanbunke.delta_time.scripting.util.TypeUtils;

import static com.jordanbunke.delta_time.scripting.util.ScriptErrorLog.*;

import java.util.stream.Stream;

// TODO - refactor: should extend DefFuncCallNode
public final class MinMaxCollectionNode extends ExpressionNode {
    private final boolean isMax;
    private final ExpressionNode col;

    public MinMaxCollectionNode(
            final TextPosition position,
            final boolean isMax, final ExpressionNode col
    ) {
        super(position);

        this.isMax = isMax;
        this.col = col;
    }

    @Override
    public void semanticErrorCheck(final SymbolTable symbolTable) {
        col.semanticErrorCheck(symbolTable);

        final TypeNode colType = col.getType(symbolTable);

        if (!(colType instanceof CollectionTypeNode c))
            semanticError(col.getPosition(),
                    "Argument is not a collection type: " +
                            expectedButGot(TypeUtils.options(
                                    TypeNode.array(), TypeNode.list(),
                                            TypeNode.set()), colType));
        else if (!c.getElementType().isNum())
            semanticError(col.getPosition(),
                    "Collection elements are of a non-numeric type: " +
                            expectedNumberButGot(c.getElementType()));
    }

    @Override
    public Number evaluate(final SymbolTable symbolTable) {
        final ScriptCollection c =
                (ScriptCollection) col.evaluate(symbolTable);
        final CollectionTypeNode colType =
                (CollectionTypeNode) col.getType(symbolTable);
        final TypeNode elemType = colType.getElementType();

        if (c.size() == 0) {
            runtimeError(col.getPosition(),
                    "Attempted to reduce an empty collection");

            return null;
        }

        final Stream<Object> elements = c.stream();

        if (elemType.equals(TypeNode.getInt()))
            return elements.map(e -> (Integer) e)
                    .reduce(intID(), this::compute);
        else
            return elements.map(e -> (Double) e)
                    .reduce(doubleID(), this::compute);
    }

    @Override
    public TypeNode getType(final SymbolTable symbolTable) {
        final TypeNode colType = col.getType(symbolTable);

        if (colType instanceof CollectionTypeNode c)
            return c.getElementType();

        return TypeNode.wildcard();
    }

    @Override
    public String toString() {
        return (isMax ? ScriptVisitor.MAX : ScriptVisitor.MIN) + "(" + col + ")";
    }

    private int intID() {
        return isMax ? Integer.MIN_VALUE : Integer.MAX_VALUE;
    }

    private double doubleID() {
        return isMax ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
    }

    private int compute(final int a, final int b) {
        return isMax ? Math.max(a, b) : Math.min(a, b);
    }

    private double compute(final double a, final double b) {
        return isMax ? Math.max(a, b) : Math.min(a, b);
    }
}
