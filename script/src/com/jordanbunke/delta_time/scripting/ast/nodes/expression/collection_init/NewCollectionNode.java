package com.jordanbunke.delta_time.scripting.ast.nodes.expression.collection_init;

import com.jordanbunke.delta_time.scripting.ast.collection.ScriptCollection;
import com.jordanbunke.delta_time.scripting.ast.collection.ScriptList;
import com.jordanbunke.delta_time.scripting.ast.collection.ScriptSet;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.CollectionTypeNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public final class NewCollectionNode extends ExpressionNode {
    private final CollectionTypeNode.Type collectionType;
    private final TypeNode elementType;

    public NewCollectionNode(
            final TextPosition position,
            final CollectionTypeNode.Type collectionType,
            final TypeNode elementType
    ) {
        super(position);

        this.collectionType = collectionType;
        this.elementType = elementType;
    }

    @Override
    public void semanticErrorCheck(final SymbolTable symbolTable) {}

    @Override
    public ScriptCollection evaluate(final SymbolTable symbolTable) {
        if (collectionType == CollectionTypeNode.Type.SET)
            return new ScriptSet();

        return new ScriptList();
    }

    @Override
    public TypeNode getType(final SymbolTable symbolTable) {
        return new CollectionTypeNode(collectionType, elementType);
    }

    @Override
    public String toString() {
        return "new " + elementType +
                (collectionType == CollectionTypeNode.Type.SET ? "{}" : "<>");
    }
}
