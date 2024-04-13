package com.jordanbunke.delta_time.menu.menu_elements.ext.dropdown;

import java.util.function.Supplier;

public final class LogicItem extends SimpleItem implements Supplier<Boolean> {
    private final Supplier<Boolean> precondition;

    public LogicItem(
            final String label, final Supplier<Boolean> precondition,
            final Runnable behaviour
    ) {
        super(label, behaviour);

        this.precondition = precondition;
    }

    @Override
    public Boolean get() {
        return precondition.get();
    }

    @Override
    public void run() {
        if (precondition.get())
            run();
    }
}
