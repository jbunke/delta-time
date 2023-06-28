package com.jordanbunke.delta_time.game_world.ecs.basic_components.camera;

import com.jordanbunke.delta_time.debug.GameDebugger;
import com.jordanbunke.delta_time.game_world.ecs.GameEntity;
import com.jordanbunke.delta_time.game_world.ecs.basic_components.sprite.SpriteComponent3D;
import com.jordanbunke.delta_time.game_world.physics.vector.Vector2D;
import com.jordanbunke.delta_time.game_world.physics.vector.Vector3D;
import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.utility.Coord2D;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class BasicTopDownCameraComponent extends CameraComponent<Vector3D> {
    private static final Function<GameImage, GameImage> IDENTITY = x -> x;

    // rendering math
    private final int canvasWidth, canvasHeight;

    private boolean xIsAcross;
    private double xRange, zRange, yPerspectiveMultiplier;
    private Function<GameImage, GameImage> spriteFilter;

    // follow math
    private double snapDistanceThreshold, catchUpRatePerTick;

    private GameEntity<Vector3D> target;

    public BasicTopDownCameraComponent(
            final int canvasWidth, final int canvasHeight,
            final boolean xIsAcross, final double xRange, final double zRange,
            final double yPerspectiveMultiplier,
            final double snapDistanceThreshold, final double catchUpRatePerTick,
            final GameEntity<Vector3D> target,
            final Function<GameImage, GameImage> spriteFilter
    ) {
        this.target = target;

        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        this.xIsAcross = xIsAcross;
        this.xRange = xRange;
        this.zRange = zRange;
        this.yPerspectiveMultiplier = yPerspectiveMultiplier;

        this.spriteFilter = spriteFilter;

        this.snapDistanceThreshold = snapDistanceThreshold;
        this.catchUpRatePerTick = catchUpRatePerTick;
    }

    public BasicTopDownCameraComponent(
            final int canvasWidth, final int canvasHeight,
            final boolean xIsAcross, final double xRange, final double zRange,
            final double yPerspectiveMultiplier,
            final double snapDistanceThreshold, final double catchUpRatePerTick,
            final GameEntity<Vector3D> target
    ) {
        this(canvasWidth, canvasHeight, xIsAcross, xRange, zRange,
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
            getEntity().setPosition(target.getPosition());
        else
            getEntity().move(separation.scale(catchUpRatePerTick));
    }

    @Override
    public void draw(
            final GameImage canvas, final Collection<GameEntity<Vector3D>> entities
    ) {
        final List<GameEntity<Vector3D>> entityList = new ArrayList<>(entities);
        entityList.sort(Comparator.comparingDouble(e -> e.getPosition().z -
                e.getPosition().y));

        entityList.forEach(entity -> {
            final Coord2D lensPosition = getLensPosition(entity.getPosition());
            entity.executeIfComponentPresent(SpriteComponent3D.class, ss -> {
                final GameImage sprite = ss.getSprite();
                final Coord2D offset = ss.getSpriteOffset();

                canvas.draw(spriteFilter.apply(sprite),
                        lensPosition.x + offset.x, lensPosition.y + offset.y);
            });
        });
    }

    protected Coord2D getLensPosition(final Vector3D worldPosition) {
        final double diffX = getEntity().getPosition().x - worldPosition.x,
                diffZ = getEntity().getPosition().z - worldPosition.z;

        final int x = (int)(canvasWidth * ((xRange / 2d) - diffX) / xRange),
                z = (int)(canvasHeight * ((zRange / 2d) - diffZ) / zRange),
                y = (int)(yPerspectiveMultiplier * worldPosition.y);

        if (xIsAcross)
            return new Coord2D(x, z + y);
        else
            return new Coord2D(z, x + y);
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

    public double getXRange() {
        return xRange;
    }

    public void setXRange(final double xRange) {
        this.xRange = xRange;
    }

    public double getZRange() {
        return zRange;
    }

    public void setZRange(final double xRange) {
        this.zRange = xRange;
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
}
