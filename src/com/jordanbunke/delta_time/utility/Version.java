package com.jordanbunke.delta_time.utility;

public final class Version {
    private int major, minor, patch, build;
    private final boolean hasBuild;

    private Version(
            final int major, final int minor, final int patch,
            final int build, final boolean hasBuild
    ) {
        this.major = major;
        this.minor = minor;
        this.patch = patch;

        this.hasBuild = hasBuild;
        this.build = hasBuild ? build : 0;
    }

    public Version(
            final int major, final int minor, final int patch
    ) {
        this(major, minor, patch, 0, false);
    }

    public Version(
            final int major, final int minor, final int patch, final int build
    ) {
        this(major, minor, patch, build, true);
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
