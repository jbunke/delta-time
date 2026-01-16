package com.jordanbunke.delta_time.scripting.ast.nodes.expression.std_lib.global.rng;

import com.jordanbunke.delta_time.scripting.ast.nodes.expression.std_lib.DefFuncCallNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.Arguments;
import com.jordanbunke.delta_time.scripting.util.ScriptVisitor;
import com.jordanbunke.delta_time.scripting.util.TextPosition;
import com.jordanbunke.delta_time.utility.math.RNG;

public final class RandNode extends DefFuncCallNode {
    public RandNode(
            final TextPosition position
    ) {
        super(Arguments.none(), TypeNode.getFloat(), position);
    }

    @Override
    public void semanticErrorCheck(final SymbolTable symbolTable) {}

    @Override
    public Double evaluate(final SymbolTable symbolTable) {
        return RNG.randomInRange(0d, 1d);
    }

    @Override
    protected String funcName() {
        return ScriptVisitor.RAND;
    }
}
