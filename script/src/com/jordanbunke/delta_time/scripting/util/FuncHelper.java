package com.jordanbunke.delta_time.scripting.util;

import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.collection_init.ExplicitCollectionInitNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.collection_init.ExplicitMapInitNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.function.LambdaExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.function.ChildFuncNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.function.HelperFuncNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.function.LambdaFuncNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.CollectionTypeNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.FuncTypeNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.MapTypeNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;

import java.util.Arrays;

public final class FuncHelper {
    public static Object evaluate(
            final ChildFuncNode func,
            final ExpressionNode[] args,
            final SymbolTable symbolTable
    ) {
        final SymbolTable funcTable = getScopeTable(func, symbolTable);

        final Object[] argVals = Arrays.stream(args)
                .map(a -> a.evaluate(symbolTable)).toArray(Object[]::new);

        return func.execute(funcTable, argVals);
    }

    public static SymbolTable getScopeTable(
            final ChildFuncNode func, final SymbolTable symbolTable
    ) {
        if (func instanceof HelperFuncNode)
            return symbolTable.getRoot().getChild(func);
        else if (func instanceof LambdaFuncNode l && l.getScope() != null)
            return l.getScope();
        else
            return new SymbolTable(func, symbolTable);
    }

    public static void execute(
            final ChildFuncNode func,
            final ExpressionNode[] args,
            final SymbolTable symbolTable
    ) {
        evaluate(func, args, symbolTable);
    }

    public static void deepLambdaTypeDefinitions(
            final TypeNode lhsType, final ExpressionNode rhs
    ) {
        if (lhsType instanceof FuncTypeNode fType &&
                rhs instanceof LambdaExpressionNode l) {
            final TypeNode returnType = fType.getReturnType();
            final TypeNode[] paramTypes = fType.getParamTypes();

            l.f.setTypes(paramTypes, returnType);
        } else if (lhsType instanceof CollectionTypeNode cType &&
                rhs instanceof ExplicitCollectionInitNode ec) {
            final TypeNode elementType = cType.getElementType();

            for (ExpressionNode element : ec.getInitElements())
                deepLambdaTypeDefinitions(elementType, element);
        } else if (lhsType instanceof MapTypeNode mType &&
                rhs instanceof ExplicitMapInitNode em) {
            final TypeNode keyType = mType.getKeyType(),
                    valType = mType.getValueType();

            for (ExpressionNode key : em.getKeys())
                deepLambdaTypeDefinitions(keyType, key);

            for (ExpressionNode val : em.getVals())
                deepLambdaTypeDefinitions(valType, val);
        }
    }
}
