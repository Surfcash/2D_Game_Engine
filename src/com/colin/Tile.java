package com.colin;

import processing.core.PApplet;

import static com.colin.MainApp.game;

public class Tile extends CoordinateObject{

    private final PApplet applet = Game.applet;

    public static final int TILE_SIZE = 32;

    private int color;

    public Tile() {
        super();
    }

    public Tile(float x, float y) {
        super(x * TILE_SIZE, y * TILE_SIZE);
    }

    public void render() {
        renderWireFrame();
    }

    public void update() {

    }

    private void renderWireFrame() {
        applet.pushStyle();
        applet.fill(color);
        applet.noStroke();
        //applet.stroke(0, 255, 0);
        //applet.strokeWeight(1);
        applet.rectMode(applet.CORNER);
        applet.rect(getPos().x + game.cam.getPos().x, getPos().y + game.cam.getPos().y, getTileSize(), getTileSize());
        applet.popStyle();
    }

    public void renderHighlight() {
        applet.pushStyle();
        applet.fill(128, 255, 128);
        applet.noStroke();
        applet.rectMode(applet.CORNER);
        applet.rect(getPos().x + game.cam.getPos().x, getPos().y + game.cam.getPos().y, getTileSize(), getTileSize());
        applet.popStyle();
    }

    public int getTileSize() {
        return TILE_SIZE;
    }

    public void setColor(int num) {
        color = num;
    }
}
