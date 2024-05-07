package com.jordanbunke.delta_time.scripting.ast.nodes;

import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public abstract class ASTNode {
    private final TextPosition position;

    public ASTNode(final TextPosition position) {
        this.position = position;
    }

    public abstract void semanticErrorCheck(final SymbolTable symbolTable);

    public abstract String toString();

    public final TextPosition getPosition() {
        return position;
    }
}
