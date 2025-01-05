package com.jordanbunke.delta_time.scripting.ast.nodes.statement.native_calls;

import com.jordanbunke.delta_time.scripting.ast.collection.ScriptCollection;
import com.jordanbunke.delta_time.scripting.ast.collection.ScriptList;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.statement.StatementNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.CollectionTypeNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.FuncControlFlow;
import com.jordanbunke.delta_time.scripting.util.ScriptErrorLog;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public final class RemoveNode extends StatementNode {
    private final ExpressionNode collection, arg;

    public RemoveNode(
            final TextPosition position,
            final ExpressionNode collection,
            final ExpressionNode arg
    ) {
        super(position);

        this.collection = collection;
        this.arg = arg;
    }

    @Override
    public void semanticErrorCheck(final SymbolTable symbolTable) {
        collection.semanticErrorCheck(symbolTable);
        arg.semanticErrorCheck(symbolTable);

        final TypeNode
                colType = collection.getType(symbolTable),
                elemType = (colType instanceof CollectionTypeNode ct)
                        ? ct.getElementType() : null;
        final CollectionTypeNode.Type typeOfCol =
                (colType instanceof CollectionTypeNode ct)
                        ? ct.getType() : null;

        if (elemType == null || typeOfCol == null)
            ScriptErrorLog.fireError(
                    ScriptErrorLog.Message.EXPECTED_FOR_CALL,
                    collection.getPosition(),
                    "remove()", "list - <>, set - {}", colType.toString());
        else if (typeOfCol == CollectionTypeNode.Type.ARRAY)
            ScriptErrorLog.fireError(
                    ScriptErrorLog.Message.REMOVE_FROM_ARRAY,
                    collection.getPosition());
    }

    @Override
    public FuncControlFlow execute(final SymbolTable symbolTable) {
        final ScriptCollection c =
                (ScriptCollection) collection.evaluate(symbolTable);
        final Object evalArg = arg.evaluate(symbolTable);

        if (evalArg instanceof Integer i && c instanceof ScriptList l) {
            try {
                l.removeAt(i);
            } catch (IllegalArgumentException e) {
                ScriptErrorLog.fireError(
                        ScriptErrorLog.Message.INDEX_OUT_OF_BOUNDS,
                        this.arg.getPosition(), String.valueOf(i),
                        String.valueOf(l.size()), String.valueOf(false));
            }
        } else {
            c.remove(evalArg);
        }

        return FuncControlFlow.cont();
    }

    @Override
    public String toString() {
        return collection + ".remove(" + arg + ");";
    }
}
