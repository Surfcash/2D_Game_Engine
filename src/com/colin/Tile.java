package com.colin;

import processing.core.PImage;
import processing.core.PVector;

import static com.colin.MainApp.game;
import static com.colin.MainApp.spriteManager;

public class Tile extends CoordinateObject{

    enum Tiles {
        DEFAULT("default"),
        GRASS("grass"),
        DEEP_GRASS("deep_grass"),
        WATER("water"),
        DEEP_WATER("deep_water"),
        SAND("sand"),
        SANDY_GRASS("sandy_grass"),
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

    public static final int TILE_SIZE = 32;

    private int color;
    private Tiles type;
    private PImage sprite;
    private PVector coordinate;

    /*
     * CONSTRUCTORS
     */

    public Tile() {
        super();
        setType(Tiles.DEFAULT);
    }

    public Tile(float x, float y, Tiles tile) {
        super(x * TILE_SIZE, y * TILE_SIZE);
        setCoordinate(x, y);
        setColor(0);
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
    }

    private void renderWireFrame() {
        getApplet().pushStyle();
        getApplet().noFill();
        getApplet().stroke(0, 255, 0);
        getApplet().strokeWeight(1);
        getApplet().rectMode(getApplet().CORNER);
        getApplet().rect(getPos().x + game.getCamera().getPos().x, getPos().y + game.getCamera().getPos().y, getTileSize(), getTileSize());
        getApplet().popStyle();
    }

    private void renderFill() {
        getApplet().pushStyle();
        getApplet().fill(getColor());
        getApplet().noStroke();
        getApplet().rectMode(getApplet().CORNER);
        getApplet().rect(getPos().x + game.getCamera().getPos().x, getPos().y + game.getCamera().getPos().y, getTileSize(), getTileSize());
        getApplet().popStyle();
    }

    public void renderHighlight() {
        getApplet().pushStyle();
        getApplet().fill(128, 255, 128, 128);
        getApplet().noStroke();
        getApplet().rectMode(getApplet().CORNER);
        getApplet().rect(getPos().x + game.getCamera().getPos().x, getPos().y + game.getCamera().getPos().y, getTileSize(), getTileSize());
        getApplet().popStyle();
    }

    public void renderSprite() {
        getApplet().pushStyle();
        getApplet().imageMode(getApplet().CORNER);
        getApplet().image(sprite, getPos().x + game.getCamera().getPos().x, getPos().y + game.getCamera().getPos().y);
        getApplet().popStyle();
    }

    /*
     * GETTERS
     */

    public int getColor() {
        return color;
    }

    public Tiles getType() {
        return type;
    }

    public int getTileSize() {
        return TILE_SIZE;
    }

    public PVector getCoordinate() {
        return new PVector(coordinate.x,coordinate.y);
    }

    /*
     * SETTERS
     */

    public void setColor(int num) {
        color = num;
    }

    public void setCoordinate(float x, float y) {
        this.coordinate = new PVector(x, y);
    }

    public void setCoordinate(PVector vector) {
        setCoordinate(vector.x, vector.y);
    }

    public void setType(Tiles tile) {
        type = tile;
        loadSprite();

    }

    /*
     * MODIFIERS
     */

    public void addCoordinate(float x, float y) {
        setCoordinate(coordinate.x + x, coordinate.y + y);
    }

    public void addCoordinate(PVector vector) {
        addCoordinate(vector.x, vector.y);
    }

    void loadSprite() {
        sprite = spriteManager.getSprite("t_" + type.name);
    }
}
