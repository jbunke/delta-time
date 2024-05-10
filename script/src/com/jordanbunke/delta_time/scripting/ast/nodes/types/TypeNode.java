package com.jordanbunke.delta_time.scripting.ast.nodes.types;

import com.jordanbunke.delta_time.scripting.ast.nodes.ASTNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

import java.util.Set;

public abstract class TypeNode extends ASTNode {
    private static BaseTypeNode CHAR, INT, BOOL, FLOAT,
            STRING, COLOR, IMAGE, WILDCARD;

    public TypeNode(final TextPosition position) {
        super(position);
    }

    @Override
    public void semanticErrorCheck(final SymbolTable symbolTable) {}

    public boolean isNum() {
        return false;
    }

    public abstract boolean hasSize();

    public abstract boolean complies(final Object o);

    @Override
    public abstract boolean equals(final Object o);

    @Override
    public abstract int hashCode();

    @Override
    public abstract String toString();

    public static Set<TypeNode> numTypes() {
        return Set.of(getInt(), getFloat());
    }

    public static CollectionTypeNode set() {
        return setOf(wildcard());
    }

    public static CollectionTypeNode setOf(final TypeNode elementType) {
        return new CollectionTypeNode(
                CollectionTypeNode.Type.SET, elementType);
    }

    public static CollectionTypeNode list() {
        return listOf(wildcard());
    }

    public static CollectionTypeNode listOf(final TypeNode elementType) {
        return new CollectionTypeNode(
                CollectionTypeNode.Type.LIST, elementType);
    }

    public static CollectionTypeNode array() {
        return arrayOf(wildcard());
    }

    public static CollectionTypeNode arrayOf(final TypeNode elementType) {
        return new CollectionTypeNode(
                CollectionTypeNode.Type.ARRAY, elementType);
    }

    public static BaseTypeNode getFloat() {
        if (FLOAT == null)
            FLOAT = new BaseTypeNode(BaseTypeNode.Type.FLOAT);

        return FLOAT;
    }

    public static BaseTypeNode getInt() {
        if (INT == null)
            INT = new BaseTypeNode(BaseTypeNode.Type.INT);

        return INT;
    }

    public static BaseTypeNode getChar() {
        if (CHAR == null)
            CHAR = new BaseTypeNode(BaseTypeNode.Type.CHAR);

        return CHAR;
    }

    public static BaseTypeNode getBool() {
        if (BOOL == null)
            BOOL = new BaseTypeNode(BaseTypeNode.Type.BOOL);

        return BOOL;
    }

    public static BaseTypeNode getString() {
        if (STRING == null)
            STRING = new BaseTypeNode(BaseTypeNode.Type.STRING);

        return STRING;
    }

    public static BaseTypeNode getColor() {
        if (COLOR == null)
            COLOR = new BaseTypeNode(BaseTypeNode.Type.COLOR);

        return COLOR;
    }

    public static BaseTypeNode getImage() {
        if (IMAGE == null)
            IMAGE = new BaseTypeNode(BaseTypeNode.Type.IMAGE);

        return IMAGE;
    }
    
    public static BaseTypeNode wildcard() {
        if (WILDCARD == null)
            WILDCARD = new BaseTypeNode(BaseTypeNode.Type.WILDCARD);

        return WILDCARD;
    }
}
