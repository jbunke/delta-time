package com.jordanbunke.jbjgl;

import com.jordanbunke.jbjgl.io.FileIO;

import java.nio.file.Path;

public class IncrementVersion {
    public static void main(String[] args) {
        Constants.VERSION.incrementBuild();

        final String[] infoFileContents = new String[] {
                "title:{" + Constants.TITLE + "}",
                "version:{" + Constants.VERSION + "}",
                ""
        };

        FileIO.writeFile(Path.of("res", Constants.INFO_FILENAME), infoFileContents);
    }
}
