package com.jordanbunke.delta_time.scripting.ast.nodes.expression.native_calls.scoped;

import com.jordanbunke.delta_time.scripting.ast.collection.ScriptCollection;
import com.jordanbunke.delta_time.scripting.ast.collection.ScriptMap;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.CollectionTypeNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.MapTypeNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.ScriptErrorLog;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

import java.util.Set;

public final class ContainsNode extends ScopedNativeCallNode {
    private final ExpressionNode element;

    public ContainsNode(
            final TextPosition position,
            final ExpressionNode owner,
            final ExpressionNode element
    ) {
        super(position, owner, Set.of(
                new MapTypeNode(TypeNode.wildcard(), TypeNode.wildcard()),
                TypeNode.set(), TypeNode.list(), TypeNode.array()));

        this.element = element;
    }

    @Override
    public void semanticErrorCheck(final SymbolTable symbolTable) {
        element.semanticErrorCheck(symbolTable);

        super.semanticErrorCheck(symbolTable);

        final TypeNode
                ownerType = getScope().getType(symbolTable),
                elemType = element.getType(symbolTable);

        if (ownerType instanceof MapTypeNode mapType) {
            final TypeNode keyType = mapType.getKeyType();

            if (!keyType.equals(elemType))
                ScriptErrorLog.fireError(
                        ScriptErrorLog.Message.MAP_KEY_TYPE_MISMATCH,
                        element.getPosition(),
                        keyType.toString(), elemType.toString());
        } else if (ownerType instanceof CollectionTypeNode colType) {
            final TypeNode colElemType = colType.getElementType();

            if (!colElemType.equals(elemType))
                ScriptErrorLog.fireError(
                        ScriptErrorLog.Message.ELEMENT_DOES_NOT_MATCH_COL,
                        element.getPosition(),
                        colElemType.toString(), elemType.toString());
        } else if (ownerType.equals(TypeNode.getString())) {
            if (!(elemType.equals(TypeNode.getChar()) ||
                    elemType.equals(TypeNode.getString())))
                ScriptErrorLog.fireError(
                        ScriptErrorLog.Message.EXPECTED_FOR_CALL,
                        getPosition(), callName(), "\"char\" or \"string\"",
                        elemType.toString());
        }
    }

    @Override
    public Boolean evaluate(final SymbolTable symbolTable) {
        final Object owner = getScope().evaluate(symbolTable);
        final Object elemValue = element.evaluate(symbolTable);

        if (owner instanceof ScriptMap map)
            return map.containsKey(elemValue);
        else if (owner instanceof ScriptCollection c)
            return c.contains(elemValue);
        else if (owner instanceof String s) {
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
    String callName() {
        return "has()";
    }

    @Override
    public String toString() {
        final String base = super.toString();

        return base.substring(0, base.length() - 2) + "(" + element + ")";
    }
}
