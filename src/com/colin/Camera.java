package com.colin;

import processing.core.PApplet;
import processing.core.PVector;

import static com.colin.Tile.TILE_SIZE;
import static com.colin.TileChunk.CHUNK_WIDTH;

public class Camera extends CoordinateObject{

    private PVector camBorder;

    public Camera(float x, float y) {
        super(x, y);
        setCamBorder(((Game.MAP_WIDTH * CHUNK_WIDTH * TILE_SIZE) - getApplet().width) / 2F, (Game.MAP_HEIGHT * CHUNK_WIDTH * TILE_SIZE) / 2F - getApplet().height);
    }

    public void render() {
    }

    public void update() {
        setPos(PApplet.constrain(getPos().x, -getCamBorder().x, getCamBorder().x), PApplet.constrain(getPos().y, -getCamBorder().y, getCamBorder().y));
    }

    public PVector getCamBorder() {
        return camBorder;
    }

    public void setCamBorder(float x, float y) {
        camBorder = new PVector(x, y);
    }

    public boolean offCamera(CoordinateObject obj, int buffer) {
        PVector trueLoc = new PVector(obj.getPos().x + getPos().x, obj.getPos().y + getPos().y);
        return((trueLoc.x < -buffer || trueLoc.x > getApplet().width + buffer) || (trueLoc.y < -buffer || trueLoc.y > getApplet().height + buffer));
    }
}
