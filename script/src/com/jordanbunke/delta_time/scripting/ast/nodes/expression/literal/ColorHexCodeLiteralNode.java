package com.jordanbunke.delta_time.scripting.ast.nodes.expression.literal;

import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.ColorHelper;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

import java.awt.*;

public final class ColorHexCodeLiteralNode extends LiteralNode {
    private final Color value;

    public ColorHexCodeLiteralNode(
            final TextPosition position,
            final String code
    ) {
        super(position);

        // trim prepended #
        final String contents = code.substring(1);

        this.value = ColorHelper.hexCodeToColor(
                contents + (contents.length() == 6 ? "ff" : ""));
    }

    @Override
    public Color evaluate(final SymbolTable symbolTable) {
        return value;
    }

    @Override
    public TypeNode getType(final SymbolTable symbolTable) {
        return TypeNode.getColor();
    }

    @Override
    public String toString() {
        return "#" + ColorHelper.colorToHexCode(value);
    }
}
