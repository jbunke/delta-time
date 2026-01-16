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

import static com.jordanbunke.delta_time.scripting.util.ScriptErrorLog.*;

// TODO - refactor: should extend MemberFuncExecNode
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

        final TypeNode colType = collection.getType(symbolTable),
                elemType = (colType instanceof CollectionTypeNode ct)
                        ? ct.getElementType() : null;
        final CollectionTypeNode.Type typeOfCol =
                (colType instanceof CollectionTypeNode ct)
                        ? ct.getType() : null;

        if (elemType == null || typeOfCol == null)
            semanticError(collection.getPosition(),
                    "remove() receiver expression is of an invalid type: " +
                            expectedButGot(TypeUtils.options(TypeNode.list(), TypeNode.set()), colType));
        else if (typeOfCol == CollectionTypeNode.Type.ARRAY)
            semanticError(collection.getPosition(),
                    "remove() receiver expression is of type \"" + colType +
                            "\"; cannot remove an element from an array");
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
                runtimeError(arg.getPosition(),
                        "Index out of bounds; attempted to remove the element at index " +
                                i + " of a " + l.size() + "-element collection");
            }
        } else {
            c.remove(evalArg);
        }

        return FuncControlFlow.cont();
    }

    @Override
    public String toString() {
        return collection + "." + ScriptVisitor.REMOVE + "(" + arg + ");";
    }
}
