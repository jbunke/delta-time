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

public final class ImageSectionNode extends MemberFuncCallNode {
    private static final int X = 0, Y = 1, W = 2, H = 3;

    public ImageSectionNode(
            final TextPosition position,
            final ExpressionNode receiver,
            final ExpressionNode x, final ExpressionNode y,
            final ExpressionNode width, final ExpressionNode height
    ) {
        super(position, receiver, TypeNode.getImage(), TypeNode.getImage(),
                Arguments.argsOf(x, y, width, height),
                TypeUtils.expectExact(TypeNode.getInt(), TypeNode.getInt(),
                        TypeNode.getInt(), TypeNode.getInt()));
    }

    @Override
    public GameImage evaluate(final SymbolTable symbolTable) {
        final Object[] vals = arguments.evaluate(symbolTable);

        final int x = (int) vals[X], y = (int) vals[Y],
                w = (int) vals[W], h = (int) vals[H];

        final GameImage source = (GameImage) receiver.evaluate(symbolTable);

        if (w > 0 && h > 0) {
            final GameImage section = new GameImage(w, h);
            section.draw(source, -x, -y);

            return section.submit();
        } else {
            if (w <= 0)
                ScriptErrorLog.fireError(
                        ScriptErrorLog.Message.NON_POSITIVE_IMAGE_BOUND,
                        arguments.get(W).getPosition(), "Width", String.valueOf(w));
            if (h <= 0)
                ScriptErrorLog.fireError(
                        ScriptErrorLog.Message.NON_POSITIVE_IMAGE_BOUND,
                        arguments.get(H).getPosition(), "Height", String.valueOf(h));
        }

        return null;
    }

    @Override
    protected String funcName() {
        return "section";
    }
}
