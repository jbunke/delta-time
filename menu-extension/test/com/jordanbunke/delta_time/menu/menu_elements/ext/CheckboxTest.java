package com.jordanbunke.delta_time.menu.menu_elements.ext;

import com.jordanbunke.delta_time.utility.math.Coord2D;
import com.jordanbunke.funke.core.ConcreteProperty;
import org.junit.Assert;
import org.junit.Test;

public class CheckboxTest {
    @Test
    public void checkExample() {
        final ExampleCheckbox checkbox = new ExampleCheckbox(new Coord2D(),
                new ConcreteProperty<>(() -> false, a -> {}));

        Assert.assertEquals(0, checkbox.getX());
    }
}
