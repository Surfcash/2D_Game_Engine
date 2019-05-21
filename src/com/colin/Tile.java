package com.colin;

import processing.core.PApplet;

import static com.colin.MainApp.game;

public class Tile extends CoordinateObject{

    private final PApplet applet = Game.applet;

    public static final int TILE_SIZE = 64;

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
        if(num == 0) {
            color = applet.color(0, 0, 135);
        } else if(num == 1) {
            color = applet.color(10, 10, 185);
        } else if(num == 2){
            color = applet.color(210, 180, 140);
        } else if(num == 3){
            color = applet.color(0, 128, 0);
        } else if(num == 4){
            color = applet.color(0, 90, 10);
        }
    }
}
