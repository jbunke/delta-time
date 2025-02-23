package com.jordanbunke.delta_time.scripting.ast.nodes.expression.std_lib.scoped;

import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.std_lib.MemberFuncCallNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.Arguments;
import com.jordanbunke.delta_time.scripting.util.ScriptErrorLog;
import com.jordanbunke.delta_time.scripting.util.TextPosition;
import com.jordanbunke.delta_time.scripting.util.TypeUtils;

public final class CharAtNode extends MemberFuncCallNode {
    public CharAtNode(
            final TextPosition position,
            final ExpressionNode receiver,
            final ExpressionNode index
    ) {
        super(position, receiver, TypeNode.getString(), TypeNode.getChar(),
                Arguments.argsOf(index), TypeUtils.expectExact(TypeNode.getInt()));
    }

    @Override
    public Object evaluate(final SymbolTable symbolTable) {
        final int i = (int) arguments.get(0).evaluate(symbolTable);
        final String s = (String) receiver.evaluate(symbolTable);

        if (i >= 0 && i < s.length())
            return s.charAt(i);
        else
            ScriptErrorLog.fireError(
                    ScriptErrorLog.Message.INDEX_OUT_OF_BOUNDS,
                    arguments.get(0).getPosition(), String.valueOf(i),
                    String.valueOf(s.length()), String.valueOf(false));

        return null;
    }

    @Override
    protected String funcName() {
        return "at";
    }
}
