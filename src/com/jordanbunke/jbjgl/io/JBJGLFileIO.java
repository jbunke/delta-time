package com.jordanbunke.jbjgl.io;

import com.jordanbunke.jbjgl.utility.JBJGLGlobal;
import com.jordanbunke.jbjgl.utility.StringProcessing;

import java.io.*;
import java.nio.file.Path;

public class JBJGLFileIO {
    public static String readFile(final Path filepath) {
        StringBuilder contents = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(filepath.toFile()));
            while (br.ready())
                contents.append(br.readLine()).append("\n");

            contents.deleteCharAt(contents.toString().length() - 1);
        } catch (IOException e) {
            JBJGLGlobal.printErrorToJBJGLChannel("Couldn't read file: " + filepath);
        }

        return contents.toString();
    }

    public static void writeFile(final Path filepath, final String contents, final boolean append) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filepath.toFile(), append));
            bw.write(contents);
        } catch (IOException e) {
            JBJGLGlobal.printErrorToJBJGLChannel("Couldn't write to file: " + filepath);
        }
    }

    public static void writeFile(final Path filepath, final String contents) {
        writeFile(filepath, contents, false);
    }

    public static void writeFile(final Path filepath, final String[] lines, final boolean append) {
        writeFile(filepath, StringProcessing.linesToString(lines), append);
    }

    public static void writeFile(final Path filepath, final String[] lines) {
        writeFile(filepath, lines, false);
    }
}
