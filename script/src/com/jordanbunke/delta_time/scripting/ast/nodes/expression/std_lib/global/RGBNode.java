package com.jordanbunke.delta_time.scripting.ast.nodes.expression.std_lib.global;

import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.std_lib.DefFuncCallNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.Arguments;
import com.jordanbunke.delta_time.scripting.util.ScriptErrorLog;
import com.jordanbunke.delta_time.scripting.util.TextPosition;
import com.jordanbunke.delta_time.scripting.util.TypeUtils;

import java.awt.*;

import static com.jordanbunke.delta_time.scripting.util.Arguments.argsOf;

public final class RGBNode extends DefFuncCallNode {
    private static final int R = 0, G = 1, B = 2, A = 3;

    private final boolean hasAlpha;

    public RGBNode(
            final TextPosition position,
            final ExpressionNode r, final ExpressionNode g,
            final ExpressionNode b, final ExpressionNode a
    ) {
        super(prepArgs(r, g, b, a), TypeNode.getColor(), position);

        hasAlpha = a != null;
    }

    private static Arguments prepArgs(
            final ExpressionNode r, final ExpressionNode g,
            final ExpressionNode b, final ExpressionNode a
    ) {
        final ExpressionNode[] args = a == null
                ? argsOf(r, g, b) : argsOf(r, g, b, a);
        final TypeNode[][] expected = a == null
                ? TypeUtils.expectExact(TypeNode.getInt(), TypeNode.getInt(),
                TypeNode.getInt())
                : TypeUtils.expectExact(TypeNode.getInt(), TypeNode.getInt(),
                TypeNode.getInt(), TypeNode.getInt());

        return new Arguments(args, expected);
    }

    @Override
    public Color evaluate(final SymbolTable symbolTable) {
        final Object[] vals = arguments.evaluate(symbolTable);

        final int MAX = 0xff, MIN = 0, r = (int) vals[R], g = (int) vals[G],
                b = (int) vals[B], a = hasAlpha ? (int) vals[A] : MAX;
        final int[] channels = new int[] { r, g, b, a };

        for (int c = 0; c < channels.length; c++) {
            final String channelName = switch (c) {
                case R -> "Red";
                case G -> "Green";
                case B -> "Blue";
                default -> "Alpha / opacity";
            };
            final int val = channels[c];

            if (val < MIN || val > MAX) {
                ScriptErrorLog.fireError(
                        ScriptErrorLog.Message.COLOR_CHANNEL_OUT_OF_BOUNDS,
                        getPosition(), channelName, String.valueOf(val));
                return null;
            }
        }

        return new Color(r, g, b, a);
    }

    @Override
    protected String funcName() {
        return "rgb" + (hasAlpha ? "a" : "");
    }
}
