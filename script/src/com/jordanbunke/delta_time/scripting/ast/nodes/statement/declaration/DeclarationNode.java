package com.jordanbunke.delta_time.scripting.ast.nodes.statement.declaration;

import com.jordanbunke.delta_time.scripting.ast.nodes.expression.assignable.IdentifierNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.statement.StatementNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.Variable;
import com.jordanbunke.delta_time.scripting.util.FuncControlFlow;
import com.jordanbunke.delta_time.scripting.util.ScriptErrorLog;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public abstract sealed class DeclarationNode extends StatementNode
        permits ExplicitDeclarationNode, ImplicitDeclarationNode {
    final IdentifierNode ident;

    public DeclarationNode(
            final TextPosition position,
            final IdentifierNode ident
    ) {
        super(position);

        this.ident = ident;
    }

    public abstract boolean isMutable();
    public abstract TypeNode getType();

    public final String getIdent() {
        return ident.getName();
    }

    @Override
    public void semanticErrorCheck(final SymbolTable symbolTable) {
        if (symbolTable.hasVarAtLevel(getIdent()))
            ScriptErrorLog.fireError(
                    ScriptErrorLog.Message.VAR_ALREADY_DEFINED,
                    ident.getPosition(), getIdent());
        else
            symbolTable.put(getIdent(), new Variable(isMutable(), getType()));
    }

    @Override
    public FuncControlFlow execute(final SymbolTable symbolTable) {
        symbolTable.put(ident.getName(),
                new Variable(isMutable(), getType()));

        return FuncControlFlow.cont();
    }

    @Override
    public String toString() {
        return (isMutable() ? "" : "final ") + getType() + " " + ident;
    }
}
