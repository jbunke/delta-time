package com.jordanbunke.delta_time.scripting.ast.nodes.statement.control_flow;

import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.function.FuncNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.statement.StatementNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.FuncControlFlow;
import com.jordanbunke.delta_time.scripting.util.ScriptErrorLog;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public final class ReturnStatementNode extends StatementNode {
    private final ExpressionNode expression;

    private ReturnStatementNode(
            final TextPosition position,
            final ExpressionNode expression
    ) {
        super(position);

        this.expression = expression;
    }

    public static ReturnStatementNode forVoid(
            final TextPosition position
    ) {
        return new ReturnStatementNode(position, null);
    }

    public static ReturnStatementNode forTyped(
            final TextPosition position, final ExpressionNode expression
    ) {
        return new ReturnStatementNode(position, expression);
    }

    @Override
    public void semanticErrorCheck(final SymbolTable symbolTable) {
        final FuncNode func = symbolTable.getFunc();

        if (func != null) {
            final TypeNode
                    returnType = func.getReturnType(),
                    exprType = expression != null
                            ? expression.getType(symbolTable) : null;
            final TextPosition pos = expression != null
                    ? expression.getPosition() : getPosition();

            final boolean
                    bothNull = exprType == null && returnType == null,
                    bothNonNull = exprType != null && returnType != null,
                    bothEqual = bothNonNull && exprType.equals(returnType);
            final boolean typeEquality = bothNull || bothEqual;

            if (!typeEquality)
                ScriptErrorLog.fireError(
                        ScriptErrorLog.Message.RETURN_TYPE_MISMATCH,
                        pos, String.valueOf(returnType),
                        String.valueOf(exprType));
        }
    }

    @Override
    public FuncControlFlow execute(final SymbolTable symbolTable) {
        return expression == null
                ? FuncControlFlow.returnVoid()
                : FuncControlFlow.returnVal(expression.evaluate(symbolTable));
    }

    @Override
    public String toString() {
        if (expression == null)
            return "return;";

        return "return " + expression + ";";
    }
}
