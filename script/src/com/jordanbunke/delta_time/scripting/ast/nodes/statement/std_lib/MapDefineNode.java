package com.jordanbunke.delta_time.scripting.ast.nodes.statement.std_lib;

import com.jordanbunke.delta_time.scripting.ast.collection.ScriptMap;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.statement.StatementNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.MapTypeNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.FuncControlFlow;
import com.jordanbunke.delta_time.scripting.util.ScriptVisitor;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

import static com.jordanbunke.delta_time.scripting.util.ScriptErrorLog.*;

// TODO - refactor: should extend MemberFuncExecNode
public final class MapDefineNode extends StatementNode {
    private final ExpressionNode map, key, value;

    public MapDefineNode(
            final TextPosition position,
            final ExpressionNode map,
            final ExpressionNode key,
            final ExpressionNode value
    ) {
        super(position);

        this.map = map;
        this.key = key;
        this.value = value;
    }

    @Override
    public void semanticErrorCheck(final SymbolTable symbolTable) {
        map.semanticErrorCheck(symbolTable);
        key.semanticErrorCheck(symbolTable);
        value.semanticErrorCheck(symbolTable);

        final TypeNode
                mapType = map.getType(symbolTable),
                keyType = key.getType(symbolTable),
                valueType = value.getType(symbolTable);

        if (!(mapType instanceof MapTypeNode m))
            semanticError(map.getPosition(),
                    "define() receiver expression is of an invalid type: " +
                            expectedButGot(new MapTypeNode(), mapType));
        else if (!keyType.equals(m.getKeyType()))
            semanticError(key.getPosition(),
                    typeMismatch("define() key argument expression type",
                            "receiver map expression key type",
                            m.getKeyType(), keyType));
        else if (!valueType.equals(m.getValueType()))
            semanticError(value.getPosition(),
                    typeMismatch("define() value argument expression type",
                            "receiver map expression value type",
                            m.getValueType(), valueType));
    }

    @Override
    public FuncControlFlow execute(final SymbolTable symbolTable) {
        final ScriptMap m = (ScriptMap) map.evaluate(symbolTable);
        final Object
                k = key.evaluate(symbolTable),
                v = value.evaluate(symbolTable);

        m.put(k, v);

        return FuncControlFlow.cont();
    }

    @Override
    public String toString() {
        return map + "." + ScriptVisitor.DEFINE + "(" + key + ", " + value + ");";
    }
}
