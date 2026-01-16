package com.jordanbunke.delta_time.scripting.ast.nodes.statement.std_lib;

import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.FuncControlFlow;
import com.jordanbunke.delta_time.scripting.util.ScriptVisitor;
import com.jordanbunke.delta_time.scripting.util.TextPosition;
import com.jordanbunke.delta_time.scripting.util.TypeUtils;

import java.awt.*;

public final class FillNode extends MemberFuncExecNode {
    public FillNode(
            final TextPosition position,
            final ExpressionNode canvas, final ExpressionNode color,
            final ExpressionNode x, final ExpressionNode y,
            final ExpressionNode width, final ExpressionNode height
    ) {
        super(position, canvas, TypeNode.getImage(),
                new ExpressionNode[] { color, x, y, width, height },
                TypeUtils.expectExact(TypeNode.getColor(), TypeNode.getInt(),
                        TypeNode.getInt(), TypeNode.getInt(), TypeNode.getInt()));
    }

    @Override
    public FuncControlFlow execute(final SymbolTable symbolTable) {
        final Object[] vs = arguments.evaluate(symbolTable);

        final Color color = (Color) vs[0];
        final int x = (int) vs[1], y = (int) vs[2], width = (int) vs[3], height = (int) vs[4];
        final GameImage canvas = (GameImage) receiver.evaluate(symbolTable);

        // TODO - potential runtime errors based on argument values

        canvas.fillRectangle(color, x, y, width, height);
        canvas.free();

        return FuncControlFlow.cont();
    }

    @Override
    protected String funcName() {
        return ScriptVisitor.FILL;
    }
}
