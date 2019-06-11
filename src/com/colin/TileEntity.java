package com.colin;

import processing.core.PVector;

import static com.colin.MainApp.game;

public class TileEntity extends Entity {

    enum TileEntities {
        TREE("tree", 76, 128, (Tile.TILE_SIZE / 2F), -(Tile.TILE_SIZE / 8F)),
        ROCK("rock", 64, 64, (Tile.TILE_SIZE / 2F), (Tile.TILE_SIZE / 2F)),
        SUNFLOWER("sunflower", 64, 128, (Tile.TILE_SIZE / 2F), -(Tile.TILE_SIZE / 4F)),
        LILAC("lilac", 64, 64, (Tile.TILE_SIZE / 2F), (Tile.TILE_SIZE / 4F)),
        LAMP_POST("lamp_post", 64, 128, (Tile.TILE_SIZE / 2F), -(Tile.TILE_SIZE / 4F)),
        LONG_GRASS("long_grass", 64, 64, (Tile.TILE_SIZE / 2F), (Tile.TILE_SIZE / 2F));

        String name;
        int width, height;
        PVector renderBuffer;

        TileEntities(String name, int width, int height, float renderBufferX, float renderBufferY) {
            this.name = name;
            this.width = width;
            this.height = height;
            this.renderBuffer = new PVector(renderBufferX, renderBufferY);
        }

        @Override
        public String toString() {
            return name;
        }
    }

    private TileEntities type;

    public TileEntity(float x, float y, String id, int width, int height, float renderBufferX, float renderBufferY) {
        super(x, y, id, width, height);
        setRenderPoint(getPos().x + renderBufferX, getPos().y + renderBufferY);
    }

    public TileEntity(float x, float y, TileEntities type) {
        this(x, y, type.name, type.width, type.height, type.renderBuffer.x, type.renderBuffer.y);
        setType(type);
    }


    public void update() {
    }

    public void render() {
        renderSprite();
    }

    @Override
    public void renderSprite() {
        getApplet().pushStyle();
        getApplet().imageMode(getApplet().CENTER);
        getApplet().tint(getTint());
        getApplet().image(getSprite(), getRenderPoint().x + game.getCamera().getRealPos().x, getRenderPoint().y + game.getCamera().getRealPos().y);
        getApplet().popStyle();
    }

    public TileEntities getType() {
        return type;
    }

    public void setType(TileEntities val) {
        type = val;
    }

    public static TileEntities getEntityType(String str) {
        for(TileEntities i : TileEntities.values()) {
            if(str.equals(i.name)) {
                return i;
            }
        }
        return null;
    }
}
