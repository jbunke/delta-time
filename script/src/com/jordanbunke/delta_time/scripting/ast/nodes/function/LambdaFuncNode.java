package com.jordanbunke.delta_time.scripting.ast.nodes.function;

import com.jordanbunke.delta_time.scripting.ast.nodes.statement.StatementNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.statement.declaration.DeclarationNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.statement.declaration.ImplicitDeclarationNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.util.ScriptErrorLog;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public final class LambdaFuncNode extends ChildFuncNode {
    public LambdaFuncNode(
            final TextPosition position,
            final FuncSignatureNode signature,
            final StatementNode body
    ) {
        super(position, signature, body);
    }

    public void populateTypes(
            final TypeNode[] paramTypes, final TypeNode returnType
    ) {
        final DeclarationNode[] params = signature.parameters.params;

        if (params.length != paramTypes.length) {
            ScriptErrorLog.fireError(ScriptErrorLog.Message.CUSTOM_CT,
                    getPosition(), "Lambda accepts " + params.length +
                            " arguments instead of the expected " +
                            paramTypes.length);
            return;
        }

        if (signature.mutableReturn)
            signature.setReturnType(returnType);

        final int l = params.length;

        for (int i = 0; i < l; i++) {
            if (params[i] instanceof ImplicitDeclarationNode ip)
                ip.setType(paramTypes[i]);
        }
    }

    @Override
    public String toString() {
        return "/* λ @ " + getPosition() + " */ " + super.toString();
    }
}
