package com.jordanbunke.delta_time.scripting.ast.nodes.expression.std_lib.global.img_gen;

import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.std_lib.DefFuncCallNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.*;

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
                ScriptErrorLog.runtimeError(arguments.get(W).getPosition(),
                        "Attempted to create a new image with a non-positive width argument (" + w + ")");
            if (h <= 0)
                ScriptErrorLog.runtimeError(arguments.get(H).getPosition(),
                        "Attempted to create a new image with a non-positive height argument (" + h + ")");
        }

        return null;
    }

    @Override
    protected String funcName() {
        return ScriptVisitor.NEW_IMAGE_OF;
    }
}
