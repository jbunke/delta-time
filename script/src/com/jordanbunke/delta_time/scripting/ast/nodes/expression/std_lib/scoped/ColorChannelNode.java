package com.jordanbunke.delta_time.scripting.ast.nodes.expression.std_lib.scoped;

import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.std_lib.PropertyNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

import java.awt.*;

public final class ColorChannelNode extends PropertyNode {
    public enum Channel {
        RED, GREEN, BLUE, ALPHA;

        private int evaluate(final Color c) {
            return switch (this) {
                case RED -> c.getRed();
                case GREEN -> c.getGreen();
                case BLUE -> c.getBlue();
                case ALPHA -> c.getAlpha();
            };
        }
    }

    private final Channel channel;

    public ColorChannelNode(
            final TextPosition position, final ExpressionNode receiver,
            final Channel channel
    ) {
        super(position, receiver, TypeNode.getColor(), TypeNode.getInt());

        this.channel = channel;
    }

    @Override
    public Integer evaluate(final SymbolTable symbolTable) {
        return channel.evaluate(((Color) receiver.evaluate(symbolTable)));
    }

    @Override
    protected String funcName() {
        return channel.name().toLowerCase();
    }
}
