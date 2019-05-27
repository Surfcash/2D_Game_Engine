package com.colin;

import static com.colin.MainApp.game;

public class Tile extends CoordinateObject{

    enum Tiles {
        DEFAULT("default"),
        DEEP_WATER("deep_water"),
        WATER("water"),
        SAND("sand"),
        SANDY_GRASS("sandy_grass"),
        GRASS("grass"),
        DEEP_GRASS("deep_grass"),
        SLATE("slate"),
        DEEP_SLATE("deep_slate");

        String name;

        Tiles(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    /*
     * VARS
     */

    public static final int TILE_SIZE = 64;

    private Tiles type;
    public Entity entity;
    private int depth;

    /*
     * CONSTRUCTORS
     */

    public Tile(float x, float y, Tiles tile) {
        super(x * TILE_SIZE, y * TILE_SIZE);
        setSpriteRoot("t_");
        setCoordinate(x, y);
        setType(tile);
    }

    /*
     * UPDATERS
     */

    public void update() {

    }

    /*
     * RENDERS
     */

    public void render() {
        renderSprite();
        if(hasEntity()) {
            getEntity().render();
        }
    }

    private void renderWireframe() {
        getApplet().pushStyle();
        getApplet().noFill();
        getApplet().stroke(0, 255, 0);
        getApplet().strokeWeight(1);
        getApplet().rectMode(getApplet().CORNER);
        getApplet().rect(getPos().x + game.getCamera().getPos().x, getPos().y + game.getCamera().getPos().y, getTileSize(), getTileSize());
        getApplet().popStyle();
    }

    public void renderHighlight() {
        getApplet().pushStyle();
        getApplet().fill(128, 255, 128, 128);
        getApplet().stroke(128,255,128,225);
        getApplet().strokeWeight(2);
        getApplet().rectMode(getApplet().CORNER);
        getApplet().rect(getPos().x + game.getCamera().getPos().x, getPos().y + game.getCamera().getPos().y, getTileSize(), getTileSize());
        getApplet().popStyle();
        if(hasEntity()) {
            getEntity().renderHighlight();
        }
    }

    public void renderSprite() {
        getApplet().pushStyle();
        getApplet().imageMode(getApplet().CORNER);
        getApplet().image(getSprite(), getPos().x + game.getCamera().getPos().x, getPos().y + game.getCamera().getPos().y);
        getApplet().popStyle();
    }

    /*
     * GETTERS
     */

    public Tiles getType() {
        return type;
    }

    public int getTileSize() {
        return TILE_SIZE;
    }

    public int getDepth() {
        return depth;
    }

    public Entity getEntity() {
        return entity;
    }

    /*
     * SETTERS
     */

    public void setType(Tiles tile) {
        type = tile;
        if(tile == Tiles.WATER) {
            setDepth(-1);
        } else if(tile == Tiles.DEEP_WATER) {
            setDepth(-2);
        } else {
            setDepth(0);
        }
        setSpriteID(tile.name);
        loadSprite();
    }

    public void setDepth(int num) {
        this.depth = num;
    }

    public void setEntity(Entity ent) {
        entity = ent;
    }

    public void delEntity() {
        entity = null;
    }

    /*
     * MODIFIERS
     */

    public void addDepth(int num) {
        this.depth += num;
    }

    /*
     * QUERIES
     */

    public boolean hasEntity() {
        return getEntity() != null;
    }
}
