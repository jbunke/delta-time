package com.jordanbunke.delta_time.scripting.ast.nodes.statement.declaration;

import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.assignable.IdentifierNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.function.LambdaExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.FuncTypeNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.FuncControlFlow;
import com.jordanbunke.delta_time.scripting.util.ScriptErrorLog;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public final class InitializationNode extends ExplicitDeclarationNode {
    private final ExpressionNode value;

    public InitializationNode(
            final TextPosition position, final boolean mutable,
            final TypeNode type, final IdentifierNode ident,
            final ExpressionNode value
    ) {
        super(position, mutable, type, ident);

        this.value = value;
    }

    @Override
    public void semanticErrorCheck(final SymbolTable symbolTable) {
        value.semanticErrorCheck(symbolTable);
        super.semanticErrorCheck(symbolTable);

        final TypeNode declarationType = getType();

        if (declarationType instanceof FuncTypeNode fType &&
                value instanceof LambdaExpressionNode l) {
            final TypeNode returnType = fType.getReturnType();
            final TypeNode[] paramTypes = fType.getParamTypes();

            l.f.populateTypes(paramTypes, returnType);
        }

        final TypeNode initType = value.getType(symbolTable);

        if (!declarationType.equals(initType))
            ScriptErrorLog.fireError(
                    ScriptErrorLog.Message.VAR_TYPE_MISMATCH,
                    value.getPosition(), getType().toString(),
                    initType.toString());
    }

    @Override
    public FuncControlFlow execute(final SymbolTable symbolTable) {
        super.execute(symbolTable);

        final Object v = value.evaluate(symbolTable);

        symbolTable.update(getIdent(), v);

        return FuncControlFlow.cont();
    }

    @Override
    public String toString() {
        return super.toString() + " = " + value + ";";
    }
}
