package com.jordanbunke.delta_time.sprite;

import java.util.*;

public class SpriteStates<R> {
    public static final String STANDARD_SEPARATOR = "-";

    private final String separator;
    private final R[][] contributors;

    private final List<String> validSpriteIDs;

    @SafeVarargs
    public SpriteStates(final String separator, final R[]... contributors) {
        this.separator = separator;
        this.contributors = contributors;

        validSpriteIDs = new ArrayList<>(generateSpriteIDPermutations());
    }

    @SafeVarargs
    public SpriteStates(final R[]... contributors) {
        this(STANDARD_SEPARATOR, contributors);
    }

    public List<String> generateSpriteIDPermutations() {
        return SpriteStates.generateSpriteIDPermutations(separator, contributors);
    }

    public Optional<String> getSpriteIDIfValid(final int... contributorIndices) {
        if (contributorIndices.length != contributors.length)
            return Optional.empty();

        final StringBuilder spriteIDBuilder = new StringBuilder();

        for (int i = 0; i < contributorIndices.length; i++) {
            final int index = contributorIndices[i];

            if (index < 0 || index >= contributors[i].length)
                return Optional.empty();

            spriteIDBuilder.append(contributors[i][index]);

            if (i + 1 < contributorIndices.length)
                spriteIDBuilder.append(separator);
        }

        final String spriteID = spriteIDBuilder.toString();

        if (isValidSpriteID(spriteID))
            return Optional.of(spriteID);

        return Optional.empty();
    }

    public boolean isValidSpriteID(final String spriteID) {
        return validSpriteIDs.contains(spriteID);
    }

    public void addSpecialCase(final String spriteID) {
        validSpriteIDs.add(spriteID);
    }

    public void addSpecialCases(final Collection<String> spriteIDs) {
        validSpriteIDs.addAll(spriteIDs);
    }

    public void removeSpriteIDFromValidList(final String spriteID) {
        validSpriteIDs.remove(spriteID);
    }

    public void removeSpriteIDsFromValidList(final Collection<String> spriteIDs) {
        validSpriteIDs.removeAll(spriteIDs);
    }

    public void removeMutuallyExclusiveContributors(
            final Collection<String> anyOf, final String... contributors
    ) {
        anyOf.forEach(x -> {
            final String[] withX = new String[contributors.length + 1];

            System.arraycopy(contributors, 0, withX, 0, contributors.length);
            withX[contributors.length] = x;

            removeMutuallyExclusiveContributors(withX);
        });
    }

    public void removeMutuallyExclusiveContributors(final String... contributors) {
        final Set<String> spriteIDs = new HashSet<>(getValidSpriteIDs());
        final Set<String> haveMutuallyExclusiveContributors = new HashSet<>();

        for (String spriteID : spriteIDs) {
            boolean containsAll = true;

            for (String contributor : contributors) {
                containsAll = substringIsContributor(contributor, spriteID);
                if (!containsAll)
                    break;
            }

            if (containsAll)
                haveMutuallyExclusiveContributors.add(spriteID);
        }

        removeSpriteIDsFromValidList(haveMutuallyExclusiveContributors);
    }

    private boolean substringIsContributor(final String substring, final String spriteID) {
        return spriteID.equals(substring) ||
                spriteID.startsWith(substring + separator) ||
                spriteID.endsWith(separator + substring) ||
                spriteID.contains(separator + substring + separator);
    }

    public List<String> getValidSpriteIDs() {
        return validSpriteIDs;
    }

    @SafeVarargs
    public static <U> List<String> generateSpriteIDPermutations(
            final String separator, final U[]... contributors
    ) {
        final List<String> spriteIDs = new ArrayList<>();

        for (int i = 0; i < contributors[0].length; i++) {
            permutationRecursion(separator, contributors, 1,
                    contributors[0][i].toString(), spriteIDs);
        }

        return spriteIDs;
    }

    @SafeVarargs
    public static <U> List<String> generateSpriteIDPermutations(final U[]... contributors) {
        return generateSpriteIDPermutations(STANDARD_SEPARATOR, contributors);
    }

    private static <U> void permutationRecursion(
            final String separator, final U[][] contributors,
            final int layer, final String builtSoFar,
            final List<String> spriteIDs
    ) {
        if (layer == contributors.length) {
            spriteIDs.add(builtSoFar);
            return;
        }

        for (int i = 0; i < contributors[layer].length; i++) {
            permutationRecursion(separator, contributors, layer + 1,
                    builtSoFar + separator + contributors[layer][i],
                    spriteIDs);
        }
    }

    public static String extractContributor(
            final String separator, final int i, final String spriteID
    ) {
        final String[] contributors = spriteID.split(separator);

        if (contributors.length <= i)
            return "";

        return contributors[i];
    }

    public static String extractContributor(
            final int i, final String spriteID
    ) {
        return extractContributor(STANDARD_SEPARATOR, i, spriteID);
    }

    public static boolean matchesAllContributors(
            final String separator, final String[] matchValues,
            final int[] contributorIndices, final String spriteID
    ) {
        if (contributorIndices.length != matchValues.length)
            return false;

        for (int i = 0; i < matchValues.length; i++)
            if (!matchValues[i].equals(extractContributor(
                    separator, contributorIndices[i], spriteID)))
                return false;

        return true;
    }

    public static boolean matchesAllContributors(
            final String[] matchValues, final int[] contributorIndices,
            final String spriteID
    ) {
        return matchesAllContributors(STANDARD_SEPARATOR, matchValues, contributorIndices, spriteID);
    }
}
