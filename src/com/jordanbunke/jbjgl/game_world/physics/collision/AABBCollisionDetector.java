package com.jordanbunke.jbjgl.game_world.physics.collision;

import com.jordanbunke.jbjgl.game_world.physics.vector.Vector;
import com.jordanbunke.jbjgl.game_world.physics.vector.Vector2D;
import com.jordanbunke.jbjgl.game_world.physics.vector.Vector3D;
import com.jordanbunke.jbub.math.MathPlus;

import java.util.Optional;

public class AABBCollisionDetector {
    private static final int NONE = -1, X = 0, Y = 1, Z = 2;

    @FunctionalInterface
    private interface OverlapCandidateLogic<A, R> {
        R apply(A a, A b, A c, A d);
    }

    public static boolean doCollide2D(
            final Collider<Vector2D> a, final Collider<Vector2D> b
    ) {
        return !collisionOverlap2D(a, b).equals(new Vector2D());
    }

    public static boolean doCollide3D(
            final Collider<Vector3D> a, final Collider<Vector3D> b
    ) {
        return !collisionOverlap3D(a, b).equals(new Vector3D());
    }

    public static Vector2D collisionOverlap2D(
            final Collider<Vector2D> a, final Collider<Vector2D> b
    ) {
        return collisionCheck(a, b, new Vector2D(), AABBCollisionDetector::overlapCandidate2D);
    }

    public static Vector3D collisionOverlap3D(
            final Collider<Vector3D> a, final Collider<Vector3D> b
    ) {
        return collisionCheck(a, b, new Vector3D(), AABBCollisionDetector::overlapCandidate3D);
    }

    private static <T extends Vector<T>> T collisionCheck(
            final Collider<T> a, final Collider<T> b,
            final T noCollision,
            final OverlapCandidateLogic<T, Optional<T>> candidateLogic
    ) {
        final T posA = a.getPosition(), posB = b.getPosition();
        T largestOverlap = noCollision;

        for (AABB<T> boxA : a.boundingBoxes) {
            for (AABB<T> boxB : b.boundingBoxes) {
                final Optional<T> candidate =
                        candidateLogic.apply(boxA.start(posA), boxA.end(posA),
                                boxB.start(posB), boxB.end(posB));

                if (candidate.isEmpty())
                    continue;

                largestOverlap = MathPlus.findBest(largestOverlap, 0d,
                        T::magnitude, (l, r) -> l > r, largestOverlap, candidate.get());
            }
        }

        return largestOverlap;
    }

    private static Optional<Vector2D> overlapCandidate2D(
            final Vector2D startA, final Vector2D endA,
            final Vector2D startB, final Vector2D endB
    ) {
        final boolean
                overlapsX = aOverlapsB(startA.x, endA.x, startB.x) ||
                        aOverlapsB(startB.x, endB.x, startA.x),
                overlapsY = aOverlapsB(startA.y, endA.y, startB.y) ||
                        aOverlapsB(startB.y, endB.y, startA.y),
                aSpansBX = aSpansB(startA.x, endA.x, startB.x, endB.x),
                aSpansBY = aSpansB(startA.y, endA.y, startB.y, endB.y),
                bSpansAX = aSpansB(startB.x, endB.x, startA.x, endA.x),
                bSpansAY = aSpansB(startB.y, endB.y, startA.y, endA.y),
                spansX = aSpansBX || bSpansAX,
                spansY = aSpansBY || bSpansAY;

        if (!overlapsX || !overlapsY)
            return Optional.empty();

        final double
                minDiffX = MathPlus.minMagnitude(startA.x - startB.x,
                        startA.x - endB.x, endA.x - startB.x, endA.x - endB.x),
                minDiffY = MathPlus.minMagnitude(startA.y - startB.y,
                        startA.y - endB.y, endA.y - startB.y, endA.y - endB.y);
        final int minDimension = getMinDimension(
                new double[] { minDiffX, minDiffY }, spansX, spansY);

        final Vector2D
                bestCase = new Vector2D(minDimension == X
                        ? minDiffX : 0d, minDimension == Y ? minDiffY : 0d),
                naiveCase = new Vector2D(minDiffX, minDiffY),
                expulsionCase = new Vector2D(
                        MathPlus.minMagnitude(endA.x - startA.x, endB.x - startB.x),
                        MathPlus.minMagnitude(endA.y - startA.y, endB.y - startB.y)
                );

        final Vector2D result;

        if (!bestCase.equals(new Vector2D()))
            result = bestCase;
        else if (naiveCase.equals(new Vector2D()) &&
                ((aSpansBX && aSpansBY) || (bSpansAX && bSpansAY)))
            result = expulsionCase;
        else
            result = naiveCase;

        return Optional.of(result);
    }

    private static Optional<Vector3D> overlapCandidate3D(
            final Vector3D startA, final Vector3D endA,
            final Vector3D startB, final Vector3D endB
    ) {
        final boolean
                overlapsX = aOverlapsB(startA.x, endA.x, startB.x) ||
                        aOverlapsB(startB.x, endB.x, startA.x),
                overlapsY = aOverlapsB(startA.y, endA.y, startB.y) ||
                        aOverlapsB(startB.y, endB.y, startA.y),
                overlapsZ = aOverlapsB(startA.z, endA.z, startB.z) ||
                        aOverlapsB(startB.z, endB.z, startA.z),
                aSpansBX = aSpansB(startA.x, endA.x, startB.x, endB.x),
                aSpansBY = aSpansB(startA.y, endA.y, startB.y, endB.y),
                aSpansBZ = aSpansB(startA.z, endA.z, startB.z, endB.z),
                bSpansAX = aSpansB(startB.x, endB.x, startA.x, endA.x),
                bSpansAY = aSpansB(startB.y, endB.y, startA.y, endA.y),
                bSpansAZ = aSpansB(startB.z, endB.z, startA.z, endA.z),
                spansX = aSpansBX || bSpansAX,
                spansY = aSpansBY || bSpansAY,
                spansZ = aSpansBZ || bSpansAZ;

        if (!overlapsX || !overlapsY || !overlapsZ)
            return Optional.empty();

        final double
                minDiffX = MathPlus.minMagnitude(startA.x - startB.x,
                        startA.x - endB.x, endA.x - startB.x, endA.x - endB.x),
                minDiffY = MathPlus.minMagnitude(startA.y - startB.y,
                        startA.y - endB.y, endA.y - startB.y, endA.y - endB.y),
                minDiffZ = MathPlus.minMagnitude(startA.z - startB.z,
                        startA.z - endB.z, endA.z - startB.z, endA.z - endB.z);
        final int minDimension = getMinDimension(
                new double[] { minDiffX, minDiffY, minDiffZ },
                spansX, spansY, spansZ);

        final Vector3D
                bestCase = new Vector3D(minDimension == X ? minDiffX : 0d,
                        minDimension == Y ? minDiffY : 0d,
                        minDimension == Z ? minDiffZ : 0d),
                naiveCase = new Vector3D(minDiffX, minDiffY, minDiffZ),
                expulsionCase = new Vector3D(
                        MathPlus.minMagnitude(endA.x - startA.x, endB.x - startB.x),
                        MathPlus.minMagnitude(endA.y - startA.y, endB.y - startB.y),
                        MathPlus.minMagnitude(endA.z - startA.z, endB.z - startB.z)
                );

        final Vector3D result;

        if (!bestCase.equals(new Vector3D()))
            result = bestCase;
        else if (naiveCase.equals(new Vector3D()) &&
                ((aSpansBX && aSpansBY && aSpansBZ) ||
                        (bSpansAX && bSpansAY && bSpansAZ)))
            result = expulsionCase;
        else
            result = naiveCase;

        return Optional.of(result);
    }

    private static int getMinDimension(
            final double[] minDiffDims, final boolean... spansDims
    ) {
        assert minDiffDims.length == spansDims.length;

        double minDimension = Double.MAX_VALUE;
        double value;
        int index = NONE;

        for (int i = 0; i < minDiffDims.length; i++) {
            if (!spansDims[i]) {
                value = MathPlus.minMagnitude(minDimension, minDiffDims[i]);
                if (Math.abs(value) < Math.abs(minDimension) && Math.abs(value) > 0d) {
                    index = i;
                    minDimension = value;
                }
            }
        }

        return index;
    }

    private static boolean aOverlapsB(
            final double startAd, final double endAd, final double startBd
    ) {
        return startAd <= startBd && endAd > startBd;
    }

    private static boolean aSpansB(
            final double startAd, final double endAd,
            final double startBd, final double endBd
    ) {
        return startAd <= startBd && endAd >= endBd;
    }
}
