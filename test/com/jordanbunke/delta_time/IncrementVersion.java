package com.jordanbunke.delta_time;

import com.jordanbunke.delta_time.io.FileIO;

import java.nio.file.Path;

public class IncrementVersion {
    public static void main(String[] args) {
        Constants.VERSION.incrementBuild();

        System.out.println(Constants.VERSION);

        final String[] infoFileContents = new String[] {
                "title:{" + Constants.TITLE + "}",
                "version:{" + Constants.VERSION + "}",
                ""
        };

        FileIO.writeFile(Path.of("res", Constants.INFO_FILENAME), infoFileContents);
    }
}
