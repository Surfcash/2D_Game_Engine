package com.colin;

import processing.core.PApplet;
import processing.core.PVector;

import static com.colin.MainApp.game;

public class Tile extends CoordinateObject{

    private final PApplet applet = Game.applet;

    public static final int TILE_SIZE = 16;

    private int color;
    private PVector coordinate;

    public Tile() {
        super();
    }

    public Tile(float x, float y) {
        super(x * TILE_SIZE, y * TILE_SIZE);
        setCoordinate(x, y);
        setColor(0);
    }

    public void render() {
        renderFill();
    }

    public void update() {

    }

    private void renderWireFrame() {
        applet.pushStyle();
        applet.noFill();
        applet.stroke(0, 255, 0);
        applet.strokeWeight(1);
        applet.rectMode(applet.CORNER);
        applet.rect(getPos().x + game.cam.getPos().x, getPos().y + game.cam.getPos().y, getTileSize(), getTileSize());
        applet.popStyle();
    }

    private void renderFill() {
        applet.pushStyle();
        applet.fill(color);
        applet.noStroke();
        applet.rectMode(applet.CORNER);
        applet.rect(getPos().x + game.cam.getPos().x, getPos().y + game.cam.getPos().y, getTileSize(), getTileSize());
        applet.popStyle();
    }

    public void renderHighlight() {
        applet.pushStyle();
        applet.fill(128, 255, 128, 128);
        applet.noStroke();
        applet.rectMode(applet.CORNER);
        applet.rect(getPos().x + game.cam.getPos().x, getPos().y + game.cam.getPos().y, getTileSize(), getTileSize());
        applet.popStyle();
    }

    public int getTileSize() {
        return TILE_SIZE;
    }

    public PVector getCoordinate() {
        return new PVector(coordinate.x,coordinate.y);
    }

    public void setCoordinate(float x, float y) {
        this.coordinate = new PVector(x, y);
    }

    public void setCoordinate(PVector vector) {
        setCoordinate(vector.x, vector.y);
    }

    public void addCoordinate(float x, float y) {
        setCoordinate(coordinate.x + x, coordinate.y + y);
    }

    public void addCoordinate(PVector vector) {
        addCoordinate(vector.x, vector.y);
    }

    public void setColor(int num) {
        color = num;
    }

    public int getColor() {
        return color;
    }
}
