package com.jordanbunke.delta_time.scripting.ast.nodes.expression.std_lib.global.min_max;

import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.std_lib.DefFuncCallNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.Arguments;
import com.jordanbunke.delta_time.scripting.util.ScriptErrorLog;
import com.jordanbunke.delta_time.scripting.util.TextPosition;
import com.jordanbunke.delta_time.scripting.util.TypeUtils;

import java.util.function.BinaryOperator;

public abstract class NumBinOpNode extends DefFuncCallNode {
    private static final TypeNode[] ARG_TYPE_OPTIONS;

    private final BinaryOperator<Integer> intOp;
    private final BinaryOperator<Double> floatOp;

    static {
        ARG_TYPE_OPTIONS = TypeUtils.options(
                TypeNode.getInt(), TypeNode.getFloat());
    }


    public NumBinOpNode(
            final TextPosition position,
            final ExpressionNode a, final ExpressionNode b,
            final BinaryOperator<Integer> intOp,
            final BinaryOperator<Double> floatOp
    ) {
        super(new Arguments(Arguments.argsOf(a, b),
                ARG_TYPE_OPTIONS, ARG_TYPE_OPTIONS),
                TypeNode.wildcard(), position);

        this.intOp = intOp;
        this.floatOp = floatOp;
    }

    @Override
    public final void semanticErrorCheck(final SymbolTable symbolTable) {
        final ExpressionNode a = arguments.get(0);

        final TypeNode aType = a.getType(symbolTable),
                bType = arguments.get(1).getType(symbolTable);

        if (!aType.equals(bType))
            ScriptErrorLog.fireError(ScriptErrorLog.Message.ARG_NOT_TYPE,
                    getPosition(), "Second " + funcName(),
                    aType.toString(), bType.toString());
        if (!aType.isNum())
            ScriptErrorLog.fireError(ScriptErrorLog.Message.NAN,
                    a.getPosition(), "First " + funcName() + " argument",
                    aType.toString());
    }

    @Override
    public final Number evaluate(final SymbolTable symbolTable) {
        final Object[] vals = arguments.evaluate(symbolTable);

        final Object aVal = vals[0], bVal = vals[1];

        if (aVal instanceof Integer ai && bVal instanceof Integer bi)
            return intOp.apply(ai, bi);
        else if (aVal instanceof Double ad && bVal instanceof Double bd)
            return floatOp.apply(ad, bd);

        return null;
    }

    @Override
    public final TypeNode getType(final SymbolTable symbolTable) {
        return arguments.get(0).getType(symbolTable);
    }
}
