package com.jordanbunke.delta_time.scripting.ast.nodes.statement.declaration;

import com.jordanbunke.delta_time.scripting.ast.nodes.expression.assignable.IdentifierNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.Variable;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public final class ImplicitDeclarationNode extends DeclarationNode {
    private TypeNode type;

    public ImplicitDeclarationNode(
            final TextPosition position, final IdentifierNode ident
    ) {
        super(position, ident);

        type = TypeNode.wildcard();
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public TypeNode getType() {
        return type;
    }

    public void setType(final TypeNode type, final SymbolTable symbolTable) {
        this.type = type;

        // overwrite wildcard in symbol table with concrete-typed variable
        if (symbolTable.hasVarAtLevel(getIdent()))
            symbolTable.put(getIdent(), new Variable(isMutable(), type));
    }
}
