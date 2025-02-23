package com.jordanbunke.delta_time.scripting.ast.nodes.expression.std_lib.global.img_gen;

import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.io.GameImageIO;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.std_lib.DefFuncCallNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.*;

public final class ImageFromPathNode extends DefFuncCallNode {
    public ImageFromPathNode(
            final TextPosition position, final ExpressionNode path
    ) {
        super(new Arguments(Arguments.argsOf(path),
                TypeUtils.expectExact(TypeNode.getString())),
                TypeNode.getImage(), position);
    }

    @Override
    public GameImage evaluate(final SymbolTable symbolTable) {
        final ExpressionNode path = arguments.get(0);

        final String fp = (String) path.evaluate(symbolTable);
        final GameImage image = GameImageIO.readImage(
                PathHelper.process(fp, symbolTable, path.getPosition()));

        if (image == null)
            ScriptErrorLog.fireError(
                    ScriptErrorLog.Message.PATH_DOES_NOT_CONTAIN_IMAGE,
                    path.getPosition(), PathHelper.formatPathString(fp));

        return image;
    }

    @Override
    protected String funcName() {
        return "read_image";
    }
}
