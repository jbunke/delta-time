package com.jordanbunke.delta_time.scripting.ast.nodes.expression.std_lib.scoped;

import com.jordanbunke.delta_time.scripting.ast.collection.ScriptArray;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.std_lib.MemberFuncCallNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.ScriptVisitor;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

import java.util.Arrays;

import static com.jordanbunke.delta_time.scripting.util.Arguments.argsOf;
import static com.jordanbunke.delta_time.scripting.util.TypeUtils.expectExact;

public class SplitStringNode extends MemberFuncCallNode {
    public SplitStringNode(
            final TextPosition position,
            final ExpressionNode receiver,
            final ExpressionNode regex
    ) {
        super(position, receiver, TypeNode.getString(),
                TypeNode.arrayOf(TypeNode.getString()), argsOf(regex),
                expectExact(TypeNode.getString()));
    }

    @Override
    public ScriptArray evaluate(final SymbolTable symbolTable) {
        final String regex = (String) arguments.evaluate(symbolTable)[0],
                s = (String) receiver.evaluate(symbolTable);

        return new ScriptArray(Arrays.stream(s.split(regex)));
    }

    @Override
    protected String funcName() {
        return ScriptVisitor.SPLIT;
    }
}
