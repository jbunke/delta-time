package com.jordanbunke.delta_time.scripting.ast.nodes.expression.function;

import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.function.HelperFuncNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.FuncTypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.FunctionHook;
import com.jordanbunke.delta_time.scripting.util.IHookable;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public final class HOFuncNode extends ExpressionNode implements IHookable {
    private final String name;

    public HOFuncNode(
            final TextPosition position, final String name
    ) {
        super(position);

        this.name = name;
    }

    @Override
    public void semanticErrorCheck(final SymbolTable symbolTable) {
        // return value is not needed, but call is needed to
        // trigger an error if func association is not found
        FunctionHook.getFunc(symbolTable, this);
    }

    @Override
    public HelperFuncNode evaluate(final SymbolTable symbolTable) {
        return FunctionHook.getFunc(symbolTable, this);
    }

    @Override
    public FuncTypeNode getType(final SymbolTable symbolTable) {
        final HelperFuncNode func = FunctionHook.getFunc(symbolTable, this);

        return func != null ? func.getType() : null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "::" + name;
    }
}
