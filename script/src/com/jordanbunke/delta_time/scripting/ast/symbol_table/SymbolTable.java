package com.jordanbunke.delta_time.scripting.ast.symbol_table;

import com.jordanbunke.delta_time.scripting.ast.nodes.ASTNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.function.FuncNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.function.HeadFuncNode;

import java.util.HashMap;
import java.util.Map;

public final class SymbolTable {
    private final ASTNode scope;
    private final SymbolTable parent;
    private final Map<ASTNode, SymbolTable> children;
    private final Map<String, Variable> contents;
    private Variable scopeVar;

    public SymbolTable(
            final ASTNode scope,
            final SymbolTable parent
    ) {
        this.scope = scope;
        this.parent = parent;

        children = new HashMap<>();
        contents = new HashMap<>();
        scopeVar = null;

        if (parent != null)
            parent.addChild(this.scope, this);
    }

    public static String funcWithName(final String name) {
        return "::" + name;
    }

    public static SymbolTable root(
            final HeadFuncNode func
    ) {
        return new SymbolTable(func, null);
    }

    public SymbolTable getRoot() {
        return parent != null ? parent.getRoot() : this;
    }

    public FuncNode getFunc() {
        if (scope instanceof FuncNode func)
            return func;

        return parent != null ? parent.getFunc() : null;
    }

    private void addChild(
            final ASTNode subScope, final SymbolTable child
    ) {
        children.put(subScope, child);
    }

    public SymbolTable getChild(final ASTNode subScope) {
        return children.getOrDefault(subScope, null);
    }

    public boolean hasVarAtLevel(final String ident) {
        return contents.containsKey(ident);
    }

    public Variable get(final String ident) {
        if (contents.containsKey(ident))
            return contents.get(ident);

        return parent != null ? parent.get(ident) : null;
    }

    public void put(final String ident, final Variable var) {
        contents.put(ident, var);
    }

    public void update(final String ident, final Object value) {
        if (!contents.containsKey(ident)) {
            if (parent != null)
                parent.update(ident, value);

            return;
        }

        contents.get(ident).set(value);
    }

    public void defineScopeVar(final ExpressionNode e) {
        scopeVar = new Variable(false, e.getType(this));
    }

    public void evaluateScopeVar(final ExpressionNode e) {
        scopeVar.set(e.evaluate(this));
    }

    public Variable getScopeVar() {
        return scopeVar;
    }

    @Override
    public String toString() {
        final int gen = generation();
        final String tab = "  ".repeat(gen);

        final StringBuilder sb = new StringBuilder();

        final String header = "Scope: " + scope.getClass().getSimpleName();

        sb.append(tab).append(header)
                .append(" ".repeat(Math.max(5, 40 - (header + tab).length())))
                .append(scope.toString().split("\n")[0]).append("\n\n");

        for (String var : contents.keySet()) {
            sb.append(tab).append(var).append(" -> ")
                    .append(contents.get(var)).append("\n");
        }

        return (parent != null ? parent + "\n" + tab +
                "-".repeat(20 + (2 * gen)) : "") + "\n" + sb;
    }

    private int generation() {
        return parent == null ? 0 : 1 + parent.generation();
    }
}
