package com.jordanbunke.delta_time;

import com.jordanbunke.delta_time.io.FileIO;

import java.nio.file.Path;

public class IncrementVersion {
    public static void main(String[] args) {
        About.VERSION.incrementBuild();

        System.out.println(About.VERSION);

        final String[] infoFileContents = new String[] {
                "title:{" + About.TITLE + "}",
                "version:{" + About.VERSION + "}",
                ""
        };

        FileIO.writeFile(Path.of("res", About.INFO_FILENAME), infoFileContents);
    }
}
