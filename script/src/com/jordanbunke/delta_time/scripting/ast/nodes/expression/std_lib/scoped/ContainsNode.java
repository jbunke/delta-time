package com.jordanbunke.delta_time.scripting.ast.nodes.expression.std_lib.scoped;

import com.jordanbunke.delta_time.scripting.ast.collection.ScriptCollection;
import com.jordanbunke.delta_time.scripting.ast.collection.ScriptMap;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.std_lib.MemberFuncCallNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.CollectionTypeNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.MapTypeNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.*;

public final class ContainsNode extends MemberFuncCallNode {
    public ContainsNode(
            final TextPosition position,
            final ExpressionNode receiver,
            final ExpressionNode element
    ) {
        super(position, new Receiver(receiver,
                TypeUtils.options(new MapTypeNode(), TypeNode.set(),
                        TypeNode.list(), TypeNode.array(),
                        TypeNode.getString())),
                TypeNode.getBool(), Arguments.argsOf(element),
                TypeUtils.expectExact(TypeNode.wildcard()));
    }

    @Override
    public void semanticErrorCheck(final SymbolTable symbolTable) {
        super.semanticErrorCheck(symbolTable);

        final ExpressionNode element = arguments.get(0);

        final TypeNode recType = receiver.getType(symbolTable),
                elemType = element.getType(symbolTable);

        if (recType instanceof MapTypeNode mapType) {
            final TypeNode keyType = mapType.getKeyType();

            if (!keyType.equals(elemType))
                ScriptErrorLog.fireError(
                        ScriptErrorLog.Message.MAP_KEY_TYPE_MISMATCH,
                        element.getPosition(),
                        keyType.toString(), elemType.toString());
        } else if (recType instanceof CollectionTypeNode colType) {
            final TypeNode colElemType = colType.getElementType();

            if (!colElemType.equals(elemType))
                ScriptErrorLog.fireError(
                        ScriptErrorLog.Message.ELEMENT_DOES_NOT_MATCH_COL,
                        element.getPosition(),
                        colElemType.toString(), elemType.toString());
        } else if (recType.equals(TypeNode.getString())) {
            if (!(elemType.equals(TypeNode.getChar()) ||
                    elemType.equals(TypeNode.getString())))
                ScriptErrorLog.fireError(
                        ScriptErrorLog.Message.EXPECTED_FOR_CALL,
                        getPosition(), funcName(), "\"char\" or \"string\"",
                        elemType.toString());
        }
    }

    @Override
    public Boolean evaluate(final SymbolTable symbolTable) {
        final Object rec = receiver.evaluate(symbolTable);
        final Object elemValue = arguments.get(0).evaluate(symbolTable);

        if (rec instanceof ScriptMap map)
            return map.containsKey(elemValue);
        else if (rec instanceof ScriptCollection c)
            return c.contains(elemValue);
        else if (rec instanceof String s) {
            if (elemValue instanceof String sub)
                return s.contains(sub);
            else if (elemValue instanceof Character c)
                return s.indexOf(c) >= 0;
        }

        return false;
    }

    @Override
    public TypeNode getType(final SymbolTable symbolTable) {
        return TypeNode.getBool();
    }

    @Override
    protected String funcName() {
        return "has";
    }
}
