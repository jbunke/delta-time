package com.jordanbunke.delta_time.scripting.ast.nodes.function;

import com.jordanbunke.delta_time.scripting.ast.nodes.types.FuncTypeNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.FlexParamFunc;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public final class DynamicFuncNode<R> extends ChildFuncNode {
    private final TypeNode[] paramTypes;
    private final TypeNode returnType;
    private final FlexParamFunc<R> func;

    public DynamicFuncNode(
            final TypeNode[] paramTypes, final TypeNode returnType,
            final FlexParamFunc<R> func
    ) {
        super(TextPosition.N_A, null, null);

        this.paramTypes = paramTypes;
        this.returnType = returnType;
        this.func = func;
    }

    @Override
    public R execute(final SymbolTable symbolTable, final Object... args) {
        return func.apply(args);
    }

    @Override
    public FuncTypeNode getType() {
        return new FuncTypeNode(paramTypes, returnType);
    }

    @Override
    public TypeNode getReturnType() {
        return returnType;
    }

    @Override
    public boolean paramsMatch(final TypeNode... spec) {
        if (paramTypes.length != spec.length)
            return false;

        for (int i = 0; i < paramTypes.length; i++)
            if (!paramTypes[i].equals(spec[i]))
                return false;

        return true;
    }
}
