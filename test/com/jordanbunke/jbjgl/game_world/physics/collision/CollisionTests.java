package com.jordanbunke.jbjgl.game_world.physics.collision;

import com.jordanbunke.jbjgl.game_world.physics.vector.Vector;
import com.jordanbunke.jbjgl.game_world.physics.vector.Vector3D;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.BiFunction;

public class CollisionTests {

    @Test
    public void firstCollisionTest() {
        final AABB<Vector3D>[] shape = new AABB[] {
                new AABB<>(new Vector3D(1, 10, 1)),
                new AABB<>(new Vector3D(2, 2, 2))
        };

        collisionTestAuxiliary(shape, new Vector3D(2, 0, 1),
                new Vector3D(0, 4, 0), new Vector3D(10, 6, 5),
                AABBCollisionDetector::collisionCheck3D, new Vector3D() , 10,
                new boolean[] { false, false, false, false, false, true, false, false, false, false });
    }

    private <T extends Vector<T>> void collisionTestAuxiliary(
            final AABB<T>[] shape, final T movementVector,
            final T aPosition, final T bPosition,
            final BiFunction<Collider<T>, Collider<T>, T> collisionCheck,
            final T noCollision, final int n, final boolean[] expected
    ) {
        assert expected.length == n;

        final ExampleConcreteCollider<T>
                a = new ExampleConcreteCollider<>("A", aPosition, shape),
                b = new ExampleConcreteCollider<>("B", bPosition, shape);

        final boolean[] checks = new boolean[n];
        for (int i = 0; i < n; i++) {
            final T overlap = collisionCheck.apply(a, b);
            final boolean collision = !overlap.equals(noCollision);
            checks[i] = collision;

            System.out.println((i + 1) + "/" +  n + ": " + (collision ? "Collided!" : "Did not collide"));
            System.out.println(a + " & " + b);
            if (collision) {
                System.out.println("Overlap vector: " + overlap);
            }
            System.out.println();

            a.move(movementVector);
        }

        for (int i = 0; i < n; i++) {
            Assert.assertEquals(checks[i], expected[i]);
        }
    }
}
