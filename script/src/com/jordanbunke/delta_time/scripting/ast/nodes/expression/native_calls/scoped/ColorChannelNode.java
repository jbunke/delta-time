package com.jordanbunke.delta_time.scripting.ast.nodes.expression.native_calls.scoped;

import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

import java.awt.*;
import java.util.Set;

public final class ColorChannelNode extends ScopedNativeCallNode {
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
            final TextPosition position,
            final ExpressionNode owner,
            final Channel channel
    ) {
        super(position, owner, Set.of(TypeNode.getColor()));

        this.channel = channel;
    }

    @Override
    public Integer evaluate(final SymbolTable symbolTable) {
        return channel.evaluate(((Color) getScope().evaluate(symbolTable)));
    }

    @Override
    public TypeNode getType(final SymbolTable symbolTable) {
        return TypeNode.getInt();
    }

    @Override
    String callName() {
        return channel.name().toLowerCase();
    }
}
