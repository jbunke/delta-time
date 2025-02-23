package com.jordanbunke.delta_time.scripting.ast.nodes.statement.std_lib;

import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.Arguments;
import com.jordanbunke.delta_time.scripting.util.Receiver;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public abstract class MemberFuncExecNode extends DefFuncExecNode {
    protected final Receiver receiver;

    public MemberFuncExecNode(
            final TextPosition position,
            final ExpressionNode receiver, final TypeNode expectedReceiver,
            final ExpressionNode[] args, final TypeNode[]... expectedArgs
    ) {
        super(new Arguments(args, expectedArgs), position);

        this.receiver = new Receiver(receiver, expectedReceiver);
    }

    @Override
    public void semanticErrorCheck(SymbolTable symbolTable) {
        super.semanticErrorCheck(symbolTable);
        receiver.semanticErrorCheck(symbolTable);
    }

    @Override
    public String toString() {
        return receiver + super.toString();
    }
}
