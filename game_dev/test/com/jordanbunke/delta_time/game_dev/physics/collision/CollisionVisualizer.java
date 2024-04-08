package com.jordanbunke.delta_time.game_dev.physics.collision;

import com.jordanbunke.delta_time._core.ProgramContext;
import com.jordanbunke.delta_time.debug.GameDebugger;
import com.jordanbunke.delta_time.events.GameEvent;
import com.jordanbunke.delta_time.events.GameKeyEvent;
import com.jordanbunke.delta_time.events.Key;
import com.jordanbunke.delta_time._core.Program;
import com.jordanbunke.delta_time._core.GameManager;
import com.jordanbunke.delta_time.game_dev.ecs.basic_components.collider.Collider;
import com.jordanbunke.delta_time.game_dev.ecs.basic_components.collider.TriggerCollider;
import com.jordanbunke.delta_time.game_dev.physics.vector.Vector2D;
import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.io.InputEventLogger;
import com.jordanbunke.delta_time.window.GameWindow;

import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CollisionVisualizer implements ProgramContext {
    private static final String TITLE = "Collision Visualizer";
    private static final int CANVAS_W = 320, CANVAS_H = 180, SCALE_UP = 5, TRANSPARENCY = 100;
    private static final double GAME_HZ = 60d, UNIT = 1d, NANOS_IN_SECOND = 1E9,
            FACTOR = 100 / NANOS_IN_SECOND,
            PLAYER_WEIGHT = 1d, COLLISION_FACTOR = 1d;
    private static final Color
            COLLIDING_COLOR = new Color(255, 0, 0, TRANSPARENCY),
            NOT_COLLIDING_COLOR = new Color(0, 255, 0, TRANSPARENCY);

    private final ExampleConcreteCollider<Vector2D> player;
    private final Set<ExampleConcreteCollider<Vector2D>> colliders;
    private boolean movingLeft, movingRight, movingUp, movingDown;

    public CollisionVisualizer() {
        player = new ExampleConcreteCollider<>("Player", PLAYER_WEIGHT,
                new Vector2D(CANVAS_W / 2d, 10),
                new AABB<>(new Vector2D(7, 7)),
                new AABB<>(new Vector2D(3, 13)),
                new AABB<>(new Vector2D(13, 3)));

        colliders = new HashSet<>();
        colliders.add(player);

        for (int i = 0; i < 3; i++) {
            colliders.add(new ExampleConcreteCollider<>("Ref", 0.8 * i,
                    new Vector2D((CANVAS_W * (i + 1)) / 4d, CANVAS_H / 2d),
                    new AABB<>(new Vector2D(15, 15))));
        }
    }

    public static void main(String[] args) {
        final GameWindow gw = new GameWindow(TITLE, CANVAS_W * SCALE_UP,
                CANVAS_H * SCALE_UP, GameImage.dummy(), false);
        final GameManager gm = new GameManager(0, new CollisionVisualizer());
        final Program gl = new Program(gw, gm, GAME_HZ, GAME_HZ);
        gl.setCanvasSize(CANVAS_W, CANVAS_H);
    }

    @Override
    public void process(final InputEventLogger eventLogger) {
        final List<GameEvent> events = eventLogger.getUnprocessedEvents();

        for (GameEvent event : events) {
            if (event instanceof GameKeyEvent gke) {
                if (gke.key == Key.A) {
                    movingLeft = gke.action == GameKeyEvent.Action.PRESS;
                    gke.markAsProcessed();
                } else if (gke.key == Key.D) {
                    movingRight = gke.action == GameKeyEvent.Action.PRESS;
                    gke.markAsProcessed();
                } else if (gke.key == Key.W) {
                    movingUp = gke.action == GameKeyEvent.Action.PRESS;
                    gke.markAsProcessed();
                } else if (gke.key == Key.S) {
                    movingDown = gke.action == GameKeyEvent.Action.PRESS;
                    gke.markAsProcessed();
                }
            }
        }
    }

    @Override
    public void update(final double deltaTime) {
        // "Player" movement
        final Vector2D movement = new Vector2D(
                movingLeft ? -UNIT : (movingRight ? UNIT : 0d),
                movingUp ? -UNIT : (movingDown ? UNIT : 0d)
        ).normalize().scale(deltaTime * FACTOR);
        player.move(movement);

        // Update collision
        final Set<Collider<Vector2D>> processedColliders = new HashSet<>();

        for (Collider<Vector2D> reference : colliders) {
            reference.setColliding(false);
            processedColliders.add(reference);

            for (Collider<Vector2D> collider : colliders) {
                if (collider.equals(reference) || (processedColliders.contains(collider)))
                    continue;

                final Vector2D overlap = AABBCollisionDetector.collisionOverlap2D(reference, collider);
                final boolean colliding = !overlap.equals(new Vector2D());
                reference.setColliding(reference.isColliding() || colliding);

                if (colliding) {
                    if (reference instanceof ExampleConcreteCollider<Vector2D> rcc &&
                            collider instanceof ExampleConcreteCollider<Vector2D> ccc) {
                        rcc.handleCollision(ccc.getWeight(), overlap, COLLISION_FACTOR);
                        ccc.handleCollision(rcc.getWeight(), overlap.scale(-1), COLLISION_FACTOR);
                    } else if (reference instanceof TriggerCollider<Vector2D> rtc) {
                        rtc.handleCollision(collider);
                    } else if (collider instanceof TriggerCollider<Vector2D> ctc) {
                        ctc.handleCollision(reference);
                    }
                }
            }
        }
    }

    @Override
    public void render(final GameImage canvas) {

    }

    @Override
    public void debugRender(final GameImage canvas, final GameDebugger debugger) {
        for (ExampleConcreteCollider<Vector2D> collider : colliders)
            renderCollider(canvas, collider);
    }

    private void renderCollider(
            final GameImage canvas, final ExampleConcreteCollider<Vector2D> collider
    ) {
        for (AABB<Vector2D> boundingBox : collider.boundingBoxes) {
            final Vector2D start = boundingBox.beginning(collider.getPosition()),
                    end = boundingBox.end(collider.getPosition());
            final double x = start.x, y = start.y, width = end.x - x, height = end.y - y;
            canvas.fillRectangle(
                    collider.equals(player)
                            ? (collider.isColliding() ? COLLIDING_COLOR : NOT_COLLIDING_COLOR)
                            : new Color(0, 0, (int)(collider.getWeight() * 100), TRANSPARENCY),
                    (int) x, (int) y, (int) width, (int) height
            );
        }
    }
}
