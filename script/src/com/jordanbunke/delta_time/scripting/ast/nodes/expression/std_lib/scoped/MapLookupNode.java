package com.jordanbunke.delta_time.scripting.ast.nodes.expression.std_lib.scoped;

import com.jordanbunke.delta_time.scripting.ast.collection.ScriptMap;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.std_lib.MemberFuncCallNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.MapTypeNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.ScriptErrorLog;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

import static com.jordanbunke.delta_time.scripting.util.Arguments.argsOf;
import static com.jordanbunke.delta_time.scripting.util.TypeUtils.expectExact;

public final class MapLookupNode extends MemberFuncCallNode {
    private final ExpressionNode element;

    public MapLookupNode(
            final TextPosition position,
            final ExpressionNode receiver,
            final ExpressionNode element
    ) {
        super(position, receiver, new MapTypeNode(), TypeNode.wildcard(),
                argsOf(element), expectExact(TypeNode.wildcard()));

        this.element = element;
    }

    @Override
    public void semanticErrorCheck(final SymbolTable symbolTable) {
        super.semanticErrorCheck(symbolTable);

        final TypeNode receiverType = receiver.getType(symbolTable),
                elemType = element.getType(symbolTable);

        if (receiverType instanceof MapTypeNode mapType) {
            final TypeNode keyType = mapType.getKeyType();

            if (!keyType.equals(elemType))
                ScriptErrorLog.fireError(
                        ScriptErrorLog.Message.MAP_KEY_TYPE_MISMATCH,
                        element.getPosition(), elemType.toString());
        }
    }

    @Override
    public Object evaluate(final SymbolTable symbolTable) {
        final Object rec = receiver.evaluate(symbolTable);
        final Object elemValue = element.evaluate(symbolTable);

        if (rec instanceof ScriptMap map) {
            if (map.containsKey(elemValue))
                return map.get(elemValue);
            else
                ScriptErrorLog.fireError(
                        ScriptErrorLog.Message.MAP_DOES_NOT_CONTAIN_ELEMENT,
                        element.getPosition(), elemValue.toString());
        }

        return null;
    }

    @Override
    public TypeNode getType(final SymbolTable symbolTable) {
        return ((MapTypeNode) receiver.getType(symbolTable)).getValueType();
    }

    @Override
    protected String funcName() {
        return "lookup";
    }
}
