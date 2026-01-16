package com.jordanbunke.delta_time.scripting.ast.nodes.expression.std_lib.global.rng;

import com.jordanbunke.delta_time.scripting.ast.nodes.expression.std_lib.DefFuncCallNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.Arguments;
import com.jordanbunke.delta_time.scripting.util.ScriptVisitor;
import com.jordanbunke.delta_time.scripting.util.TextPosition;
import com.jordanbunke.delta_time.utility.math.RNG;

public final class FlipCoinNode extends DefFuncCallNode {
    public FlipCoinNode(TextPosition position) {
        super(Arguments.none(), TypeNode.getBool(), position);
    }

    @Override
    public void semanticErrorCheck(final SymbolTable symbolTable) {}

    @Override
    public Object evaluate(final SymbolTable symbolTable) {
        return RNG.flipCoin();
    }

    @Override
    protected String funcName() {
        return ScriptVisitor.FLIP_COIN;
    }
}
