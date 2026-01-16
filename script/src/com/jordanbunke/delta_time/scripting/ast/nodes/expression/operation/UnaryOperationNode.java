package com.jordanbunke.delta_time.scripting.ast.nodes.expression.operation;

import com.jordanbunke.delta_time.scripting.ast.collection.ScriptCollection;
import com.jordanbunke.delta_time.scripting.ast.collection.ScriptMap;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.MapTypeNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.TextPosition;
import com.jordanbunke.delta_time.scripting.util.TypeUtils;

import static com.jordanbunke.delta_time.scripting.util.ScriptErrorLog.*;

import java.util.Set;

public final class UnaryOperationNode extends ExpressionNode {
    private enum Operator {
        NEGATE, NOT, SIZE;

        private static Operator fromString(final String s) {
            return switch (s) {
                case "-" -> NEGATE;
                case "!" -> NOT;
                default -> SIZE;
            };
        }

        @Override
        public String toString() {
            return switch (this) {
                case SIZE -> "#|";
                case NEGATE -> "-";
                case NOT -> "!";
            };
        }
    }

    private final Operator operator;
    private final ExpressionNode operand;

    public UnaryOperationNode(
            final TextPosition position,
            final String operatorString,
            final ExpressionNode operand
    ) {
        super(position);

        operator = Operator.fromString(operatorString);
        this.operand = operand;
    }

    @Override
    public void semanticErrorCheck(final SymbolTable symbolTable) {
        operand.semanticErrorCheck(symbolTable);

        final TypeNode operandType = operand.getType(symbolTable);

        switch (operator) {
            case NOT -> {
                if (!operandType.equals(
                        TypeNode.getBool()))
                    semanticError(operand.getPosition(),
                            notBool("Logical negation (" +
                                    operator + ") operand", operandType));
            }
            case NEGATE -> {
                final Set<TypeNode> acceptedTypes = TypeNode.numTypes();

                if (!acceptedTypes.contains(operandType))
                    semanticError(operand.getPosition(),
                            "Arithmetic negation (" + operator +
                                    ") operand is of a non-numeric type: " +
                                    expectedNumberButGot(operandType));
            }
            case SIZE -> {
                if (!operandType.hasSize())
                    semanticError(operand.getPosition(),
                            "Cannot evaluate the length/size (" + operator +
                                    ") of an operand of this type: " +
                                    expectedButGot(TypeUtils.options(
                                            TypeNode.array(), TypeNode.list(),
                                            TypeNode.set(), new MapTypeNode(),
                                            TypeNode.getString()), operandType));
            }
        }
    }

    @Override
    public Object evaluate(final SymbolTable symbolTable) {
        final Object operandValue = operand.evaluate(symbolTable);

        return switch (operator) {
            case NOT -> !((Boolean) operandValue);
            case SIZE -> {
                if (operandValue instanceof ScriptCollection c)
                    yield c.size();
                else if (operandValue instanceof String s)
                    yield s.length();
                else if (operandValue instanceof ScriptMap m)
                    yield m.size();

                runtimeError(operand.getPosition(),
                        "Could not evaluate the length/size of the operand " +
                                "because the operand was not evaluated to a collection");
                yield null;
            }
            case NEGATE -> {
                if (operandValue instanceof Integer i)
                    yield -i;
                else if (operandValue instanceof Double f)
                    yield -f;
                else if (operandValue instanceof Float f)
                    yield -f;
                else {
                    runtimeError(operand.getPosition(),
                            "Operand could not be arithmetically negated " +
                                    "because the operand was not evaluated to a number");
                    yield null;
                }
            }
        };
    }

    @Override
    public TypeNode getType(final SymbolTable symbolTable) {
        if (operator == Operator.SIZE)
            return TypeNode.getInt();

        return operand.getType(symbolTable);
    }

    @Override
    public String toString() {
        return operator.toString() + operand;
    }
}
