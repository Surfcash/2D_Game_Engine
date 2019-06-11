package com.colin;

import processing.core.PApplet;

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
    public TileEntity entity;
    private int depth;
    private float lightLevel;

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
        if(lightLevel == 0) {
            setTint(game.getClock().getCurrentTint());
        }
        setLightLevel(game.getChunkMap().getLightMap().getLight(getCoordX(), getCoordY()));
        if(getLightLevel() > 0) {
            updateLightingTint();
        }
        if(hasEntity()) {
            getEntity().update();
            getEntity().setTint(getTint());
        }
    }

    public void updateLightingTint() {
        int naturalTint = game.getClock().getCurrentTint();
        float lightValue = getLightLevel();
        float r = PApplet.map(lightValue, 0, 1, 65, 210);
        float g = PApplet.map(lightValue, 0, 1, 20, 200);
        float b = PApplet.map(lightValue, 0, 1, 65, 115);

        float tintR = getApplet().red(naturalTint);
        float tintG = getApplet().green(naturalTint);
        float tintB = getApplet().blue(naturalTint);

        int newColor = getApplet().color((r > tintR) ? r : tintR, (g > tintG) ? g : tintG, (b > tintB) ? b : tintB);
        setTint(newColor);
    }

    /*
     * RENDERS
     */

    public void render() {
        update();
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
        getApplet().rect(getPos().x + game.getCamera().getRealPos().x, getPos().y + game.getCamera().getRealPos().y, getTileSize(), getTileSize());
        getApplet().popStyle();
    }

    public void renderHighlight() {
        getApplet().pushStyle();
        getApplet().fill(128, 255, 128, 128);
        getApplet().stroke(128,255,128,225);
        getApplet().strokeWeight(2);
        getApplet().rectMode(getApplet().CORNER);
        getApplet().rect(getPos().x + game.getCamera().getRealPos().x, getPos().y + game.getCamera().getRealPos().y, getTileSize(), getTileSize());
        getApplet().popStyle();
        if(hasEntity()) {
            getEntity().renderHighlight();
        }
    }

    public void renderSprite() {
        getApplet().pushStyle();
        getApplet().imageMode(getApplet().CORNER);
        getApplet().tint(getTint());
        getApplet().image(getSprite(), getPos().x + game.getCamera().getRealPos().x, getPos().y + game.getCamera().getRealPos().y);
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

    public float getLightLevel() {
        return lightLevel;
    }

    public TileEntity getEntity() {
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
        if(hasEntity() && getDepth() < 0) {
            delEntity();
        }
        setSpriteID(tile.name);
    }

    public void setDepth(int num) {
        this.depth = num;
    }

    public void setEntity(TileEntity ent) {
        entity = ent;
    }

    public void setLightLevel(float num) {
        lightLevel = num;
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

    @Override
    public String toString() {
        String str = "[Tile] [Coordinates: ( " + PApplet.floor(getCoordinate().x) + ", " + PApplet.floor(getCoordinate().y) + " )]" + " [Type: ( " + getType().toString() + " )] [Depth: ( " + getDepth() + " )] [Light: ( " + getLightLevel() + " )]";
        if(hasEntity()) {
            str = str + " [Entity: ( " + getEntity().getSpriteID() + " )]";
        }
        return str;
    }

    public static Tiles getTileType(String str) {
        for(Tiles i : Tiles.values()) {
            if(str.equals(i.name)) {
                return i;
            }
        }
        return null;
    }
}
