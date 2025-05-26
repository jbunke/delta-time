package com.jordanbunke.delta_time.utility;

import java.util.Arrays;

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

    public static Version parse(final String s) {
        try {
            final String[] sections = s.split("\\.");
            final int[] v = Arrays.stream(sections)
                    .mapToInt(Integer::parseInt)
                    .filter(i -> i >= 0).toArray();

            if (v.length < sections.length)
                return null;

            if (v.length == 4)
                return new Version(v[0], v[1], v[2], v[3]);
            else if (v.length == 3)
                return new Version(v[0], v[1], v[2]);

            return null;
        } catch (NumberFormatException nfe) {
            return null;
        }
    }

    @Override
    public boolean equals(final Object o) {
        return o instanceof Version v &&
                major == v.major && minor == v.minor && patch == v.patch &&
                build == v.build && hasBuild == v.hasBuild;
    }

    /** @return {@code true} if {@code this} is a later version
     * than {@code v}, {@code false} otherwise */
    public boolean isLaterVersion(final Version v) {
        if (equals(v))
            return false;

        return isLater(0, v);
    }

    private boolean isLater(final int index, final Version v) {
        final int n = getIndex(index), vn = v.getIndex(index);

        if (n > vn)
            return true;
        else if (n < vn)
            return false;
        else if (index < 2 || index == 2 && hasBuild)
            return isLater(index + 1, v);

        return false;
    }

    private int getIndex(final int index) {
        return switch (index) {
            case 0 -> major;
            case 1 -> minor;
            case 2 -> patch;
            case 3 -> hasBuild ? build : -1;
            default -> -1;
        };
    }
}
