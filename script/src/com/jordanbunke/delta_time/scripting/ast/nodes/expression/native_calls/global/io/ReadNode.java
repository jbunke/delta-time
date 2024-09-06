package com.jordanbunke.delta_time.scripting.ast.nodes.expression.native_calls.global.io;

import com.jordanbunke.delta_time.scripting.Interpreter;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public final class ReadNode extends ExpressionNode {
    public ReadNode(final TextPosition position) {
        super(position);
    }

    @Override
    public void semanticErrorCheck(final SymbolTable symbolTable) {}

    @Override
    public String evaluate(final SymbolTable symbolTable) {
        return Interpreter.read();
    }

    @Override
    public TypeNode getType(final SymbolTable symbolTable) {
        return TypeNode.getString();
    }

    @Override
    public String toString() {
        return "read()";
    }
}
