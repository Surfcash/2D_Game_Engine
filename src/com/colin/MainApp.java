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
     * Needs to be replaced with an input handler
     */

    public static int type = 1;

    public void keyPressed() {
        switch(keyCode) {
            case 49 : {
                type = 1;
                break;
            }
            case 50 : {
                type = 2;
                break;
            }
            case 51 : {
                type = 3;
                break;
            }
            case 52 : {
                type = 4;
                break;
            }
            case 53 : {
                type = 5;
                break;
            }
            case 54 : {
                type = 6;
                break;
            }
            case 55 : {
                type = 7;
                break;
            }
            case 56 : {
                type = 8;
                break;
            }
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

    public static int getType() {
        return type;
    }
}