package com.jordanbunke.delta_time.scripting.ast.nodes.function;

import com.jordanbunke.delta_time.scripting.ast.nodes.statement.StatementNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public final class HeadFuncNode extends FuncNode {
    private final HelperFuncNode[] helpers;

    public HeadFuncNode(
            final TextPosition position,
            final FuncSignatureNode signature,
            final StatementNode body,
            final HelperFuncNode[] helpers
    ) {
        super(position, signature, body);

        this.helpers = helpers;
    }

    @Override
    public void semanticErrorCheck(final SymbolTable symbolTable) {
        for (HelperFuncNode helper : helpers)
            helper.link(symbolTable);

        for (HelperFuncNode helper : helpers)
            helper.semanticErrorCheck(symbolTable);

        super.semanticErrorCheck(symbolTable);
    }
}
