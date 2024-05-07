package com.jordanbunke.delta_time.scripting.ast.nodes.expression.native_calls.scoped;

import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

import java.util.Set;

public final class ImageBoundNode extends ScopedNativeCallNode {
    private final boolean width;

    public ImageBoundNode(
            final TextPosition position,
            final ExpressionNode owner,
            final boolean width
    ) {
        super(position, owner,
                Set.of(TypeNode.getImage()));

        this.width = width;
    }

    @Override
    public Integer evaluate(final SymbolTable symbolTable) {
        final GameImage img = ((GameImage) getScope().evaluate(symbolTable));

        return width ? img.getWidth() : img.getHeight();
    }

    @Override
    public TypeNode getType(final SymbolTable symbolTable) {
        return TypeNode.getInt();
    }

    @Override
    String callName() {
        return width ? "width" : "height";
    }
}
