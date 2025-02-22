package com.jordanbunke.delta_time.scripting.ast.nodes.expression.std_lib.global.img_gen;

import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.io.GameImageIO;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.PathHelper;
import com.jordanbunke.delta_time.scripting.util.ScriptErrorLog;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public final class ImageFromPathNode extends ExpressionNode {
    private final ExpressionNode path;

    public ImageFromPathNode(
            final TextPosition position,
            final ExpressionNode path
    ) {
        super(position);

        this.path = path;
    }

    @Override
    public void semanticErrorCheck(final SymbolTable symbolTable) {
        path.semanticErrorCheck(symbolTable);

        final TypeNode pathType = path.getType(symbolTable);

        if (!pathType.equals(TypeNode.getString()))
            ScriptErrorLog.fireError(
                    ScriptErrorLog.Message.ARG_NOT_TYPE,
                    path.getPosition(), "Image filepath",
                    TypeNode.getString().toString(), pathType.toString());
    }

    @Override
    public GameImage evaluate(final SymbolTable symbolTable) {
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
    public TypeNode getType(final SymbolTable symbolTable) {
        return TypeNode.getImage();
    }

    @Override
    public String toString() {
        return "read_image(" + path + ")";
    }
}
