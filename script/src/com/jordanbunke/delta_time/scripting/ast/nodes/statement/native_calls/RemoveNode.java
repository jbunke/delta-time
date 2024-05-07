package com.jordanbunke.delta_time.scripting.ast.nodes.statement.native_calls;

import com.jordanbunke.delta_time.scripting.ast.collection.ScriptCollection;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.statement.StatementNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.CollectionTypeNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.FuncControlFlow;
import com.jordanbunke.delta_time.scripting.util.ScriptErrorLog;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public final class RemoveNode extends StatementNode {
    private final ExpressionNode collection, index;

    public RemoveNode(
            final TextPosition position,
            final ExpressionNode collection,
            final ExpressionNode index
    ) {
        super(position);

        this.collection = collection;
        this.index = index;
    }

    @Override
    public void semanticErrorCheck(final SymbolTable symbolTable) {
        collection.semanticErrorCheck(symbolTable);
        index.semanticErrorCheck(symbolTable);

        final TypeNode
                colType = collection.getType(symbolTable),
                elemType = (colType instanceof CollectionTypeNode ct)
                        ? ct.getElementType() : null,
                iType = index.getType(symbolTable);
        final CollectionTypeNode.Type typeOfCol =
                (colType instanceof CollectionTypeNode ct)
                        ? ct.getType() : null;

        if (elemType == null || typeOfCol == null)
            ScriptErrorLog.fireError(
                    ScriptErrorLog.Message.EXPECTED_FOR_CALL,
                    collection.getPosition(),
                    "remove()", "list - <>", colType.toString());
        else if (typeOfCol != CollectionTypeNode.Type.LIST)
            ScriptErrorLog.fireError(
                    ScriptErrorLog.Message.REMOVE_FROM_SET_OR_ARRAY,
                    collection.getPosition());
        if (!iType.equals(TypeNode.getInt()))
            ScriptErrorLog.fireError(
                    ScriptErrorLog.Message.INDEX_NOT_INT,
                    index.getPosition(), iType.toString());
    }

    @Override
    public FuncControlFlow execute(final SymbolTable symbolTable) {
        final ScriptCollection c =
                (ScriptCollection) collection.evaluate(symbolTable);
        final Integer i = (Integer) index.evaluate(symbolTable);

        try {
            c.remove(i);
        } catch (IllegalArgumentException e) {
            ScriptErrorLog.fireError(
                    ScriptErrorLog.Message.INDEX_OUT_OF_BOUNDS,
                    this.index.getPosition(), String.valueOf(i),
                    String.valueOf(c.size()), String.valueOf(false));
        }

        return FuncControlFlow.cont();
    }

    @Override
    public String toString() {
        return collection + ".remove(" + index + ");";
    }
}
