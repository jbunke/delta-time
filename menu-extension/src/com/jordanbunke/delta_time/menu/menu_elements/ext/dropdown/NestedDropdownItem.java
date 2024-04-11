package com.jordanbunke.delta_time.menu.menu_elements.ext.dropdown;

public final class NestedDropdownItem extends DropdownItem {
    private final DropdownItem[] items;
    private final int dropdownAllowanceY;

    public NestedDropdownItem(
            final String label,
            final DropdownItem[] items,
            final int dropdownAllowanceY
    ) {
        super(label);
        this.items = items;
        this.dropdownAllowanceY = dropdownAllowanceY;
    }

    public DropdownItem[] getItems() {
        return items;
    }

    public int getDropdownAllowanceY() {
        return dropdownAllowanceY;
    }
}
