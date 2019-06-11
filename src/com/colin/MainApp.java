package com.colin;

import processing.core.PApplet;
import processing.event.MouseEvent;

public class MainApp extends PApplet {
    public static MainApp applet;
    public static Game game;
    public long timeLast, timeCurrent;
    private static long deltaTime;
    public static SpriteManager spriteManager;


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
        noSmooth();
    }

    public void draw() {
        //Time Update
        timeCurrent = System.currentTimeMillis();
        deltaTime = timeCurrent - timeLast;

        background(0);
        spriteManager.tick();
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

    public void keyPressed() {
        switch(keyCode) {
            case 49 : {
                game.getUI().getHotbar().setSelectedSlot(0);
                break;
            }
            case 50 : {
                game.getUI().getHotbar().setSelectedSlot(1);
                break;
            }
            case 51 : {
                game.getUI().getHotbar().setSelectedSlot(2);
                break;
            }
            case 52 : {
                game.getUI().getHotbar().setSelectedSlot(3);
                break;
            }
            case 53 : {
                game.getUI().getHotbar().setSelectedSlot(4);
                break;
            }
            case 54 : {
                game.getUI().getHotbar().setSelectedSlot(5);
                break;
            }
            case 55 : {
                game.getUI().getHotbar().setSelectedSlot(6);
                break;
            }
            case 56 : {
                game.getUI().getHotbar().setSelectedSlot(7);
                break;
            }
            case 37 : {
                game.getUI().getHotbar().previousSlot();
                break;
            }
            case 39 : {
                game.getUI().getHotbar().nextSlot();
                break;
            }
        }
        keyCode = 0;
    }

    public void mouseWheel(MouseEvent event) {
        if(event.getCount() > 0) {
            game.getUI().getHotbar().nextSlot();
        } else {
            game.getUI().getHotbar().previousSlot();
        }
    }
}