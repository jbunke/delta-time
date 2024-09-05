package com.jordanbunke.delta_time.scripting.ast.nodes.function;

import com.jordanbunke.delta_time.scripting.ast.nodes.statement.StatementNode;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public final class LambdaFuncNode extends ChildFuncNode {
    public LambdaFuncNode(
            final TextPosition position,
            final FuncSignatureNode signature,
            final StatementNode body
    ) {
        super(position, signature, body);
    }

    @Override
    public String toString() {
        return "/* λ @ " + getPosition() + " */ " + super.toString();
    }
}
