package com.jordanbunke.delta_time.menu.menu_elements.ext.dropdown;

public final class DropdownBehaviour extends DropdownItem implements Runnable {
    private final Runnable behaviour;

    public DropdownBehaviour(
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
