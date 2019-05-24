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
        //Time Update
        timeCurrent = System.currentTimeMillis();
        deltaTime = timeLast - timeCurrent;

        background(0);
        game.frame();

        //Time Update
        timeLast = timeCurrent;
    }

    public static long getDeltaTime() {
        return deltaTime;
    }

    /*
     * Needs to be replaced with a command handler
     */

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

    public void mouseClicked() {
        modifyTile(mouseButton);
    }

    public void mouseDragged() {
        modifyTile(mouseButton);
    }

    private void modifyTile(int mouseButton) {
        Tile tile = game.getHoveredTile();
        if(tile != null) {
            if(mouseButton == LEFT) {
                tile.setType(Tile.Tiles.WATER);
            }

            else if(mouseButton == RIGHT) {
                tile.setType(Tile.Tiles.GRASS);
            }
        }
    }
}