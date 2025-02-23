package com.jordanbunke.delta_time.scripting.ast.nodes.expression.std_lib.scoped;

import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.std_lib.MemberFuncCallNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.Arguments;
import com.jordanbunke.delta_time.scripting.util.ScriptErrorLog;
import com.jordanbunke.delta_time.scripting.util.TextPosition;
import com.jordanbunke.delta_time.scripting.util.TypeUtils;

import java.awt.*;

public final class ColorAtPixelNode extends MemberFuncCallNode {
    private static final int X = 0, Y = 1;

    public ColorAtPixelNode(
            final TextPosition position,
            final ExpressionNode receiver,
            final ExpressionNode x,
            final ExpressionNode y
    ) {
        super(position, receiver, TypeNode.getImage(), TypeNode.getColor(),
                Arguments.argsOf(x, y),
                TypeUtils.expectExact(TypeNode.getInt(), TypeNode.getInt()));
    }

    @Override
    public Color evaluate(final SymbolTable symbolTable) {
        final Object[] vals = arguments.evaluate(symbolTable);

        final int x = (int) vals[X], y = (int) vals[Y];
        final GameImage img = ((GameImage) receiver.evaluate(symbolTable));

        if (x < 0 || x >= img.getWidth())
            ScriptErrorLog.fireError(
                    ScriptErrorLog.Message.PIX_ARG_OUT_OF_BOUNDS,
                    getPosition(), "X", String.valueOf(x),
                    "width -- " + img.getWidth());
        if (y < 0 || y >= img.getHeight())
            ScriptErrorLog.fireError(
                    ScriptErrorLog.Message.PIX_ARG_OUT_OF_BOUNDS,
                    getPosition(), "Y", String.valueOf(y),
                    "height -- " + img.getHeight());

        return img.getColorAt(x, y);
    }

    @Override
    protected String funcName() {
        return "pixel";
    }
}
