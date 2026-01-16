package com.jordanbunke.delta_time.scripting.ast.nodes.expression.std_lib.scoped;

import com.jordanbunke.delta_time.scripting.ast.collection.ScriptMap;
import com.jordanbunke.delta_time.scripting.ast.collection.ScriptSet;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.std_lib.MemberFuncCallNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.MapTypeNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.Arguments;
import com.jordanbunke.delta_time.scripting.util.ScriptVisitor;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public final class MapKeysetNode extends MemberFuncCallNode {
    public MapKeysetNode(
            final TextPosition position,
            final ExpressionNode receiver
    ) {
        super(position, receiver, new MapTypeNode(),
                TypeNode.set(), Arguments.argsOf());
    }

    @Override
    public ScriptSet evaluate(final SymbolTable symbolTable) {
        return new ScriptSet(((ScriptMap) receiver.evaluate(symbolTable))
                .keySet().stream());
    }

    @Override
    public TypeNode getType(final SymbolTable symbolTable) {
        final MapTypeNode mtn = (MapTypeNode) receiver.getType(symbolTable);

        return TypeNode.setOf(mtn.getKeyType());
    }

    @Override
    protected String funcName() {
        return ScriptVisitor.KEYS;
    }
}
