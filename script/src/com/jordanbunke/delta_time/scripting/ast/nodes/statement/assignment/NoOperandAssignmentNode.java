package com.jordanbunke.delta_time.scripting.ast.nodes.statement.assignment;

import com.jordanbunke.delta_time.scripting.ast.nodes.expression.assignable.AssignableNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.BaseTypeNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.FuncControlFlow;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

import static com.jordanbunke.delta_time.scripting.util.ScriptErrorLog.*;

public final class NoOperandAssignmentNode extends AssignmentNode {
    private final boolean increment;

    public NoOperandAssignmentNode(
            final TextPosition position,
            final AssignableNode assignable,
            final boolean increment
    ) {
        super(position, assignable);

        this.increment = increment;
    }

    @Override
    public void semanticErrorCheck(final SymbolTable symbolTable) {
        super.semanticErrorCheck(symbolTable);

        final TypeNode assignableType = getAssignable().getType(symbolTable);
        final BaseTypeNode
                intType = TypeNode.getInt();

        if (!assignableType.equals(intType))
            semanticError(getAssignable().getPosition(),
                    "Assignable is of an invalid type for a " +
                            (increment ? "in" : "de") + "crementation operation: " +
                            expectedButGot(intType, assignableType));
    }

    @Override
    public FuncControlFlow execute(final SymbolTable symbolTable) {
        final int value = (int) getAssignable().evaluate(symbolTable);
        getAssignable().update(symbolTable, value + (increment ? 1 : -1));

        return FuncControlFlow.cont();
    }

    @Override
    public String toString() {
        return getAssignable() + (increment ? "++" : "--") + ";";
    }
}
