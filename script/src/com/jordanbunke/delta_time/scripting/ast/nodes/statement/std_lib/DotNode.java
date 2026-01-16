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

public final class DotNode extends MemberFuncExecNode {
    public DotNode(
            final TextPosition position,
            final ExpressionNode canvas,
            final ExpressionNode color,
            final ExpressionNode x, final ExpressionNode y
    ) {
        super(position, canvas, TypeNode.getString(),
                new ExpressionNode[] { color, x, y },
                TypeUtils.expectExact(TypeNode.getColor(),
                        TypeNode.getInt(), TypeNode.getInt()));
    }

    @Override
    public FuncControlFlow execute(final SymbolTable symbolTable) {
        final Object[] vs = arguments.evaluate(symbolTable);

        final Color color = (Color) vs[0];
        final int x = (int) vs[1], y = (int) vs[2];
        final GameImage canvas = (GameImage) receiver.evaluate(symbolTable);

        canvas.setRGB(x, y, color.getRGB());
        canvas.free();

        return FuncControlFlow.cont();
    }

    @Override
    protected String funcName() {
        return ScriptVisitor.DOT;
    }
}
