package com.colin;

import processing.core.PApplet;

public class MainApp extends PApplet {
    public static MainApp applet;
    public static Game game;
    public long timeLast, timeCurrent;
    private static long deltaTime;
    static SpriteManager spriteManager;


    public static void main(String[] args) {
        String[] PApp = {"com.colin.MainApp"};
        PApplet.main(PApp);
    }

    public void setup() {
        applet = this;
        spriteManager = new SpriteManager(this);
        surface.setTitle("Colin's Workspace");
        surface.setResizable(false);
        surface.setLocation(-3, -3);
        smooth();
        game = new Game(this);
        timeLast = System.currentTimeMillis();
    }

    public void settings() {
        size(displayWidth, displayHeight - 61, P2D);
    }

    public void draw() {
        background(0);
        timeCurrent = System.currentTimeMillis();
        deltaTime = timeLast - timeCurrent;
        game.frame(deltaTime);
        timeLast = timeCurrent;
    }

    public long getDeltaTime() {
        return deltaTime;
    }

    public void keyPressed() {
        switch(keyCode) {
            case 37 : {
                game.getCamera().addPos(Tile.TILE_SIZE, 0);
                break;
            }
            case 38 : {
                game.getCamera().addPos(0, Tile.TILE_SIZE);
                break;
            }
            case 39 : {
                game.getCamera().addPos(-Tile.TILE_SIZE, 0);
                break;
            }
            case 40 : {
                game.getCamera().addPos(0, -Tile.TILE_SIZE);
                break;
            }
        }
        keyCode = 0;
    }
}