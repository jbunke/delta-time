package com.jordanbunke.delta_time.scripting.ast.nodes.statement.std_lib.global;

import com.jordanbunke.delta_time.io.FileIO;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.statement.std_lib.DefFuncExecNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.*;

import java.nio.file.Path;

public final class WriteFileNode extends DefFuncExecNode {
    private static final int PATH = 0, CONTENT = 1;

    public WriteFileNode(
            final TextPosition position,
            final ExpressionNode path, final ExpressionNode content
    ) {
        super(new Arguments(Arguments.argsOf(path, content),
                TypeUtils.expectExact(TypeNode.getString(),
                        TypeNode.getString())), position);
    }

    @Override
    public FuncControlFlow execute(final SymbolTable symbolTable) {
        final Object[] vals = arguments.evaluate(symbolTable);

        final String fp = (String) vals[PATH], content = (String) vals[CONTENT];
        final Path path = PathHelper.process(fp, symbolTable,
                arguments.get(PATH).getPosition());

        FileIO.writeFile(path, content);

        return FuncControlFlow.cont();
    }

    @Override
    protected String funcName() {
        return ScriptVisitor.WRITE_FILE;
    }
}
