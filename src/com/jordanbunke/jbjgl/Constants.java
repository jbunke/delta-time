package com.jordanbunke.jbjgl;

import com.jordanbunke.jbjgl.io.JBJGLFileIO;
import com.jordanbunke.jbjgl.io.JBJGLResourceLoader;
import com.jordanbunke.jbjgl.utility.Version;
import com.jordanbunke.jbjgl.utility.StringProcessing;

import java.nio.file.Path;

public class Constants {
    public static final String INFO_FILENAME = "jbjgl_info.txt";

    public static final String TITLE;
    public static final Version VERSION;

    static {
        final String TITLE_TAG = "title", VERSION_TAG = "version", SEPARATOR = ":", OPEN = "{", CLOSE = "}";
        final int HAS_BUILD_LENGTH = 4, MAJOR = 0, MINOR = 1, PATCH = 2, BUILD = 3;

        final Path INFO_FILE = Path.of(INFO_FILENAME);
        final String contents = JBJGLFileIO.readResource(
                JBJGLResourceLoader.loadResource(Constants.class, INFO_FILE), INFO_FILE.toString());

        TITLE = StringProcessing.getContentsFromTag(contents,
                TITLE_TAG, SEPARATOR, OPEN, CLOSE, "failed");

        final String[] versionInfo = StringProcessing.getContentsFromTag(contents,
                VERSION_TAG, SEPARATOR, OPEN, CLOSE, "1.0.0").split("\\.");

        if (versionInfo.length == HAS_BUILD_LENGTH)
            VERSION = new Version(Integer.parseInt(versionInfo[MAJOR]),
                    Integer.parseInt(versionInfo[MINOR]), Integer.parseInt(versionInfo[PATCH]),
                    Integer.parseInt(versionInfo[BUILD]));
        else
            VERSION = new Version(Integer.parseInt(versionInfo[MAJOR]),
                    Integer.parseInt(versionInfo[MINOR]), Integer.parseInt(versionInfo[PATCH]));
    }
}
