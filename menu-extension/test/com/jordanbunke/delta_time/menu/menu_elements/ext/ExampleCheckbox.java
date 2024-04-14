package com.jordanbunke.delta_time.menu.menu_elements.ext;

import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.utility.math.Bounds2D;
import com.jordanbunke.delta_time.utility.math.Coord2D;
import com.jordanbunke.funke.core.Property;

import java.awt.*;

public class ExampleCheckbox extends AbstractCheckbox {
    private static final int DIM_PX = 20, BORDER_PX = 2;
    private static final Color C_HL = new Color(0, 100, 175),
            C_BOTH = new Color(0, 175, 100),
            C_CK = new Color(0, 140, 0),
            C_NEITHER = new Color(140, 0, 0),
            C_BORDER = new Color(0, 0, 0);

    public ExampleCheckbox(
            final Coord2D position, final Property<Boolean> property
    ) {
        super(position, new Bounds2D(DIM_PX, DIM_PX), Anchor.LEFT_TOP, property, ExampleCheckbox::draw);
    }

    public static GameImage draw(final boolean highlighted, final boolean checked) {
        final GameImage image = new GameImage(DIM_PX, DIM_PX);

        image.fillRectangle(C_BORDER, 0, 0, DIM_PX, DIM_PX);

        final int internalDim = DIM_PX - (2 * BORDER_PX);
        final Color cInternal = highlighted
                ? (checked ? C_BOTH : C_HL)
                : (checked ? C_CK : C_NEITHER);

        image.fillRectangle(cInternal, BORDER_PX, BORDER_PX, internalDim, internalDim);

        return image.submit();
    }
}
