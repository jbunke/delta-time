package com.jordanbunke.delta_time.utility;

import java.util.List;

public final class CollectionProcessing {
    public static <T> void emptyList(final List<T> list) {
        while(!list.isEmpty())
            list.remove(0);
    }
}
