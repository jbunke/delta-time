package com.jordanbunke.delta_time.scripting.ast.nodes.statement.std_lib.global;

import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.io.GameImageIO;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.statement.std_lib.DefFuncExecNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.*;

import java.nio.file.Path;

public final class WriteImageNode extends DefFuncExecNode {
    private static final int PATH = 0, IMAGE = 1;

    public WriteImageNode(
            final TextPosition position,
            final ExpressionNode path, final ExpressionNode image
    ) {
        super(new Arguments(Arguments.argsOf(path, image),
                TypeUtils.expectExact(TypeNode.getString(),
                        TypeNode.getImage())), position);
    }

    @Override
    public FuncControlFlow execute(final SymbolTable symbolTable) {
        final Object[] vals = arguments.evaluate(symbolTable);

        final String fp = (String) vals[PATH];
        final GameImage image = (GameImage) vals[IMAGE];
        final Path path = PathHelper.process(fp, symbolTable,
                arguments.get(PATH).getPosition());

        GameImageIO.writeImage(path, image);

        return FuncControlFlow.cont();
    }

    @Override
    protected String funcName() {
        return "write_file";
    }
}
