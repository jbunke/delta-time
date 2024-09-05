package com.jordanbunke.delta_time.scripting.ast.nodes.statement.declaration;

import com.jordanbunke.delta_time.scripting.ast.nodes.expression.assignable.IdentifierNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public sealed class ExplicitDeclarationNode extends DeclarationNode
        permits InitializationNode {
    private final TypeNode type;
    private final boolean mutable;

    public ExplicitDeclarationNode(
            final TextPosition position, final boolean mutable,
            final TypeNode type, final IdentifierNode ident
    ) {
        super(position, ident);

        this.type = type;
        this.mutable = mutable;
    }

    @Override
    public final boolean isMutable() {
        return mutable;
    }

    @Override
    public TypeNode getType() {
        return type;
    }

    @Override
    public void semanticErrorCheck(final SymbolTable symbolTable) {
        type.semanticErrorCheck(symbolTable);

        super.semanticErrorCheck(symbolTable);
    }
}
