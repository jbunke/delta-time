package com.jordanbunke.delta_time.scripting.ast.nodes.expression.std_lib.global.min_max;

import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.ScriptVisitor;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

import static com.jordanbunke.delta_time.scripting.util.ScriptErrorLog.*;

// TODO - refactor: should extend DefFuncCallNode
public final class AbsoluteNode extends ExpressionNode {
    private final ExpressionNode n;

    public AbsoluteNode(
            final TextPosition position,
            final ExpressionNode n
    ) {
        super(position);

        this.n = n;
    }

    @Override
    public void semanticErrorCheck(final SymbolTable symbolTable) {
        n.semanticErrorCheck(symbolTable);

        final TypeNode nType = n.getType(symbolTable);

        if (!nType.isNum())
            semanticError(n.getPosition(),
                    ScriptVisitor.ABS + "() argument is of a non-numeric type: " +
                            expectedNumberButGot(nType));
    }

    @Override
    public Number evaluate(final SymbolTable symbolTable) {
        final Object val = n.evaluate(symbolTable);

        if (val instanceof Integer i)
            return Math.abs(i);
        else if (val instanceof Double d)
            return Math.abs(d);

        return null;
    }

    @Override
    public TypeNode getType(final SymbolTable symbolTable) {
        return n.getType(symbolTable);
    }

    @Override
    public String toString() {
        return ScriptVisitor.ABS + "(" + n + ")";
    }
}
