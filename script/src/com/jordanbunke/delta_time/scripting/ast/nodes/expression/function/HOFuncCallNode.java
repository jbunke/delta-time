package com.jordanbunke.delta_time.scripting.ast.nodes.expression.function;

import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.function.ChildFuncNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.FuncTypeNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.FuncHelper;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

import static com.jordanbunke.delta_time.scripting.util.ScriptErrorLog.*;

import java.util.Arrays;

public final class HOFuncCallNode extends ExpressionNode {
    private final ExpressionNode f;
    private final ExpressionNode[] args;

    public HOFuncCallNode(
            final TextPosition position,
            final ExpressionNode f, final ExpressionNode[] args
    ) {
        super(position);

        this.f = f;
        this.args = args;
    }

    @Override
    public void semanticErrorCheck(final SymbolTable symbolTable) {
        for (ExpressionNode arg : args)
            arg.semanticErrorCheck(symbolTable);

        // untested addition
        f.semanticErrorCheck(symbolTable);

        final TypeNode fType = f.getType(symbolTable);

        if (!(fType instanceof FuncTypeNode funcType)) {
            semanticError(f.getPosition(),
                    "Cannot invoke an expression of the non-functional type \"" +
                            fType + "\" as a function");
            return;
        }

        final TypeNode[] argTypes = Arrays.stream(args)
                .map(a -> a.getType(symbolTable))
                .toArray(TypeNode[]::new);

        final TypeNode[] paramTypes = funcType.getParamTypes();

        if (paramTypes.length != argTypes.length) {
            semanticError(getPosition(), unexpectedNumberOfArgs(paramTypes.length, argTypes.length));
            return;
        }

        for (int i = 0; i < paramTypes.length; i++)
            if (!argTypes[i].equals(paramTypes[i]))
                semanticError(args[i].getPosition(), typeMismatch(
                        "Argument type", "parameter type",
                        paramTypes[i], argTypes[i]));
    }

    @Override
    public Object evaluate(final SymbolTable symbolTable) {
        final ChildFuncNode func = (ChildFuncNode) f.evaluate(symbolTable);
        assert func != null;

        return FuncHelper.evaluate(func, args, symbolTable);
    }

    @Override
    public TypeNode getType(final SymbolTable symbolTable) {
        return ((FuncTypeNode) f.getType(symbolTable)).getReturnType();
    }

    @Override
    public String toString() {
        final String contents = args.length == 1
                ? args[0].toString()
                : Arrays.stream(args)
                .map(ExpressionNode::toString)
                .reduce((a, b) -> a + ", " + b).orElse("");

        return f + ".call(" + contents + ")";
    }
}
