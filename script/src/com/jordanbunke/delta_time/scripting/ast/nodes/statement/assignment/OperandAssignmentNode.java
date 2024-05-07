package com.jordanbunke.delta_time.scripting.ast.nodes.statement.assignment;

import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.assignable.AssignableNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.BaseTypeNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.FuncControlFlow;
import com.jordanbunke.delta_time.scripting.util.ScriptErrorLog;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

import java.util.Set;

public final class OperandAssignmentNode extends AssignmentNode {
    public enum Operator {
        ADD, SUBTRACT, MULTIPLY, DIVIDE, MODULO, AND, OR;

        private boolean isDiv() {
            return this == DIVIDE || this == MODULO;
        }

        private boolean isLogic() {
            return this == AND || this == OR;
        }

        public String toString() {
            return " " + switch (this) {
                case MODULO -> "%";
                case DIVIDE -> "/";
                case MULTIPLY -> "*";
                case SUBTRACT -> "-";
                case ADD -> "+";
                case AND -> "&";
                case OR -> "|";
            } + "= ";
        }
    }

    private final Operator operator;
    private final ExpressionNode operand;

    public OperandAssignmentNode(
            final TextPosition position,
            final AssignableNode assignable,
            final Operator operator,
            final ExpressionNode operand
    ) {
        super(position, assignable);

        this.operator = operator;
        this.operand = operand;
    }

    @Override
    public void semanticErrorCheck(final SymbolTable symbolTable) {
        super.semanticErrorCheck(symbolTable);

        final TypeNode
                assignableType = getAssignable().getType(symbolTable),
                operandType = operand.getType(symbolTable);
        final BaseTypeNode intType = TypeNode.getInt(),
                boolType = TypeNode.getBool(),
                floatType = TypeNode.getFloat(),
                charType = TypeNode.getChar(),
                stringType = TypeNode.getString();

        final Set<TypeNode> numTypes = Set.of(intType, floatType),
                textTypes = Set.of(stringType, charType);

        if (operator.isLogic()) {
            if (!assignableType.equals(boolType))
                ScriptErrorLog.fireError(
                        ScriptErrorLog.Message.VAR_NOT_BOOL,
                        getAssignable().getPosition(),
                        assignableType.toString());
            if (!operandType.equals(boolType))
                ScriptErrorLog.fireError(
                        ScriptErrorLog.Message.VAR_NOT_BOOL,
                        operand.getPosition(),
                        operandType.toString());
        } else if (operator == Operator.ADD) {
            if (numTypes.contains(assignableType)) {
                if (!numTypes.contains(operandType))
                    ScriptErrorLog.fireError(
                            ScriptErrorLog.Message.ASSIGN_EXPR_NOT_NUM,
                            operand.getPosition(),
                            operandType.toString());
            } else if (assignableType.equals(stringType)) {
                if (!textTypes.contains(operandType))
                    ScriptErrorLog.fireError(
                            ScriptErrorLog.Message.ASSIGN_EXPR_NOT_STRING,
                            operand.getPosition(),
                            operandType.toString());
            } else {
                ScriptErrorLog.fireError(
                        ScriptErrorLog.Message.VAR_NOT_NUM,
                        getAssignable().getPosition(),
                        assignableType.toString());
            }
        } else {
            if (!numTypes.contains(assignableType))
                ScriptErrorLog.fireError(
                        ScriptErrorLog.Message.VAR_NOT_NUM,
                        getAssignable().getPosition(),
                        assignableType.toString());
            if (!numTypes.contains(operandType))
                ScriptErrorLog.fireError(
                        ScriptErrorLog.Message.ASSIGN_EXPR_NOT_NUM,
                        operand.getPosition(),
                        operandType.toString());
        }
    }

    @Override
    public FuncControlFlow execute(final SymbolTable symbolTable) {
        final Object opValue = operand.evaluate(symbolTable);

        final Object
                before = getAssignable().evaluate(symbolTable),
                after = switch (operator) {
                    case OR -> (Boolean) before ||
                            (Boolean) operand.evaluate(symbolTable);
                    case AND -> (Boolean) before &&
                            (Boolean) operand.evaluate(symbolTable);
                    case ADD, SUBTRACT, MULTIPLY, DIVIDE, MODULO -> {
                        if (operator == Operator.ADD &&
                                before instanceof String bs) {
                            final String os = String.valueOf(opValue);

                            yield bs + os;
                        }

                        final TypeNode type = getAssignable()
                                .getType(symbolTable);
                        final boolean isInt = type.equals(TypeNode.getInt());

                        if (before instanceof Number bn &&
                                opValue instanceof Number on) {
                            final double bef = bn.doubleValue(),
                                    op = on.doubleValue();

                            if (op == 0d && operator.isDiv())
                                ScriptErrorLog.fireError(
                                        ScriptErrorLog.Message.DIV_BY_ZERO,
                                        operand.getPosition());

                            final Double res = switch (operator) {
                                case ADD -> bef + op;
                                case SUBTRACT -> bef - op;
                                case MULTIPLY -> bef * op;
                                case DIVIDE -> bef / op;
                                case MODULO -> bef % op;
                                default -> bef;
                            };

                            if (isInt)
                                yield res.intValue();

                            yield res;
                        }

                        yield before;
                    }
                };
        getAssignable().update(symbolTable, after);

        return FuncControlFlow.cont();
    }

    @Override
    public String toString() {
        return getAssignable() + operator.toString() + operand + ";";
    }
}
