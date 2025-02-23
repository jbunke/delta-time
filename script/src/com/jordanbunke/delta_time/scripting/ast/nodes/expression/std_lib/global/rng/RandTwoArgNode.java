package com.jordanbunke.delta_time.scripting.ast.nodes.expression.std_lib.global.rng;

import com.jordanbunke.delta_time.utility.math.RNG;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.std_lib.global.min_max.NumBinOpNode;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public final class RandTwoArgNode extends NumBinOpNode {
    public RandTwoArgNode(
            final TextPosition position,
            final ExpressionNode min, final ExpressionNode max
    ) {
        super(position, min, max, RNG::randomInRange, RNG::randomInRange);
    }

    @Override
    protected String funcName() {
        return "rand";
    }
}
