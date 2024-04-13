package com.jordanbunke.delta_time.menu.menu_elements.ext.dropdown;

public final class NestedItem extends DropdownItem {
    private final DropdownItem[] items;

    public NestedItem(
            final String label,
            final DropdownItem... items
    ) {
        super(label);
        this.items = items;
    }

    public DropdownItem[] getItems() {
        return items;
    }
}
