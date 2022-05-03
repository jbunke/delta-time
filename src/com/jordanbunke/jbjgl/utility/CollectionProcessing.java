package com.jordanbunke.jbjgl.utility;

import java.util.List;

public class CollectionProcessing {
    public static <T> void emptyList(final List<T> list) {
        while(!list.isEmpty())
            list.remove(0);
    }
}
