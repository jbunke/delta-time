package com.jordanbunke.delta_time.io;

import com.jordanbunke.delta_time.error.GameError;
import com.jordanbunke.delta_time.utility.StringProcessing;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class FileIO {
    private static JFileChooser FILE_DIALOG = new JFileChooser();

    public static void safeMakeDirectory(final Path dirPath) {
        File dir = dirPath.toFile();

        if (dir.exists())
            GameError.send("Filepath already exists: " + dirPath);
        else if (!dir.mkdirs())
            GameError.send("Couldn't create directory at specified filepath: " + dirPath);
    }

    public static String readFile(final Path filepath) {
        try {
            return read(new FileReader(filepath.toFile()), filepath.toString());
        } catch (FileNotFoundException e) {
            GameError.send("File not found: " + filepath);
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
            GameError.send("Couldn't read: " + name);
        }

        return contents.toString();
    }

    public static void writeFile(final Path filepath, final String contents, final boolean append) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filepath.toFile(), append));
            bw.write(contents);
            bw.close();
        } catch (IOException e) {
            GameError.send("Couldn't write to file: " + filepath);
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

    public static void deleteFile(final Path filepath) {
        try {
            Files.delete(filepath);
        } catch (IOException e) {
            GameError.send("Couldn't delete file: " + filepath);
        }
    }

    public static void reinitializeDialog() {
        FILE_DIALOG = new JFileChooser();
    }

    public static void setDialogToFoldersOnly() {
        FILE_DIALOG.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    }

    public static void setDialogToFilesOnly() {
        FILE_DIALOG.setFileSelectionMode(JFileChooser.FILES_ONLY);
    }

    public static void setDialogToFilesAndFolders() {
        FILE_DIALOG.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
    }

    public static Optional<File> openFileFromSystem() {
        return openFileFromSystem(new String[] {}, new String[][] {});
    }

    public static Optional<File> openFileFromSystem(
            final String[] filterNames, final String[][] extensionFilters
    ) {
        if (filterNames.length != extensionFilters.length) {
            GameError.send("Number of filter names does not match number of extensions in file chooser");
            return Optional.empty();
        }

        FILE_DIALOG.setMultiSelectionEnabled(false);

        for (int i = 0; i < filterNames.length; i++)
            FILE_DIALOG.setFileFilter(new FileNameExtensionFilter(filterNames[i], extensionFilters[i]));

        final int result = FILE_DIALOG.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION)
            return Optional.of(FILE_DIALOG.getSelectedFile());
        else return Optional.empty();
    }

    public static Optional<File[]> openFilesFromSystem() {
        return openFilesFromSystem(new String[] {}, new String[][] {});
    }

    public static Optional<File[]> openFilesFromSystem(
            final String[] filterNames, final String[][] extensionFilters
    ) {
        if (filterNames.length != extensionFilters.length) {
            GameError.send("Number of filter names does not match number of extensions in file chooser");
            return Optional.empty();
        }

        FILE_DIALOG.setMultiSelectionEnabled(true);

        for (int i = 0; i < filterNames.length; i++)
            FILE_DIALOG.setFileFilter(new FileNameExtensionFilter(filterNames[i], extensionFilters[i]));

        final int result = FILE_DIALOG.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION)
            return Optional.of(FILE_DIALOG.getSelectedFiles());
        else return Optional.empty();
    }
}
