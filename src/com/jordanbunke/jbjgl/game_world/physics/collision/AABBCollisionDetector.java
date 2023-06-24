package com.jordanbunke.jbjgl.game_world.physics.collision;

import com.jordanbunke.jbjgl.game_world.physics.vector.Vector;
import com.jordanbunke.jbjgl.game_world.physics.vector.Vector2D;
import com.jordanbunke.jbjgl.game_world.physics.vector.Vector3D;
import com.jordanbunke.jbub.math.MathPlus;

import java.util.Optional;

public class AABBCollisionDetector {
    @FunctionalInterface
    private interface OverlapCandidateLogic<A, R> {
        R apply(A a, A b, A c, A d);
    }

    public static Vector2D collisionCheck2D(
            final Collider<Vector2D> a, final Collider<Vector2D> b
    ) {
        return collisionCheck(a, b, new Vector2D(), AABBCollisionDetector::overlapCandidate2D);
    }

    public static Vector3D collisionCheck3D(
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
                overlapsX = (startA.x < startB.x && endA.x > startB.x) ||
                (startB.x < startA.x && endB.x > startA.x),
                overlapsY = (startA.y < startB.y && endA.y > startB.y) ||
                        (startB.y < startA.y && endB.y > startA.y);

        if (!overlapsX || !overlapsY)
            return Optional.empty();

        final double
                minDiffX = MathPlus.minMagnitude(startA.x - startB.x,
                startA.x - endB.x, endA.x - startB.x, endA.x - endB.x),
                minDiffY = MathPlus.minMagnitude(startA.y - startB.y,
                        startA.y - endB.y, endA.y - startB.y, endA.y - endB.y);

        return Optional.of(new Vector2D(minDiffX, minDiffY));
    }

    private static Optional<Vector3D> overlapCandidate3D(
            final Vector3D startA, final Vector3D endA,
            final Vector3D startB, final Vector3D endB
    ) {
        final boolean
                overlapsX = (startA.x < startB.x && endA.x > startB.x) ||
                (startB.x < startA.x && endB.x > startA.x),
                overlapsY = (startA.y < startB.y && endA.y > startB.y) ||
                        (startB.y < startA.y && endB.y > startA.y),
                overlapsZ = (startA.z < startB.z && endA.z > startB.z) ||
                        (startB.z < startA.z && endB.z > startA.z);

        if (!overlapsX || !overlapsY || !overlapsZ)
            return Optional.empty();

        final double
                minDiffX = MathPlus.minMagnitude(startA.x - startB.x,
                startA.x - endB.x, endA.x - startB.x, endA.x - endB.x),
                minDiffY = MathPlus.minMagnitude(startA.y - startB.y,
                        startA.y - endB.y, endA.y - startB.y, endA.y - endB.y),
                minDiffZ = MathPlus.minMagnitude(startA.z - startB.z,
                        startA.z - endB.z, endA.z - startB.z, endA.z - endB.z);

        return Optional.of(new Vector3D(minDiffX, minDiffY, minDiffZ));
    }
}
