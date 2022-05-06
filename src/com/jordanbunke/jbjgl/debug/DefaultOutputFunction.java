package com.jordanbunke.jbjgl.debug;

import java.util.function.Consumer;

public class DefaultOutputFunction implements Consumer<String> {
    @Override
    public void accept(String s) {
        System.out.println(s);
    }
}
