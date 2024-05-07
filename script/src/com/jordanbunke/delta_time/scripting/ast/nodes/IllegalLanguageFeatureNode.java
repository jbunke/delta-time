package com.jordanbunke.delta_time.scripting.ast.nodes;

import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.ScriptErrorLog;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public final class IllegalLanguageFeatureNode extends ASTNode {
    private final String description;

    public IllegalLanguageFeatureNode(
            final TextPosition position,
            final String description
    ) {
        super(position);
        this.description = description;

        semanticErrorCheck(null);
    }

    @Override
    public void semanticErrorCheck(final SymbolTable symbolTable) {
        ScriptErrorLog.fireError(ScriptErrorLog.Message.CUSTOM_CT,
                getPosition(), toString());
    }

    @Override
    public String toString() {
        return "Illegal language feature: " + description;
    }
}
