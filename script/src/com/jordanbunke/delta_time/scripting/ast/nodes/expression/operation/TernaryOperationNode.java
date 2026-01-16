package com.jordanbunke.delta_time.scripting.ast.nodes.expression.operation;

import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.BaseTypeNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

import static com.jordanbunke.delta_time.scripting.util.ScriptErrorLog.*;

public final class TernaryOperationNode extends ExpressionNode {
    private final ExpressionNode condition, a, b;

    public TernaryOperationNode(
            final TextPosition position,
            final ExpressionNode condition,
            final ExpressionNode a,
            final ExpressionNode b
    ) {
        super(position);

        this.condition = condition;
        this.a = a;
        this.b = b;
    }

    @Override
    public void semanticErrorCheck(final SymbolTable symbolTable) {
        condition.semanticErrorCheck(symbolTable);
        a.semanticErrorCheck(symbolTable);
        b.semanticErrorCheck(symbolTable);

        final TypeNode
                cType = condition.getType(symbolTable),
                aType = a.getType(symbolTable),
                bType = b.getType(symbolTable);
        final BaseTypeNode boolType = TypeNode.getBool();

        if (!cType.equals(boolType))
            semanticError(condition.getPosition(),
                    notBool("Ternary expression condition", cType));
        if (!aType.equals(bType))
            semanticError(getPosition(),
                    typeMismatch("Ternary expression false branch type",
                            "true branch type", aType, bType));
    }

    @Override
    public Object evaluate(final SymbolTable symbolTable) {
        return (Boolean) condition.evaluate(symbolTable)
                ? a.evaluate(symbolTable)
                : b.evaluate(symbolTable);
    }

    @Override
    public TypeNode getType(final SymbolTable symbolTable) {
        return a.getType(symbolTable);
    }

    @Override
    public String toString() {
        return condition + " ? " + a + " : " + b;
    }
}
