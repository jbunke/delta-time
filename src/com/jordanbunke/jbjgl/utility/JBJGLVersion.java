package com.jordanbunke.jbjgl.utility;

public final class JBJGLVersion {
    private int major, minor, patch, build;
    private final boolean hasBuild;

    private JBJGLVersion(
            final int major, final int minor, final int patch,
            final int build, final boolean hasBuild
    ) {
        this.major = major;
        this.minor = minor;
        this.patch = patch;

        this.hasBuild = hasBuild;
        this.build = hasBuild ? build : 0;
    }

    public static JBJGLVersion generate(
            final int major, final int minor, final int patch
    ) {
        return new JBJGLVersion(major, minor, patch, 0, false);
    }

    public static JBJGLVersion generate(
            final int major, final int minor, final int patch, final int build
    ) {
        return new JBJGLVersion(major, minor, patch, build, true);
    }

    public void incrementMajor() {
        major++;
        minor = 0;
        patch = 0;

        if (hasBuild)
            build = 1;
    }

    public void incrementMinor() {
        minor++;
        patch = 0;

        if (hasBuild)
            build = 1;
    }

    public void incrementPatch() {
        patch++;

        if (hasBuild)
            build = 1;
    }

    public void incrementBuild() {
        if (hasBuild)
            build++;
    }

    @Override
    public String toString() {
        return major + "." + minor + "." + patch + (hasBuild ? "." + build : "");
    }
}
