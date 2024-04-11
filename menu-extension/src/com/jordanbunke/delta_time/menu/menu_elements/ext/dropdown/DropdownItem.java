package com.jordanbunke.delta_time.menu.menu_elements.ext.dropdown;

public sealed abstract class DropdownItem permits DropdownBehaviour, NestedDropdownItem {
    public final String label;

    public DropdownItem(final String label) {
        this.label = label;
    }
}
