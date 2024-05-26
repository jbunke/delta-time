package com.jordanbunke.delta_time.scripting.ast.nodes;

import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public final class GenericIllegalNode extends ASTNode
        implements IllegalLanguageFeature {
    private final String description;

    public GenericIllegalNode(
            final TextPosition position,
            final String description
    ) {
        super(position);
        this.description = description;

        fireError();
    }

    @Override
    public void semanticErrorCheck(final SymbolTable symbolTable) {
        fireError();
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return getError();
    }
}
