package com.jordanbunke.delta_time.menu.menu_elements.ext.dropdown;

public class SimpleItem extends DropdownItem implements Runnable {
    private final Runnable behaviour;

    public SimpleItem(
            final String label,
            final Runnable behaviour
    ) {
        super(label);
        this.behaviour = behaviour;
    }

    public void run() {
        behaviour.run();
    }
}
