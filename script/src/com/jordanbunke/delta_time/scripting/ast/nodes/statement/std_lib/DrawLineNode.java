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

public final class DrawLineNode extends MemberFuncExecNode {
    public DrawLineNode(
            final TextPosition position,
            final ExpressionNode canvas,
            final ExpressionNode color,
            final ExpressionNode breadth,
            final ExpressionNode x1, final ExpressionNode y1,
            final ExpressionNode x2, final ExpressionNode y2
    ) {
        super(position, canvas, TypeNode.getImage(),
                new ExpressionNode[] { color, breadth, x1, y1, x2, y2 },
                TypeUtils.expectExact(TypeNode.getColor(), TypeNode.getFloat(),
                        TypeNode.getInt(), TypeNode.getInt(),
                        TypeNode.getInt(), TypeNode.getInt()));
    }

    @Override
    public FuncControlFlow execute(final SymbolTable symbolTable) {
        final Object[] vs = arguments.evaluate(symbolTable);

        final Color color = (Color) vs[0];
        final float breadth = ((Double) vs[1]).floatValue();
        final int x1 = (int) vs[2], y1 = (int) vs[3], x2 = (int) vs[4], y2 = (int) vs[5];
        final GameImage canvas = (GameImage) receiver.evaluate(symbolTable);

        if (breadth < 0f)
            return FuncControlFlow.cont();

        canvas.drawLine(color, breadth, x1, y1, x2, y2);
        canvas.free();

        return FuncControlFlow.cont();
    }

    @Override
    protected String funcName() {
        return ScriptVisitor.LINE;
    }
}
