package com.jordanbunke.delta_time.io;

import com.jordanbunke.delta_time.error.GameError;
import com.jordanbunke.delta_time.utility.StringProcessing;
import com.jordanbunke.sorkin.IFileDialog;

import java.io.*;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileIO {
    private static IFileDialog FILE_DIALOG = IFileDialog.make();

    public static Path extractZipToTempDir(final String zipPath) {
        try {
            final Path tempDir = Files.createTempDirectory(null);
            extractZipToDir(zipPath, tempDir);
            return tempDir;
        } catch (IOException e) {
            GameError.send(e.getMessage());
        }

        return null;
    }

    public static void deleteDirRecursive(final Path dir) {
        try {
            Files.walkFileTree(dir, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(final Path dir, final IOException exc) throws IOException {
                    Files.delete(dir);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            GameError.send(e.getMessage());
        }
    }

    public static void extractZipToDir(final String zipPath, final Path dir) {
        try (ZipInputStream zipInputStream =
                     new ZipInputStream(new FileInputStream(zipPath))) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                final File extractedFile = new File(dir.toFile(), entry.getName());

                if (entry.isDirectory()) {
                    if (!extractedFile.mkdirs())
                        GameError.send("Failed to create directory: " +
                                extractedFile);
                } else {
                    final File parentDir = extractedFile.getParentFile();
                    if (!parentDir.exists() && !parentDir.mkdirs())
                        GameError.send("Failed to create directory: " + parentDir);

                    try (FileOutputStream fos = new FileOutputStream(extractedFile)) {
                        byte[] buffer = new byte[1024];
                        int length;
                        while ((length = zipInputStream.read(buffer)) > 0) {
                            fos.write(buffer, 0, length);
                        }
                    }
                }
                zipInputStream.closeEntry();
            }
        } catch (IOException e) {
            GameError.send(e.getMessage());
        }
    }

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

            if (!contents.toString().isEmpty())
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
        FILE_DIALOG = IFileDialog.make();
    }

    public static void setDialogToFoldersOnly() {
        FILE_DIALOG.setFileSelectionMode(IFileDialog.FOLDERS);
    }

    public static void setDialogToFilesOnly() {
        FILE_DIALOG.setFileSelectionMode(IFileDialog.FILES);
    }

    public static void setDialogToFilesAndFolders() {
        FILE_DIALOG.setFileSelectionMode(IFileDialog.BOTH);
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

        FILE_DIALOG.resetChoosableFileFilters();

        for (int i = 0; i < filterNames.length; i++)
            FILE_DIALOG.addFileFilter(filterNames[i], extensionFilters[i]);

        final int result = FILE_DIALOG.showOpenDialog();

        if (result == IFileDialog.VALID)
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

        FILE_DIALOG.resetChoosableFileFilters();

        for (int i = 0; i < filterNames.length; i++)
            FILE_DIALOG.addFileFilter(filterNames[i], extensionFilters[i]);

        final int result = FILE_DIALOG.showOpenDialog();

        if (result == IFileDialog.VALID)
            return Optional.of(FILE_DIALOG.getSelectedFiles());
        else return Optional.empty();
    }
}
