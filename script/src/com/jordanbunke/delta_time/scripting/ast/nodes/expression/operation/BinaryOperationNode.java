package com.jordanbunke.delta_time.scripting.ast.nodes.expression.operation;

import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.BaseTypeNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.ScriptErrorLog;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

import java.util.Set;

public final class BinaryOperationNode extends ExpressionNode {
    private enum Operator {
        ADD, SUBTRACT,
        MULTIPLY, DIVIDE, MODULO,
        RAISE,
        EQUAL, NOT_EQUAL, GT, LT, GEQ, LEQ,
        OR, AND;

        private static Operator fromString(final String s) {
            return switch (s) {
                case "+" -> ADD;
                case "-" -> SUBTRACT;
                case "*" -> MULTIPLY;
                case "/" -> DIVIDE;
                case "%" -> MODULO;
                case "^" -> RAISE;
                case "==" -> EQUAL;
                case "!=" -> NOT_EQUAL;
                case ">" -> GT;
                case "<" -> LT;
                case ">=" -> GEQ;
                case "<=" -> LEQ;
                case "||" -> OR;
                default -> AND;
            };
        }

        private boolean isLogic() {
            return ordinal() >= EQUAL.ordinal();
        }

        private boolean isDiv() {
            return this == DIVIDE || this == MODULO;
        }

        @Override
        public String toString() {
            return switch (this) {
                case ADD -> "+";
                case SUBTRACT -> "-";
                case MULTIPLY -> "*";
                case DIVIDE -> "/";
                case MODULO -> "%";
                case RAISE -> "^";
                case EQUAL -> "==";
                case NOT_EQUAL -> "!=";
                case GT -> ">";
                case LT -> "<";
                case GEQ -> ">=";
                case LEQ -> "<=";
                case OR -> "||";
                case AND -> "&&";
            };
        }
    }

    private final Operator operator;
    private final ExpressionNode o1, o2;

    public BinaryOperationNode(
            final TextPosition position,
            final String operatorString,
            final ExpressionNode o1, final ExpressionNode o2
    ) {
        super(position);

        operator = Operator.fromString(operatorString);

        this.o1 = o1;
        this.o2 = o2;
    }

    @Override
    public void semanticErrorCheck(final SymbolTable symbolTable) {
        o1.semanticErrorCheck(symbolTable);
        o2.semanticErrorCheck(symbolTable);

        final TypeNode o1Type = o1.getType(symbolTable),
                o2Type = o2.getType(symbolTable);

        switch (operator) {
            case AND, OR -> {
                final BaseTypeNode boolType = TypeNode.getBool();

                if (!o1Type.equals(boolType))
                    ScriptErrorLog.fireError(
                            ScriptErrorLog.Message.OPERAND_NOT_BOOL,
                            o1.getPosition(), o1Type.toString());
                if (!o2Type.equals(boolType))
                    ScriptErrorLog.fireError(
                            ScriptErrorLog.Message.OPERAND_NOT_BOOL,
                            o2.getPosition(), o2Type.toString());
            }
            case ADD -> {
                if (!(o1Type instanceof BaseTypeNode))
                    ScriptErrorLog.fireError(
                            ScriptErrorLog.Message.INVALID_OPERAND_PLUS,
                            o1.getPosition(), o1Type.toString());
                if (!(o2Type instanceof BaseTypeNode))
                    ScriptErrorLog.fireError(
                            ScriptErrorLog.Message.INVALID_OPERAND_PLUS,
                            o2.getPosition(), o2Type.toString());
            }
            case SUBTRACT, MULTIPLY, DIVIDE, MODULO, RAISE,
                    GT, LT, GEQ, LEQ -> {
                final Set<TypeNode> acceptedTypes = TypeNode.numTypes();

                if (!acceptedTypes.contains(o1Type))
                    ScriptErrorLog.fireError(
                            ScriptErrorLog.Message.OPERAND_NAN_SEM,
                            o1.getPosition(), o1Type.toString());
                if (!acceptedTypes.contains(o2Type))
                    ScriptErrorLog.fireError(
                            ScriptErrorLog.Message.OPERAND_NAN_SEM,
                            o2.getPosition(), o2Type.toString());
            }
        }
    }

    @Override
    public Object evaluate(final SymbolTable symbolTable) {
        final Object o1Value = o1.evaluate(symbolTable),
                o2Value = o2.evaluate(symbolTable);

        return switch (operator) {
            case AND, OR -> {
                final boolean b1 = (Boolean) o1Value, b2 = (Boolean) o2Value;

                yield operator == Operator.AND ? b1 && b2 : b1 || b2;
            }
            case GT, LT, GEQ, LEQ -> {
                final double
                        n1 = ((Number) o1Value).doubleValue(),
                        n2 = ((Number) o2Value).doubleValue();

                yield switch (operator) {
                    case GT -> n1 > n2;
                    case LT -> n1 < n2;
                    case GEQ -> n1 >= n2;
                    default -> n1 <= n2;
                };
            }
            case EQUAL, NOT_EQUAL -> {
                final boolean equal = o1Value.equals(o2Value);

                yield (operator == Operator.EQUAL) == equal;
            }
            case ADD, SUBTRACT, MULTIPLY, DIVIDE, MODULO, RAISE -> {
                if (operator == Operator.ADD &&
                        o1Value instanceof String s1 &&
                        o2Value instanceof String s2)
                    yield s1 + s2;

                final BaseTypeNode
                        intType = TypeNode.getInt();
                final boolean bothInts =
                        o1.getType(symbolTable).equals(intType) &&
                                o2.getType(symbolTable).equals(intType);

                if (o1Value instanceof Number n1 &&
                        o2Value instanceof Number n2) {
                    final double d1 = n1.doubleValue(),
                            d2 = n2.doubleValue();

                    if (d2 == 0d && operator.isDiv())
                        ScriptErrorLog.fireError(
                                ScriptErrorLog.Message.DIV_BY_ZERO,
                                o2.getPosition());

                    final Double result = switch (operator) {
                        case ADD -> d1 + d2;
                        case SUBTRACT -> d1 - d2;
                        case MULTIPLY -> d1 * d2;
                        case DIVIDE -> d1 / d2;
                        case MODULO -> d1 % d2;
                        default -> Math.pow(d1, d2);
                    };

                    if (bothInts)
                        yield result.intValue();

                    yield result;
                }

                if (operator == Operator.ADD)
                    yield String.valueOf(o1Value) + o2Value;

                yield null;
            }
        };
    }

    @Override
    public TypeNode getType(final SymbolTable symbolTable) {
        if (operator.isLogic())
            return TypeNode.getBool();

        final TypeNode o1Type = o1.getType(symbolTable),
                o2Type = o2.getType(symbolTable);

        final boolean bothNums = o1Type.isNum() && o2Type.isNum();

        if (bothNums) {
            final BaseTypeNode floatType = TypeNode.getFloat();

            if (o1Type.equals(floatType) || o2Type.equals(floatType))
                return floatType;

            return TypeNode.getInt();
        } else if (operator == Operator.ADD)
            return TypeNode.getString();

        return TypeNode.wildcard();
    }

    @Override
    public String toString() {
        return o1 + " " + operator.toString() + " " + o2;
    }
}
