package com.jordanbunke.delta_time.game_dev.ecs.basic_components.camera;

import com.jordanbunke.delta_time.debug.GameDebugger;
import com.jordanbunke.delta_time.game_dev.ecs.basic_components.collider.Collider;
import com.jordanbunke.delta_time.game_dev.ecs.basic_components.sprite.SpriteComponent;
import com.jordanbunke.delta_time.game_dev.physics.vector.Vector2D;
import com.jordanbunke.delta_time.game_dev.physics.vector.Vector3D;
import com.jordanbunke.delta_time.game_dev.ecs.GameEntity;
import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.utility.math.Coord2D;
import com.jordanbunke.delta_time.utility.math.MathPlus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class BasicTopDownCameraComponent extends CameraComponent<Vector3D> {
    private static final Function<GameImage, GameImage> IDENTITY = x -> x;
    private static final double NAIVE_Y = 10000d;

    // rendering math
    private final int canvasWidth, canvasHeight;

    private boolean xIsAcross;
    private double acrossRange, alongRange, yPerspectiveMultiplier;
    private Function<GameImage, GameImage> spriteFilter;

    // follow math
    private double snapDistanceThreshold, catchUpRatePerTick;

    private GameEntity<Vector3D> target;

    public BasicTopDownCameraComponent(
            final int canvasWidth, final int canvasHeight,
            final boolean xIsAcross, final double acrossRange, final double alongRange,
            final double yPerspectiveMultiplier,
            final double snapDistanceThreshold, final double catchUpRatePerTick,
            final GameEntity<Vector3D> target,
            final Function<GameImage, GameImage> spriteFilter
    ) {
        this.target = target;

        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        this.xIsAcross = xIsAcross;
        this.acrossRange = acrossRange;
        this.alongRange = alongRange;
        this.yPerspectiveMultiplier = yPerspectiveMultiplier;

        this.spriteFilter = spriteFilter;

        this.snapDistanceThreshold = snapDistanceThreshold;
        this.catchUpRatePerTick = catchUpRatePerTick;
    }

    @Override
    public void start() {
        final Vector3D pos = getEntity().getPosition();
        getEntity().setPosition(new Vector3D(pos.x, NAIVE_Y, pos.z));
    }

    public BasicTopDownCameraComponent(
            final int canvasWidth, final int canvasHeight,
            final boolean xIsAcross, final double acrossRange, final double alongRange,
            final double yPerspectiveMultiplier,
            final double snapDistanceThreshold, final double catchUpRatePerTick,
            final GameEntity<Vector3D> target
    ) {
        this(canvasWidth, canvasHeight, xIsAcross, acrossRange, alongRange,
                yPerspectiveMultiplier, snapDistanceThreshold,
                catchUpRatePerTick, target, IDENTITY);
    }

    @Override
    public void update(final double deltaTime) {
        followTarget();
    }

    private void followTarget() {
        if (target == null)
            return;

        final Vector2D targetXZ = new Vector2D(target.getPosition().x, target.getPosition().z),
                cameraXZ = new Vector2D(getEntity().getPosition().x, getEntity().getPosition().z);

        final Vector2D xzSeparation = targetXZ.displace(cameraXZ.scale(-1d));
        final Vector3D separation = new Vector3D(xzSeparation.x, 0d, xzSeparation.y);

        if (separation.magnitude() < snapDistanceThreshold)
            getEntity().move(separation);
        else
            getEntity().move(separation.scale(catchUpRatePerTick));
    }

    @Override
    public void draw(
            final GameImage canvas, final Collection<GameEntity<Vector3D>> entities
    ) {
        final List<GameEntity<Vector3D>> entityList = new ArrayList<>();
        entities.stream().filter(e -> e.hasComponent(SpriteComponent.class))
                .forEach(entityList::add);
        entityList.sort(new DefaultComparator());

        entityList.forEach(entity -> {
            final Coord2D lensPosition = getLensPosition(entity.getPosition());
            entity.executeIfComponentPresent(SpriteComponent.class, ss -> {
                final GameImage sprite = ss.getSprite();
                final Coord2D offset = ss.getSpriteOffset();

                canvas.draw(spriteFilter.apply(sprite),
                        lensPosition.x + offset.x, lensPosition.y + offset.y);
            });
        });
    }

    public Coord2D getLensPosition(final Vector3D worldPosition) {
        final double diffX = getEntity().getPosition().x - worldPosition.x,
                diffZ = getEntity().getPosition().z - worldPosition.z;

        final int y = (int)(yPerspectiveMultiplier * worldPosition.y);
        final double diffAcross = xIsAcross ? diffX : diffZ,
                diffAlong = xIsAcross ? diffZ : diffX;

        final int across = (int)(canvasWidth * ((acrossRange / 2d) -
                        diffAcross) / acrossRange),
                along = (int)(canvasHeight * ((alongRange / 2d) -
                        diffAlong) / alongRange);

        return new Coord2D(across, along + y);
    }

    @Override
    public void debugDraw(
            final GameImage canvas, final GameDebugger debugger,
            final Collection<GameEntity<Vector3D>> entities
    ) {

    }

    public boolean isXAcross() {
        return xIsAcross;
    }

    public void setDimensionAcrossScreen(final boolean xIsAcross) {
        this.xIsAcross = xIsAcross;
    }

    public void switchDimension() {
        xIsAcross = !xIsAcross;
    }

    public double getAcrossRange() {
        return acrossRange;
    }

    public void setAcrossRange(final double xRange) {
        this.acrossRange = xRange;
    }

    public double getAlongRange() {
        return alongRange;
    }

    public void setAlongRange(final double xRange) {
        this.alongRange = xRange;
    }

    public double getYPerspectiveMultiplier() {
        return yPerspectiveMultiplier;
    }

    public void setYPerspectiveMultiplier(final double yPerspectiveMultiplier) {
        this.yPerspectiveMultiplier = yPerspectiveMultiplier;
    }

    public void setCatchUpRatePerTick(final double catchUpRatePerTick) {
        this.catchUpRatePerTick = catchUpRatePerTick;
    }

    public void setSnapDistanceThreshold(final double snapDistanceThreshold) {
        this.snapDistanceThreshold = snapDistanceThreshold;
    }

    public void setSpriteFilter(final Function<GameImage, GameImage> spriteFilter) {
        this.spriteFilter = spriteFilter;
    }

    public void setTarget(GameEntity<Vector3D> target) {
        this.target = target;
    }

    private class DefaultComparator implements Comparator<GameEntity<Vector3D>> {
        @Override
        public int compare(final GameEntity<Vector3D> e1, final GameEntity<Vector3D> e2) {
            final Collider<Vector3D> c1 = e1.getComponent(Collider.class);
            final Collider<Vector3D> c2 = e2.getComponent(Collider.class);

            if (c1 == null && c2 == null) {
                return Double.compare(
                        e1.getPosition().z - (e1.getPosition().y * yPerspectiveMultiplier),
                        e2.getPosition().z - (e2.getPosition().y * yPerspectiveMultiplier));
            } else if (c1 == null || c2 == null)
                return 0;

            final Vector3D beg1 = c1.beginning(), beg2 = c2.beginning(),
                    end1 = c1.end(), end2 = c2.end();

            if (beg1.y < beg2.y && (end1.y < beg2.y || (end1.y < end2.y &&
                    MathPlus.minMagnitude(end1.y - end2.y, end1.y - beg2.y) ==
                            end1.y - beg2.y)))
                return Double.compare(end1.z, beg2.z);
            else if (beg2.y < beg1.y && (end2.y < beg1.y || (end2.y < end1.y &&
                    MathPlus.minMagnitude(end2.y - end1.y, end2.y - beg1.y) ==
                            end2.y - beg1.y)))
                return Double.compare(beg1.z, end2.z);
            else
                return Double.compare(end1.z, end2.z);
        }
    }
}
