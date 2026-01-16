package com.jordanbunke.delta_time.scripting.ast.nodes.statement.std_lib;

import com.jordanbunke.delta_time.scripting.ast.collection.ScriptCollection;
import com.jordanbunke.delta_time.scripting.ast.collection.ScriptList;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.statement.StatementNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.CollectionTypeNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.FuncControlFlow;
import com.jordanbunke.delta_time.scripting.util.ScriptVisitor;
import com.jordanbunke.delta_time.scripting.util.TextPosition;
import com.jordanbunke.delta_time.scripting.util.TypeUtils;

import java.util.Optional;

import static com.jordanbunke.delta_time.scripting.util.ScriptErrorLog.*;

// TODO - refactor: should extend MemberFuncExecNode
public final class AddNode extends StatementNode {
    private final ExpressionNode collection, toAdd, index;

    public AddNode(
            final TextPosition position,
            final ExpressionNode collection,
            final ExpressionNode toAdd,
            final ExpressionNode index
    ) {
        super(position);

        this.collection = collection;
        this.toAdd = toAdd;
        this.index = index;
    }

    @Override
    public void semanticErrorCheck(final SymbolTable symbolTable) {
        final boolean hasIndex = index != null;

        collection.semanticErrorCheck(symbolTable);
        toAdd.semanticErrorCheck(symbolTable);

        if (hasIndex)
            index.semanticErrorCheck(symbolTable);

        final TypeNode colType = collection.getType(symbolTable),
                elemType = (colType instanceof CollectionTypeNode ct)
                        ? ct.getElementType() : null,
                addType = toAdd.getType(symbolTable),
                iType = hasIndex ? index.getType(symbolTable) : null;
        final CollectionTypeNode.Type typeOfCol =
                (colType instanceof CollectionTypeNode ct)
                        ? ct.getType() : null;

        if (elemType == null || typeOfCol == null)
            semanticError(collection.getPosition(),
                    "add() receiver expression is of an invalid type: " +
                            expectedButGot(TypeUtils.options(TypeNode.list(), TypeNode.set()), colType));
        else if (!elemType.equals(addType))
            semanticError(toAdd.getPosition(),
                    typeMismatch("Type of element to be added",
                            "the collection's element type", elemType, addType));
        else if (typeOfCol == CollectionTypeNode.Type.ARRAY)
            semanticError(collection.getPosition(),
                    "add() receiver expression is of type \"" + colType +
                            "\"; cannot add an element to an array");
        if (hasIndex && !iType.equals(TypeNode.getInt()))
            semanticError(index.getPosition(),
                    "add() index argument expression is of an invalid type: " +
                            expectedButGot(TypeNode.getInt(), iType));
    }

    @Override
    public FuncControlFlow execute(final SymbolTable symbolTable) {
        final ScriptCollection c =
                (ScriptCollection) collection.evaluate(symbolTable);
        final Object element = toAdd.evaluate(symbolTable);
        final Optional<Integer> i = index != null
                ? Optional.of((Integer) index.evaluate(symbolTable))
                : Optional.empty();

        if (i.isPresent() && c instanceof ScriptList l) {
            final int index = i.get();

            try {
                l.add(index, element);
            } catch (IllegalArgumentException e) {
                runtimeError(this.index.getPosition(),
                        "Index out of bounds; attempted to add an element at index " +
                                i + " of a " + l.size() + "-element collection");
            }
        } else
            c.add(element);

        return FuncControlFlow.cont();
    }

    @Override
    public String toString() {
        return collection + "." + ScriptVisitor.ADD + "(" + toAdd +
                (index != null ? ", " + index : "") + ");";
    }
}
