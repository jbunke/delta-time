package com.jordanbunke.delta_time.scripting.ast.nodes.expression.std_lib.global.io;

import com.jordanbunke.delta_time.io.FileIO;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.std_lib.DefFuncCallNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.*;

public final class ReadFileNode extends DefFuncCallNode {
    public ReadFileNode(
            final TextPosition position, final ExpressionNode path
    ) {
        super(new Arguments(Arguments.argsOf(path),
                        TypeUtils.expectExact(TypeNode.getString())),
                TypeNode.getString(), position);
    }

    @Override
    public Object evaluate(final SymbolTable symbolTable) {
        final ExpressionNode path = arguments.get(0);

        final String fp = (String) path.evaluate(symbolTable);
        final String content = FileIO.readFile(
                PathHelper.process(fp, symbolTable, path.getPosition()));

        if (content == null)
            ScriptErrorLog.runtimeError(path.getPosition(),
                    "Could not read a file at the location \"" +
                            PathHelper.formatPathString(fp) + "\"");

        return content;
    }

    @Override
    protected String funcName() {
        return ScriptVisitor.READ_FILE;
    }
}
