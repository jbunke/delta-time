package com.jordanbunke.delta_time.scripting.ast.nodes.expression;

import com.jordanbunke.delta_time.scripting.ast.nodes.types.BaseTypeNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

import static com.jordanbunke.delta_time.scripting.util.ScriptErrorLog.*;

import java.util.Map;
import java.util.Set;

public final class CastNode extends ExpressionNode {
    private static final Map<TypeNode, Set<TypeNode>>
            CAST_MATRIX = Map.ofEntries(
                    Map.entry(TypeNode.getInt(), Set.of(
                            TypeNode.getInt(), TypeNode.getFloat(),
                            TypeNode.getChar(), TypeNode.getString())),
                    Map.entry(TypeNode.getFloat(), TypeNode.numTypes()),
                    Map.entry(TypeNode.getChar(), Set.of(TypeNode.getInt())),
                    Map.entry(TypeNode.getString(), Set.of(
                            TypeNode.getChar(), TypeNode.getBool(),
                            TypeNode.getInt(), TypeNode.getFloat())));

    /*
        {
            int : { int, float, char },
            float : { int, float },
            char : { int },
            string : { char, bool, int, float }
        }
    */

    private final TypeNode type;
    private final ExpressionNode e;

    public CastNode(
            final TextPosition position,
            final TypeNode type, final ExpressionNode e
    ) {
        super(position);

        this.type = type;
        this.e = e;
    }

    @Override
    public void semanticErrorCheck(final SymbolTable symbolTable) {
        type.semanticErrorCheck(symbolTable);
        e.semanticErrorCheck(symbolTable);

        final TypeNode eType = e.getType(symbolTable);

        if (!TypeNode.getString().equals(type)) {
            if (!CAST_MATRIX.containsKey(type))
                semanticError(type.getPosition(), "Cannot cast to type \"" + type + "\"");
            else if (!CAST_MATRIX.get(type).contains(eType))
                semanticError(e.getPosition(), "Cannot cast type \"" + eType + "\" to type \"" + type + "\"");
        }
    }

    @Override
    public Object evaluate(final SymbolTable symbolTable) {
        final Object val = e.evaluate(symbolTable);

        if (type instanceof BaseTypeNode baseType) {
            return switch (baseType.getType()) {
                case INT -> {
                    if (val instanceof Double d)
                        yield d.intValue();
                    else if (val instanceof Character c)
                        yield (int) c;
                    else if (val instanceof String s) {
                        try {
                            yield Integer.parseInt(s);
                        } catch (NumberFormatException e) {
                            runtimeError(this.e.getPosition(), "Attempted to cast the string \"" + s + "\" as an int");
                        }
                    }

                    yield null;
                }
                case FLOAT -> {
                    if (val instanceof Integer i)
                        yield i.doubleValue();

                    yield failedToCast();
                }
                case CHAR -> {
                    if (val instanceof Integer i)
                        yield (char) ((int) i);

                    yield failedToCast();
                }
                case STRING -> String.valueOf(val);
                default -> failedToCast();
            };
        }

        return failedToCast();
    }

    private Object failedToCast() {
        runtimeError(e.getPosition(), "Could not cast object to type \"" + type + "\"");
        return null;
    }

    @Override
    public TypeNode getType(final SymbolTable symbolTable) {
        return type;
    }

    @Override
    public String toString() {
        return "(" + type + ") " + e;
    }
}
