package com.jordanbunke.delta_time.scripting.ast.nodes.statement;

import com.jordanbunke.delta_time.scripting.ast.nodes.IllegalLanguageFeature;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.FuncControlFlow;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public final class IllegalStatementNode extends StatementNode
        implements IllegalLanguageFeature {
    private final String description;

    public IllegalStatementNode(
            final TextPosition position, final String description
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
    public FuncControlFlow execute(final SymbolTable symbolTable) {
        return FuncControlFlow.cont();
    }

    @Override
    public String toString() {
        return getError();
    }
}
