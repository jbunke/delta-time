package com.jordanbunke.delta_time.scripting.ast.nodes.statement.std_lib.global;

import com.jordanbunke.delta_time.scripting.Interpreter;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.statement.std_lib.DefFuncExecNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.Arguments;
import com.jordanbunke.delta_time.scripting.util.FuncControlFlow;
import com.jordanbunke.delta_time.scripting.util.TextPosition;
import com.jordanbunke.delta_time.scripting.util.TypeUtils;

public final class PrintNode extends DefFuncExecNode {
    public PrintNode(
            final TextPosition position, final ExpressionNode message
    ) {
        super(new Arguments(Arguments.argsOf(message),
                TypeUtils.expectExact(TypeNode.wildcard())), position);
    }

    @Override
    public FuncControlFlow execute(final SymbolTable symbolTable) {
        final String m = String.valueOf(arguments.get(0).evaluate(symbolTable));
        Interpreter.println(m);

        return FuncControlFlow.cont();
    }

    @Override
    protected String funcName() {
        return "print";
    }
}
