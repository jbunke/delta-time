package com.jordanbunke.delta_time.scripting.ast.nodes.expression.std_lib.global.img_gen;

import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.std_lib.DefFuncCallNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.Arguments;
import com.jordanbunke.delta_time.scripting.util.ScriptErrorLog;
import com.jordanbunke.delta_time.scripting.util.TextPosition;
import com.jordanbunke.delta_time.scripting.util.TypeUtils;

public final class ImageOfBoundsNode extends DefFuncCallNode {
    private static final int W = 0, H = 1;

    public ImageOfBoundsNode(
            final TextPosition position,
            final ExpressionNode width,
            final ExpressionNode height
    ) {
        super(new Arguments(Arguments.argsOf(width, height),
                TypeUtils.expectExact(TypeNode.getInt(), TypeNode.getInt())),
                TypeNode.getImage(), position);
    }

    @Override
    public GameImage evaluate(final SymbolTable symbolTable) {
        final Object[] vals = arguments.evaluate(symbolTable);

        final int w = (int) vals[W], h = (int) vals[H];

        if (w > 0 && h > 0)
            return new GameImage(w, h);
        else {
            if (w <= 0)
                ScriptErrorLog.fireError(
                        ScriptErrorLog.Message.NON_POSITIVE_IMAGE_BOUND,
                        arguments.get(W).getPosition(),
                        "Width", String.valueOf(w));
            if (h <= 0)
                ScriptErrorLog.fireError(
                        ScriptErrorLog.Message.NON_POSITIVE_IMAGE_BOUND,
                        arguments.get(H).getPosition(),
                        "Height", String.valueOf(h));
        }

        return null;
    }

    @Override
    protected String funcName() {
        return "new_image_of";
    }
}
