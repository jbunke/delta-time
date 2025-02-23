package com.jordanbunke.delta_time.scripting.ast.nodes.expression.std_lib;

import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.util.Arguments;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public abstract class PropertyNode extends MemberFuncCallNode {
    public PropertyNode(
            final TextPosition position, final ExpressionNode receiver,
            final TypeNode expectedReceiver, final TypeNode returnType
    ) {
        super(position, receiver, expectedReceiver,
                returnType, Arguments.argsOf());
    }

    @Override
    public String toString() {
        return receiver + funcName();
    }
}
