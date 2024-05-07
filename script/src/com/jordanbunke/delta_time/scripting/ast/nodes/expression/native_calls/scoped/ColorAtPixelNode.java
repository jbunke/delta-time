package com.jordanbunke.delta_time.scripting.ast.nodes.expression.native_calls.scoped;

import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.BaseTypeNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.ScriptErrorLog;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

import java.awt.*;
import java.util.Set;

public final class ColorAtPixelNode extends ScopedNativeCallNode {
    private final ExpressionNode x, y;

    public ColorAtPixelNode(
            final TextPosition position,
            final ExpressionNode owner,
            final ExpressionNode x,
            final ExpressionNode y
    ) {
        super(position, owner,
                Set.of(TypeNode.getImage()));

        this.x = x;
        this.y = y;
    }

    @Override
    public void semanticErrorCheck(final SymbolTable symbolTable) {
        x.semanticErrorCheck(symbolTable);
        y.semanticErrorCheck(symbolTable);

        super.semanticErrorCheck(symbolTable);

        final BaseTypeNode intType = TypeNode.getInt();

        final TypeNode
                xType = x.getType(symbolTable),
                yType = y.getType(symbolTable);

        if (!xType.equals(intType))
            ScriptErrorLog.fireError(
                    ScriptErrorLog.Message.ARG_NOT_TYPE,
                    getPosition(), "X", "int", xType.toString());
        if (!yType.equals(intType))
            ScriptErrorLog.fireError(
                    ScriptErrorLog.Message.ARG_NOT_TYPE,
                    getPosition(), "Y", "int", yType.toString());
    }

    @Override
    public Color evaluate(final SymbolTable symbolTable) {
        final int pixelX = (int) x.evaluate(symbolTable),
                pixelY = (int) y.evaluate(symbolTable);
        final GameImage img = ((GameImage) getScope().evaluate(symbolTable));

        if (pixelX < 0 || pixelX >= img.getWidth())
            ScriptErrorLog.fireError(
                    ScriptErrorLog.Message.PIX_ARG_OUT_OF_BOUNDS,
                    getPosition(), "X", String.valueOf(pixelX),
                    "width -- " + img.getWidth());
        if (pixelY < 0 || pixelY >= img.getHeight())
            ScriptErrorLog.fireError(
                    ScriptErrorLog.Message.PIX_ARG_OUT_OF_BOUNDS,
                    getPosition(), "Y", String.valueOf(pixelY),
                    "height -- " + img.getHeight());

        return img.getColorAt(pixelX, pixelY);
    }

    @Override
    public TypeNode getType(final SymbolTable symbolTable) {
        return TypeNode.getColor();
    }

    @Override
    String callName() {
        return "pixel";
    }
}
