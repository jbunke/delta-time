package com.jordanbunke.jbjgl.io;

import com.jordanbunke.jbjgl.error.JBJGLError;
import com.jordanbunke.jbjgl.utility.StringProcessing;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class JBJGLFileIO {
    public static void safeMakeDirectory(final Path dirPath) {
        File dir = dirPath.toFile();

        if (dir.exists())
            JBJGLError.send("Filepath already exists: " + dirPath);
        else if (!dir.mkdirs())
            JBJGLError.send("Couldn't create directory at specified filepath: " + dirPath);
    }

    public static String readFile(final Path filepath) {
        try {
            return read(new FileReader(filepath.toFile()), filepath.toString());
        } catch (FileNotFoundException e) {
            JBJGLError.send("File not found: " + filepath);
        }

        return null;
    }

    public static String readResource(final InputStream in, final String name) {
        return read(new InputStreamReader(in), "input stream for \"" + name + "\"");
    }

    private static String read(final Reader reader, final String name) {
        StringBuilder contents = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(reader);
            while (br.ready())
                contents.append(br.readLine()).append("\n");

            if (contents.toString().length() > 0)
                contents.deleteCharAt(contents.toString().length() - 1);
        } catch (IOException e) {
            JBJGLError.send("Couldn't read: " + name);
        }

        return contents.toString();
    }

    public static void writeFile(final Path filepath, final String contents, final boolean append) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filepath.toFile(), append));
            bw.write(contents);
            bw.close();
        } catch (IOException e) {
            JBJGLError.send("Couldn't write to file: " + filepath);
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

    public static void writeFileOf(final Path filepath, final String... lines) {
        writeFile(filepath, lines, false);
    }

    public static void makeDirectory(final Path filepath) {
        final File directory = filepath.toFile();

        if (!directory.exists()) directory.mkdirs();
    }

    public static void deleteFile(final Path filepath) {
        try {
            Files.delete(filepath);
        } catch (IOException e) {
            JBJGLError.send("Couldn't delete file: " + filepath);
        }
    }

    public static Optional<File> openFileFromSystem() {
        return openFileFromSystem(new String[] {}, new String[][] {});
    }

    public static Optional<File> openFileFromSystem(final String[] filterNames, final String[][] extensionFilters) {
        final JFileChooser fc = new JFileChooser();

        if (filterNames.length != extensionFilters.length) {
            JBJGLError.send("Number of filter names does not match number of extensions in file chooser");
            return Optional.empty();
        }

        for (int i = 0; i < filterNames.length; i++)
            fc.setFileFilter(new FileNameExtensionFilter(filterNames[i], extensionFilters[i]));

        final int result = fc.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION)
            return Optional.of(fc.getSelectedFile());
        else return Optional.empty();
    }
}
