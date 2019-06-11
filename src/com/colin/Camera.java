package com.colin;

import processing.core.PApplet;
import processing.core.PVector;

import static com.colin.Tile.TILE_SIZE;
import static com.colin.TileChunk.CHUNK_WIDTH;

public class Camera extends PhysicsObject{

    private static PVector CAMERA_FRICTION = new PVector(2.5F, 2.5F);

    private PVector camBorder;
    private PVector realPosition;

    public Camera(float x, float y) {
        super(x, y);
        setRealPos(getApplet().width / 2F, getApplet().height / 2F);
        setFriction(CAMERA_FRICTION.x, CAMERA_FRICTION.y);
        setCamBorder((((Game.MAP_WIDTH * CHUNK_WIDTH * TILE_SIZE) - getApplet().width) / 2F), (((Game.MAP_HEIGHT * CHUNK_WIDTH * TILE_SIZE) - getApplet().height) / 2F));
    }

    public void render() {
    }

    public void update() {
        applyFriction();
        addPos(getVel());
        float fixedX = PApplet.constrain(getPos().x, -getCamBorder().x, getCamBorder().x) - getPos().x;
        float fixedY = PApplet.constrain(getPos().y, -getCamBorder().y, getCamBorder().y) - getPos().y;
        addPos(fixedX, fixedY);
    }

    public PVector getCamBorder() {
        return camBorder;
    }

    public PVector getRealPos() {

        return realPosition;
    }

    public void setCamBorder(float x, float y) {
        camBorder = new PVector(x, y);
    }

    public void setRealPos(float x, float y) {
        realPosition = new PVector(x, y);
    }

    @Override
    public void addPos(float x, float y) {
      super.addPos(x, y);
      setRealPos(getRealPos().x + x, getRealPos().y + y);
    }

    @Override
    public void subPos(float x, float y) {
        super.subPos(x, y);
        setRealPos(getRealPos().x - x, getRealPos().y - y);
    }

    public boolean chunkOffCamera(TileChunk obj, int buffer) {
        PVector trueLoc = new PVector(obj.getPos().x + getRealPos().x, obj.getPos().y + getRealPos().y);
        boolean checkX = (obj.getPos().x > 0) ? trueLoc.x < 0 || trueLoc.x > getApplet().width + buffer : trueLoc.x < -buffer || trueLoc.x > getApplet().width;
        boolean checkY = (obj.getPos().y > 0) ? trueLoc.y < 0 || trueLoc.y > getApplet().height + buffer : trueLoc.y < -buffer || trueLoc.y > getApplet().height;
        return (checkX || checkY);
    }

    public boolean coordinateOffCamera(CoordinateObject obj, int buffer) {
        PVector trueLoc = new PVector(obj.getPos().x + getRealPos().x, obj.getPos().y + getRealPos().y);
        boolean checkX = trueLoc.x < -buffer|| trueLoc.x > getApplet().width;
        boolean checkY = trueLoc.y < -buffer || trueLoc.y > getApplet().height;
        return (checkX || checkY);
    }

    @Override
    public String toString() {
        return "Camera: ( " +  PApplet.floor(getPos().x) + ", " + PApplet.floor(getPos().y) + " )" + " ( " + PApplet.floor(getCamBorder().x) + ", " + PApplet.floor(getCamBorder().y) + " )";
    }
}
