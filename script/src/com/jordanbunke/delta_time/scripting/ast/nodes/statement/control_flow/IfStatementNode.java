package com.jordanbunke.delta_time.scripting.ast.nodes.statement.control_flow;

import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.statement.BodyStatementNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.statement.StatementNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.BaseTypeNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.FuncControlFlow;
import com.jordanbunke.delta_time.scripting.util.ScriptErrorLog;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

import java.util.Arrays;

public final class IfStatementNode extends StatementNode {
    private final ExpressionNode[] conditions;
    private final StatementNode[] bodies;
    private final StatementNode elseBody;

    public IfStatementNode(
            final TextPosition position,
            final ExpressionNode[] conditions, final StatementNode[] bodies,
            final StatementNode elseBody
    ) {
        super(position);

        this.conditions = conditions;
        this.bodies = bodies;
        this.elseBody = elseBody;
    }

    @Override
    public void semanticErrorCheck(final SymbolTable symbolTable) {
        final BaseTypeNode boolType = TypeNode.getBool();

        for (ExpressionNode condition : conditions) {
            condition.semanticErrorCheck(symbolTable);

            final TypeNode condType = condition.getType(symbolTable);

            if (!condType.equals(boolType))
                ScriptErrorLog.fireError(
                        ScriptErrorLog.Message.ARG_NOT_TYPE,
                        condition.getPosition(), "Condition",
                        boolType.toString(), condType.toString());
        }

        for (StatementNode body : bodies)
            body.semanticErrorCheck(symbolTable);
        if (elseBody != null)
            elseBody.semanticErrorCheck(symbolTable);
    }

    @Override
    public FuncControlFlow execute(final SymbolTable symbolTable) {
        final int l = conditions.length;

        for (int i = 0; i < l; i++)
            if ((boolean) conditions[i].evaluate(symbolTable))
                return bodies[i].execute(symbolTable);

        return elseBody != null
                ? elseBody.execute(symbolTable)
                : FuncControlFlow.cont();
    }

    @Override
    public String toString() {
        final int l = conditions.length;
        final String[] branches = new String[l];

        for (int i = 0; i < l; i++)
            branches[i] = "if (" + conditions[i] + ")\n" +
                    (bodies[i] instanceof BodyStatementNode ? "" : "\t") +
                    bodies[i];

        return Arrays.stream(branches)
                .reduce((a, b) -> a + "\nelse " + b).orElse(branches[0]) +
                (elseBody != null ? "\nelse\n" + elseBody : "");
    }
}
