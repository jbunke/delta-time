package com.jordanbunke.delta_time.scripting.ast.nodes.statement.assignment;

import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.assignable.AssignableNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.FuncControlFlow;
import com.jordanbunke.delta_time.scripting.util.FuncHelper;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

import static com.jordanbunke.delta_time.scripting.util.ScriptErrorLog.*;

public final class StandardAssignmentNode extends AssignmentNode {
    private final ExpressionNode expression;

    public StandardAssignmentNode(
            final TextPosition position,
            final AssignableNode assignable,
            final ExpressionNode expression
    ) {
        super(position, assignable);

        this.expression = expression;
    }

    @Override
    public void semanticErrorCheck(final SymbolTable symbolTable) {
        expression.semanticErrorCheck(symbolTable);
        super.semanticErrorCheck(symbolTable);

        final TypeNode assignableType = getAssignable().getType(symbolTable);
        FuncHelper.deepLambdaTypeDefinitions(assignableType, expression);
        final TypeNode exprType = expression.getType(symbolTable);

        if (!assignableType.equals(exprType))
            semanticError(expression.getPosition(),
                    typeMismatch("Assignment expression type",
                            "the type of the left-hand side assignable",
                            assignableType, exprType));
    }

    @Override
    public FuncControlFlow execute(final SymbolTable symbolTable) {
        getAssignable().update(symbolTable, expression.evaluate(symbolTable));

        return FuncControlFlow.cont();
    }

    @Override
    public String toString() {
        return getAssignable() + " = " + expression + ";";
    }
}
