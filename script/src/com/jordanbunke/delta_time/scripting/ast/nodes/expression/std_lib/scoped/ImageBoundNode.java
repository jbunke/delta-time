package com.jordanbunke.delta_time.scripting.ast.nodes.expression.std_lib.scoped;

import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.std_lib.PropertyNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.ScriptVisitor;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public final class ImageBoundNode extends PropertyNode {
    private final boolean width;

    public ImageBoundNode(
            final TextPosition position,
            final ExpressionNode receiver,
            final boolean width
    ) {
        super(position, receiver, TypeNode.getImage(), TypeNode.getInt());

        this.width = width;
    }

    @Override
    public Integer evaluate(final SymbolTable symbolTable) {
        final GameImage img = ((GameImage) receiver.evaluate(symbolTable));

        return width ? img.getWidth() : img.getHeight();
    }

    @Override
    protected String funcName() {
        return width ? ScriptVisitor.WIDTH_L : ScriptVisitor.HEIGHT_L;
    }
}
