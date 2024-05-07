package com.jordanbunke.delta_time.scripting.ast.nodes.statement;

import com.jordanbunke.delta_time.scripting.ast.nodes.ASTNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.FuncControlFlow;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public abstract class StatementNode extends ASTNode {
    public StatementNode(final TextPosition position) {
        super(position);
    }

    public abstract FuncControlFlow execute(final SymbolTable symbolTable);
}
