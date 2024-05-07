package com.jordanbunke.delta_time.scripting.ast.nodes.statement.control_flow;

import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.statement.StatementNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.statement.assignment.AssignmentNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.statement.declaration.InitializationNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.BaseTypeNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.FuncControlFlow;
import com.jordanbunke.delta_time.scripting.util.ScriptErrorLog;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public final class ForLoopNode extends StatementNode {
    private final InitializationNode initialization;
    private final ExpressionNode loopCondition;
    private final AssignmentNode incrementation;
    private final StatementNode loopBody;

    public ForLoopNode(
            final TextPosition position,
            final InitializationNode initialization,
            final ExpressionNode loopCondition,
            final AssignmentNode incrementation,
            final StatementNode loopBody
    ) {
        super(position);

        this.initialization = initialization;
        this.loopCondition = loopCondition;
        this.incrementation = incrementation;
        this.loopBody = loopBody;
    }

    @Override
    public void semanticErrorCheck(final SymbolTable symbolTable) {
        final SymbolTable innerTable = new SymbolTable(this, symbolTable);

        initialization.semanticErrorCheck(innerTable);
        loopCondition.semanticErrorCheck(innerTable);
        incrementation.semanticErrorCheck(innerTable);
        loopBody.semanticErrorCheck(innerTable);

        final BaseTypeNode boolType = TypeNode.getBool();
        final TypeNode condType = loopCondition.getType(symbolTable);

        if (!condType.equals(boolType))
            ScriptErrorLog.fireError(
                    ScriptErrorLog.Message.ARG_NOT_TYPE,
                    loopCondition.getPosition(), "Condition",
                    boolType.toString(), condType.toString());
    }

    private boolean evaluateCondition(final SymbolTable symbolTable) {
        return (boolean) loopCondition.evaluate(symbolTable);
    }

    @Override
    public FuncControlFlow execute(final SymbolTable symbolTable) {
        final SymbolTable innerTable = symbolTable.getChild(this);

        initialization.execute(innerTable);

        FuncControlFlow status = FuncControlFlow.cont();

        while (evaluateCondition(innerTable)) {
            status = loopBody.execute(innerTable);

            if (!status.cont)
                return status;

            incrementation.execute(innerTable);
        }

        return status;
    }

    @Override
    public String toString() {
        return "for (" + initialization + " " + loopCondition + "; " +
                incrementation + ")\n" + loopBody;
    }
}
