package com.jordanbunke.delta_time.scripting.ast.nodes.statement.control_flow;

import com.jordanbunke.delta_time.scripting.ast.collection.ScriptCollection;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.statement.StatementNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.statement.declaration.DeclarationNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.statement.declaration.ImplicitDeclarationNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.CollectionTypeNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.FuncControlFlow;
import com.jordanbunke.delta_time.scripting.util.ScriptErrorLog;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public final class IteratorLoopNode extends StatementNode {
    private final DeclarationNode declaration;
    private final ExpressionNode collection;
    private final StatementNode loopBody;

    public IteratorLoopNode(
            final TextPosition position,
            final DeclarationNode declaration,
            final ExpressionNode collection,
            final StatementNode loopBody
    ) {
        super(position);

        this.declaration = declaration;
        this.collection = collection;
        this.loopBody = loopBody;
    }

    @Override
    public void semanticErrorCheck(final SymbolTable symbolTable) {
        final SymbolTable innerTable = new SymbolTable(this, symbolTable);

        declaration.semanticErrorCheck(innerTable);
        collection.semanticErrorCheck(innerTable);

        final TypeNode charType = TypeNode.getChar(),
                stringType = TypeNode.getString(),
                colType = collection.getType(symbolTable),
                elemType;

        if (colType instanceof CollectionTypeNode c)
            elemType = c.getElementType();
        else if (stringType.equals(colType))
            elemType = charType;
        else
            elemType = null;

        if (declaration instanceof ImplicitDeclarationNode implicit)
            implicit.setType(elemType, innerTable);

        loopBody.semanticErrorCheck(innerTable);

        final TypeNode varType = declaration.getType();

        if (elemType != null) {
            if (!varType.equals(elemType))
                ScriptErrorLog.fireError(
                        ScriptErrorLog.Message.ELEMENT_DOES_NOT_MATCH_COL,
                        declaration.getPosition(),
                        elemType.toString(), varType.toString());
        } else
            ScriptErrorLog.fireError(
                    ScriptErrorLog.Message.NOT_ITERABLE,
                    collection.getPosition(),
                    "string\", \"list - <>\" or \"array - []\"",
                    colType.toString());
    }

    @Override
    public FuncControlFlow execute(final SymbolTable symbolTable) {
        final SymbolTable innerTable = symbolTable.getChild(this);

        final Object col = collection.evaluate(innerTable);
        final int size = col instanceof ScriptCollection sc
                ? sc.size() : String.valueOf(col).length();

        FuncControlFlow status = FuncControlFlow.cont();

        for (int i = 0; i < size; i++) {
            final Object element = col instanceof ScriptCollection sc
                    ? sc.get(i) : String.valueOf(col).charAt(i);
            innerTable.update(declaration.getIdent(), element);

            status = loopBody.execute(innerTable);

            if (!status.cont)
                return status;
        }

        return status;
    }

    @Override
    public String toString() {
        return "for (" + declaration + " in " + collection + ")\n" + loopBody;
    }
}
