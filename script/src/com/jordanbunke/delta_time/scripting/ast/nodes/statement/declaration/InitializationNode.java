package com.jordanbunke.delta_time.scripting.ast.nodes.statement.declaration;

import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.assignable.IdentifierNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.FuncControlFlow;
import com.jordanbunke.delta_time.scripting.util.FuncHelper;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

import static com.jordanbunke.delta_time.scripting.util.ScriptErrorLog.*;

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
        FuncHelper.deepLambdaTypeDefinitions(declarationType, value);
        final TypeNode initType = value.getType(symbolTable);

        if (!declarationType.equals(initType))
            semanticError(value.getPosition(),
                    typeMismatch("Initialization expression type",
                            "variable's declared type",
                            declarationType, initType));
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
