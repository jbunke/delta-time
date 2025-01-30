package com.jordanbunke.delta_time.scripting.ast.nodes.statement.control_flow;

import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.function.LambdaExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.statement.StatementNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.FuncTypeNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.DebugUtils;
import com.jordanbunke.delta_time.scripting.util.FuncControlFlow;
import com.jordanbunke.delta_time.scripting.util.ScriptErrorLog;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public final class WhenStatementNode extends StatementNode {
    private final ExpressionNode control;
    private final CaseNode[] cases;

    public WhenStatementNode(
            final TextPosition position,
            final ExpressionNode control, final CaseNode[] cases
    ) {
        super(position);

        this.control = control;
        this.cases = cases;
    }

    @Override
    public void semanticErrorCheck(final SymbolTable symbolTable) {
        final SymbolTable innerTable = new SymbolTable(this, symbolTable);

        control.semanticErrorCheck(innerTable);
        innerTable.defineScopeVar(control);

        final TypeNode cType = control.getType(innerTable),
                boolType = TypeNode.getBool();

        for (CaseNode c : cases) {
            // moved down - c.semanticErrorCheck(innerTable);

            if (c instanceof PassesCaseNode p) {
                if (p.predicate instanceof LambdaExpressionNode l) {
                    l.f.setScope(innerTable);
                    l.f.setTypes(new TypeNode[] { cType }, boolType);
                }

                final TypeNode pType = p.predicate.getType(innerTable);

                if (!(pType instanceof FuncTypeNode pFuncType)) {
                    ScriptErrorLog.fireError(ScriptErrorLog.Message.NOT_HOF,
                            p.predicate.getPosition(), p.predicate.toString(),
                            pType.toString());
                } else {
                    final TypeNode[] params = pFuncType.getParamTypes();
                    final TypeNode returnType = pFuncType.getReturnType();

                    if (params.length != 1)
                        ScriptErrorLog.fireError(
                                ScriptErrorLog.Message.CUSTOM_CT,
                                p.predicate.getPosition(),
                                "when statement case predicate should accept" +
                                        " 1 argument; accepts " + params.length
                        );
                    else if (!cType.equals(params[0]))
                        ScriptErrorLog.fireError(
                                ScriptErrorLog.Message.TYPE_MISMATCH,
                                p.predicate.getPosition(),
                                "when statement case predicate parameter type",
                                cType.toString(), params[0].toString());
                    else if (!boolType.equals(returnType))
                        ScriptErrorLog.fireError(
                                ScriptErrorLog.Message.TYPE_MISMATCH,
                                p.predicate.getPosition(),
                                "when statement case predicate return type",
                                boolType.toString(), returnType.toString());
                }
            } else if (c instanceof MatchesCaseNode m) {
                final TypeNode condType = m.condition.getType(innerTable);

                if (!boolType.equals(condType))
                    ScriptErrorLog.fireError(
                            ScriptErrorLog.Message.TYPE_MISMATCH,
                            m.condition.getPosition(),
                            "when statement \"matches\" case condition type",
                            boolType.toString(), condType.toString());
            } else if (c instanceof IsCaseNode is) {
                final TypeNode isType = is.matcher.getType(innerTable);

                if (!cType.equals(isType))
                    ScriptErrorLog.fireError(
                            ScriptErrorLog.Message.TYPE_MISMATCH,
                            is.matcher.getPosition(),
                            "when statement case",
                            cType.toString(), isType.toString());
            }

            c.semanticErrorCheck(innerTable);
        }
    }

    @Override
    public FuncControlFlow execute(final SymbolTable symbolTable) {
        final SymbolTable innerTable = symbolTable.getChild(this);
        innerTable.evaluateScopeVar(control);

        for (CaseNode c : cases)
            if (c.test(innerTable))
                return c.execute(innerTable);

        return FuncControlFlow.cont();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        sb.append("when (").append(control).append(") {\n");

        for (CaseNode c : cases)
            sb.append(DebugUtils.formatMultiLineNode("\t", c.toString()));

        sb.append("}");

        return sb.toString();
    }
}
