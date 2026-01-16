package com.jordanbunke.delta_time.scripting.ast.nodes.statement.std_lib;

import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.FuncControlFlow;
import com.jordanbunke.delta_time.scripting.util.ScriptVisitor;
import com.jordanbunke.delta_time.scripting.util.TextPosition;
import com.jordanbunke.delta_time.scripting.util.TypeUtils;

public final class DrawNode extends MemberFuncExecNode {
    public DrawNode(
            final TextPosition position,
            final ExpressionNode canvas,
            final ExpressionNode superimposed,
            final ExpressionNode x, final ExpressionNode y
    ) {
        super(position, canvas, TypeNode.getImage(),
                new ExpressionNode[] { superimposed, x, y },
                TypeUtils.expectExact(TypeNode.getImage(),
                        TypeNode.getInt(), TypeNode.getInt()));
    }

    @Override
    public FuncControlFlow execute(final SymbolTable symbolTable) {
        final Object[] vs = arguments.evaluate(symbolTable);

        final int x = (int) vs[1], y = (int) vs[2];
        final GameImage superimposed = (GameImage) vs[0],
                canvas = (GameImage) receiver.evaluate(symbolTable);

        canvas.draw(superimposed, x, y);
        canvas.free();

        return FuncControlFlow.cont();
    }

    @Override
    protected String funcName() {
        return ScriptVisitor.DRAW;
    }
}
