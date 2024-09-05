package com.jordanbunke.delta_time.scripting.ast.nodes.function;

import com.jordanbunke.delta_time.scripting.ast.nodes.statement.StatementNode;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public sealed abstract class ChildFuncNode extends FuncNode
        permits HelperFuncNode, LambdaFuncNode {
    ChildFuncNode(
            final TextPosition position,
            final FuncSignatureNode signature, final StatementNode body
    ) {
        super(position, signature, body);
    }
}
