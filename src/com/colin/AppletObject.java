package com.colin;

import processing.core.PApplet;

public abstract class AppletObject {
    private static final PApplet applet = Game.applet;

    public AppletObject() {
    }

    public static PApplet getApplet() {
        return applet;
    }
}
