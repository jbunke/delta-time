package com.jordanbunke.delta_time.scripting.ast.nodes.statement.std_lib;

import com.jordanbunke.delta_time.scripting.ast.nodes.statement.StatementNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.Arguments;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public abstract class DefFuncExecNode extends StatementNode {
    protected final Arguments arguments;

    public DefFuncExecNode(
            final Arguments arguments, final TextPosition position
    ) {
        super(position);

        this.arguments = arguments;
    }

    @Override
    public void semanticErrorCheck(final SymbolTable symbolTable) {
        arguments.semanticErrorCheck(symbolTable, getPosition());
    }

    protected abstract String funcName();

    @Override
    public String toString() {
        return funcName() + arguments + ";";
    }
}
