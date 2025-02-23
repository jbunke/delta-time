package com.jordanbunke.delta_time.scripting.ast.nodes.expression.std_lib.global.min_max;

import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public final class MinMaxTwoArgNode extends NumBinOpNode {
    private final boolean isMax;

    public MinMaxTwoArgNode(
            final TextPosition position, final boolean isMax,
            final ExpressionNode a, final ExpressionNode b
    ) {
        super(position, a, b,
                isMax ? Math::max : Math::min,
                isMax ? Math::max : Math::min);

        this.isMax = isMax;
    }

    protected String funcName() {
        return isMax ? "max" : "min";
    }
}
