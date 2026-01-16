package com.jordanbunke.delta_time.scripting.ast.nodes.function;

import com.jordanbunke.delta_time.scripting.ast.nodes.ASTNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.statement.declaration.DeclarationNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.Variable;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

import java.util.Arrays;

import static com.jordanbunke.delta_time.scripting.util.ScriptErrorLog.*;

public final class ParametersNode extends ASTNode {
    final DeclarationNode[] params;

    public ParametersNode(
            final TextPosition position, final DeclarationNode[] params
    ) {
        super(position);

        this.params = params;
    }

    public void populateArgs(
            final SymbolTable symbolTable,
            final Object... args
    ) {
        if (params.length != args.length) {
            runtimeError(getPosition(), unexpectedNumberOfArgs(params.length, args.length));
            return;
        }

        for (int i = 0; i < params.length; i++) {
            final DeclarationNode param = params[i];
            final Object arg = args[i];

            final TypeNode type = param.getType();

            if (type.complies(arg))
                symbolTable.update(param.getIdent(), arg);
            else
                runtimeError(param.getPosition(),
                        "Argument does not match parameter type: " +
                                expected(type));

            symbolTable.put(param.getIdent(),
                    new Variable(param.isMutable(), param.getType(), arg));
        }
    }

    public TypeNode[] getTypes() {
        return Arrays.stream(params)
                .map(DeclarationNode::getType)
                .toArray(TypeNode[]::new);
    }

    public DeclarationNode[] getParams() {
        return params;
    }

    @Override
    public void semanticErrorCheck(final SymbolTable symbolTable) {
        for (DeclarationNode param : params)
            param.semanticErrorCheck(symbolTable);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        for (int i = 0; i < params.length; i++) {
            sb.append(params[i]);

            if (i + 1 < params.length)
                sb.append(", ");
        }

        return sb.toString();
    }
}
