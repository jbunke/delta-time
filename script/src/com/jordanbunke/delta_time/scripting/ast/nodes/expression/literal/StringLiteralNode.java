package com.jordanbunke.delta_time.scripting.ast.nodes.expression.literal;

import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public final class StringLiteralNode extends LiteralNode {
    private final String value;

    public StringLiteralNode(
            final TextPosition position, final String value
    ) {
        super(position);

        this.value = process(value);
    }

    @Override
    public String evaluate(final SymbolTable symbolTable) {
        return value;
    }

    @Override
    public TypeNode getType(final SymbolTable symbolTable) {
        return TypeNode.getString();
    }

    @Override
    public String toString() {
        return "\"" + display(value) + "\"";
    }

    private String process(final String s) {
        final String temp = "%_temp_%";
        return s.replace("\\\\", temp) // first
                .replace("\\n", "\n")
                .replace("\\t", "\t")
                .replace(temp, "\\"); // last
    }

    private String display(final String s) {
        final String temp = "%_temp_%";
        return s.replace("\\", temp) // first
                .replace("\n", "\\n")
                .replace("\t", "\\t")
                .replace(temp, "\\\\"); // last
    }
}
