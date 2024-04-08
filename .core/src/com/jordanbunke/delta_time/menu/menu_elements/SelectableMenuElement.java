package com.jordanbunke.delta_time.menu.menu_elements;

import com.jordanbunke.delta_time.menu.Menu;
import com.jordanbunke.delta_time.utility.math.Coord2D;

public abstract class SelectableMenuElement extends MenuElement {
    public static final int UNSUITABLE = Integer.MAX_VALUE;

    private boolean selected;

    public SelectableMenuElement(
            final Coord2D position, final Coord2D dimensions,
            final Anchor anchor, final boolean visible
    ) {
        super(position, dimensions, anchor, visible);

        selected = false;
    }

    public void select() {
        selected = true;
    }

    public void deselect() {
        selected = false;
    }

    public final boolean attemptChoose() {
        if (selected)
            choose();

        return selected;
    }

    public abstract void choose();

    public int selectionSuitability(
            final SelectableMenuElement selected, final Menu.Direction direction
    ) {
        final int diffX = selected.getX() - getX(), // > 0 means THIS is left of SELECTED
                diffY = selected.getY() - getY();   // > 0 means THIS is above SELECTED

        if (diffX == 0 && diffY == 0)
            return UNSUITABLE;

        switch (direction) {
            case UP -> {
                if (diffY <= 0)
                    return UNSUITABLE;

                return Math.abs(diffY) + diagonalTax(diffX);
            }
            case DOWN -> {
                if (diffY >= 0)
                    return UNSUITABLE;

                return Math.abs(diffY) + diagonalTax(diffX);
            }
            case LEFT -> {
                if (diffX <= 0)
                    return UNSUITABLE;

                return Math.abs(diffX) + diagonalTax(diffY);
            }
            case RIGHT -> {
                if (diffX >= 0)
                    return UNSUITABLE;

                return Math.abs(diffX) + diagonalTax(diffY);
            }
        }

        return UNSUITABLE;
    }

    private int diagonalTax(final int offDim) {
        final double DIAGONAL_TAX = 2.0;
        return (int)(Math.abs(offDim) * DIAGONAL_TAX);
    }

    public boolean isSelected() {
        return selected;
    }
}
