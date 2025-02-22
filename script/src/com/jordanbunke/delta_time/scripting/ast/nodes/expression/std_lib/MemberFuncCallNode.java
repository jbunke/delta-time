package com.jordanbunke.delta_time.scripting.ast.nodes.expression.std_lib;

import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.Arguments;
import com.jordanbunke.delta_time.scripting.util.Receiver;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public abstract class MemberFuncCallNode extends DefFuncCallNode {
    protected final Receiver receiver;

    public MemberFuncCallNode(
            final TextPosition position,
            final ExpressionNode receiver, final TypeNode expectedReceiver,
            final TypeNode returnType,
            final ExpressionNode[] args, final TypeNode... expectedArgs
    ) {
        super(new Arguments(args, expectedArgs), returnType, position);

        this.receiver = new Receiver(receiver, expectedReceiver);
    }

    @Override
    public void semanticErrorCheck(final SymbolTable symbolTable) {
        super.semanticErrorCheck(symbolTable);
        receiver.semanticErrorCheck(symbolTable, getPosition());
    }

    @Override
    public String toString() {
        return receiver + super.toString();
    }
}
