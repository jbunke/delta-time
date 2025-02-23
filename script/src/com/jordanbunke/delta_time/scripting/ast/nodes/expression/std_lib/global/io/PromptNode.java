package com.jordanbunke.delta_time.scripting.ast.nodes.expression.std_lib.global.io;

import com.jordanbunke.delta_time.scripting.Interpreter;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.std_lib.DefFuncCallNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.Arguments;
import com.jordanbunke.delta_time.scripting.util.TextPosition;
import com.jordanbunke.delta_time.scripting.util.TypeUtils;

public final class PromptNode extends DefFuncCallNode {
    public PromptNode(
            final TextPosition position, final ExpressionNode message
    ) {
        super(new Arguments(Arguments.argsOf(message),
                TypeUtils.expectExact(TypeNode.wildcard())),
                TypeNode.getString(), position);
    }

    @Override
    public String evaluate(final SymbolTable symbolTable) {
        final String m = String.valueOf(arguments.get(0).evaluate(symbolTable));

        return Interpreter.prompt(m);
    }

    @Override
    protected String funcName() {
        return "prompt";
    }
}
