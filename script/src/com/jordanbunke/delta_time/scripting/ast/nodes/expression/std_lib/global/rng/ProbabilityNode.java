package com.jordanbunke.delta_time.scripting.ast.nodes.expression.std_lib.global.rng;

import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.std_lib.DefFuncCallNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.Arguments;
import com.jordanbunke.delta_time.scripting.util.TextPosition;
import com.jordanbunke.delta_time.scripting.util.TypeUtils;
import com.jordanbunke.delta_time.utility.math.RNG;

public final class ProbabilityNode extends DefFuncCallNode {
    public ProbabilityNode(
            final TextPosition position,
            final ExpressionNode p
    ) {
        super(new Arguments(Arguments.argsOf(p),
                TypeUtils.expectExact(TypeNode.getFloat())),
                TypeNode.getBool(), position);
    }

    @Override
    public Boolean evaluate(final SymbolTable symbolTable) {
        return RNG.prob((double) arguments.get(0).evaluate(symbolTable));
    }

    @Override
    protected String funcName() {
        return "prob";
    }
}
