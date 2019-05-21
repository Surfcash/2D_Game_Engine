package com.colin;

import processing.core.PApplet;

public class MainApp extends PApplet {
    public static MainApp applet;
    public static Game game;

    public static void main(String[] args) {
        String[] PApp = {"com.colin.MainApp"};
        PApplet.main(PApp);
    }

    public void setup() {
        applet = this;
        surface.setTitle("Colin's Workspace");
        surface.setResizable(false);
        surface.setLocation(-3, -3);
        smooth();
        game = new Game(this);
    }

    public void settings() {
        size(displayWidth, displayHeight - 61, P2D);
    }

    public void draw() {
        background(0);
        game.frame();
    }

    public void keyPressed() {
        switch(keyCode) {
            case 37 : {
                game.cam.addPos(64, 0);
                break;
            }
            case 38 : {
                game.cam.addPos(0, 64);
                break;
            }
            case 39 : {
                game.cam.addPos(-64, 0);
                break;
            }
            case 40 : {
                game.cam.addPos(0, -64);
                break;
            }
        }
        keyCode = 0;
    }
}