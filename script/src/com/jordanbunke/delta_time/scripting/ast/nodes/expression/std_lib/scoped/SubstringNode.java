package com.jordanbunke.delta_time.scripting.ast.nodes.expression.std_lib.scoped;

import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.std_lib.MemberFuncCallNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.ScriptErrorLog;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

import static com.jordanbunke.delta_time.scripting.util.Arguments.argsOf;
import static com.jordanbunke.delta_time.scripting.util.TypeUtils.expectExact;

public final class SubstringNode extends MemberFuncCallNode {
    private static final int BEG = 0, END = 1;

    public SubstringNode(
            final TextPosition position,
            final ExpressionNode receiver,
            final ExpressionNode beginning,
            final ExpressionNode end
    ) {
        super(position, receiver, TypeNode.getString(),
                TypeNode.getString(), argsOf(beginning, end),
                expectExact(TypeNode.getInt(), TypeNode.getInt()));
    }

    @Override
    public String evaluate(final SymbolTable symbolTable) {
        final Object[] vals = arguments.evaluate(symbolTable);

        final int beg = (int) vals[BEG], end = (int) vals[END];

        final String s = (String) receiver.evaluate(symbolTable);

        if (beg >= 0 && end <= s.length() && beg < end)
            return s.substring(beg, end);
        else {
            if (beg < 0)
                ScriptErrorLog.fireError(
                        ScriptErrorLog.Message.SUB_BEG_OUT_OF_BOUNDS,
                        arguments.get(BEG).getPosition(),
                        String.valueOf(beg));
            if (end > s.length())
                ScriptErrorLog.fireError(
                        ScriptErrorLog.Message.SUB_END_OUT_OF_BOUNDS,
                        arguments.get(END).getPosition(),
                        String.valueOf(s.length()), String.valueOf(end));
            if (beg >= end)
                ScriptErrorLog.fireError(
                        ScriptErrorLog.Message.SUB_END_GEQ_BEG,
                        arguments.get(BEG).getPosition(),
                        String.valueOf(beg), String.valueOf(end));
        }

        return null;
    }

    @Override
    protected String funcName() {
        return "sub";
    }
}
