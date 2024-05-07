package com.jordanbunke.delta_time.scripting.ast.nodes.expression.assignable;

import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public final class ArrayAssignableNode extends CollectionAssignableNode {
    public ArrayAssignableNode(
            final TextPosition position,
            final String name,
            final ExpressionNode index
    ) {
        super(position, name, index);
    }

    @Override
    public String toString() {
        return getName() + "[" + getIndex() + "]";
    }
}
