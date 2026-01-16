package com.jordanbunke.delta_time.scripting.ast.nodes.expression.collection_init;

import com.jordanbunke.delta_time.scripting.ast.collection.ScriptArray;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

import static com.jordanbunke.delta_time.scripting.util.ScriptErrorLog.*;

public final class NewArrayNode extends ExpressionNode {
    private final TypeNode elementType;
    private final ExpressionNode length;

    public NewArrayNode(
            final TextPosition position,
            final TypeNode elementType,
            final ExpressionNode length
    ) {
        super(position);

        this.elementType = elementType;
        this.length = length;
    }

    @Override
    public void semanticErrorCheck(final SymbolTable symbolTable) {
        length.semanticErrorCheck(symbolTable);
        elementType.semanticErrorCheck(symbolTable);

        final TypeNode lengthType = length.getType(symbolTable), intType = TypeNode.getInt();

        if (!lengthType.equals(intType))
            semanticError(length.getPosition(),
                    "Length expression of array creation by size is an invalid type: " +
                            expectedButGot(intType, lengthType));
    }

    @Override
    public ScriptArray evaluate(final SymbolTable symbolTable) {
        final int l = (int) length.evaluate(symbolTable);

        if (l < 0)
            runtimeError(length.getPosition(), "Attempted to create an array with " +
                    l + " elements; array length must be non-negative");

        return new ScriptArray(l);
    }

    @Override
    public TypeNode getType(final SymbolTable symbolTable) {
        return TypeNode.arrayOf(elementType);
    }

    @Override
    public String toString() {
        return elementType + "[" + length + "]";
    }
}
