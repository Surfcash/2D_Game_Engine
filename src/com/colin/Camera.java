package com.colin;

import processing.core.PApplet;
import processing.core.PVector;

public class Camera extends CoordinateObject{

    private final PApplet applet = Game.applet;

    public Camera() {
        super();
    }

    public Camera(float x, float y) {
        super(x, y);
    }

    public void render() {
    }

    public void update() {

    }

    public boolean offCamera(CoordinateObject obj) {
        int buffer = 100;
        PVector trueLoc = new PVector(obj.getPos().x + getPos().x, obj.getPos().y + getPos().y);
        return((trueLoc.x < -buffer || trueLoc.x > applet.width + buffer) || (trueLoc.y < -buffer || trueLoc.y > applet.height + buffer));
    }
}
