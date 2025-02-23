package com.jordanbunke.delta_time.scripting.ast.nodes.expression.std_lib.global.io;

import com.jordanbunke.delta_time.scripting.Interpreter;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.std_lib.DefFuncCallNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.Arguments;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public final class ReadNode extends DefFuncCallNode {
    public ReadNode(final TextPosition position) {
        super(Arguments.none(), TypeNode.getString(), position);
    }

    @Override
    public void semanticErrorCheck(final SymbolTable symbolTable) {}

    @Override
    public String evaluate(final SymbolTable symbolTable) {
        return Interpreter.read();
    }

    @Override
    protected String funcName() {
        return "read";
    }
}
