package com.jordanbunke.delta_time.scripting.ast.nodes.expression.std_lib.scoped;

import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.std_lib.MemberFuncCallNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.Arguments;
import com.jordanbunke.delta_time.scripting.util.ScriptVisitor;
import com.jordanbunke.delta_time.scripting.util.TextPosition;
import com.jordanbunke.delta_time.scripting.util.TypeUtils;

import java.awt.*;

import static com.jordanbunke.delta_time.scripting.util.ScriptErrorLog.*;

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

        if (x < 0 || x >= img.getWidth()) {
            runtimeError(arguments.get(0).getPosition(),
                    "Attempted to retrieve pixel color with an out-of-bounds X coordinate (x=" +
                            x + "; width=" + img.getWidth() + "px)");
            return null;
        }
        if (y < 0 || y >= img.getHeight()) {
            runtimeError(arguments.get(1).getPosition(),
                    "Attempted to retrieve pixel color with an out-of-bounds Y coordinate (y=" +
                            x + "; height=" + img.getWidth() + "px)");
            return null;
        }

        return img.getColorAt(x, y);
    }

    @Override
    protected String funcName() {
        return ScriptVisitor.PIXEL;
    }
}
