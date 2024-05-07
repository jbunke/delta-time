package com.jordanbunke.delta_time.scripting.ast.nodes.expression.operation;

import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.BaseTypeNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.ScriptErrorLog;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public final class TernaryOperationNode extends ExpressionNode {
    private final ExpressionNode condition, a, b;

    public TernaryOperationNode(
            final TextPosition position,
            final ExpressionNode condition,
            final ExpressionNode a,
            final ExpressionNode b
    ) {
        super(position);

        this.condition = condition;
        this.a = a;
        this.b = b;
    }

    @Override
    public void semanticErrorCheck(final SymbolTable symbolTable) {
        condition.semanticErrorCheck(symbolTable);
        a.semanticErrorCheck(symbolTable);
        b.semanticErrorCheck(symbolTable);

        final TypeNode
                cType = condition.getType(symbolTable),
                aType = a.getType(symbolTable),
                bType = b.getType(symbolTable);
        final BaseTypeNode boolType = TypeNode.getBool();

        if (!cType.equals(boolType))
            ScriptErrorLog.fireError(
                    ScriptErrorLog.Message.ARG_NOT_TYPE,
                    getPosition(), "Ternary condition",
                    boolType.toString(), cType.toString());
        if (!aType.equals(bType))
            ScriptErrorLog.fireError(
                    ScriptErrorLog.Message.DIFFERENT_TYPES, getPosition(),
                    "Ternary branches", "true case", "false case",
                    aType.toString(), bType.toString());
    }

    @Override
    public Object evaluate(final SymbolTable symbolTable) {
        return (Boolean) condition.evaluate(symbolTable)
                ? a.evaluate(symbolTable)
                : b.evaluate(symbolTable);
    }

    @Override
    public TypeNode getType(final SymbolTable symbolTable) {
        return a.getType(symbolTable);
    }

    @Override
    public String toString() {
        return condition + " ? " + a + " : " + b;
    }
}
