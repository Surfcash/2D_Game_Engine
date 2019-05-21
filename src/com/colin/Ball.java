package com.colin;

import processing.core.PApplet;
import processing.core.PVector;

public class Ball extends PhysicsObject{
    private int radius;

    private final PApplet applet = Game.applet;
    private PhysicsObject pObject;


    public Ball() {
        setRadius(0);
    }

    public Ball(float x, float y, int radius) {
        super(x,y);
        setRadius(radius);
    }

    public Ball(float x, float y, float vx, float vy, int radius) {
        super(x, y, vx, vy);
        setRadius(radius);
    }

    public Ball(float x, float y, float vx, float vy, double weight, int radius) {
        super(x, y, vx, vy, weight);
        setRadius(radius);
    }

    public void render() {
        applet.pushStyle();
        applet.noFill();
        applet.stroke(255);
        applet.strokeWeight(2);
        applet.ellipse(getPos().x,getPos().y,getRadius() * 2,getRadius() * 2);
        applet.popStyle();
    }

    public void update() {
        addPos(getVel());
        bounceBoundBorder();
    }

    private void bounceBoundBorder() {
        if(getPos().x + getRadius() >= applet.width) {
            setVel(-getVel().x,getVel().y);
            setPos(applet.width - getRadius(), getPos().y);
        } else if(getPos().x - getRadius() <= 0) {
            setVel(-getVel().x,getVel().y);
            setPos(getRadius(), getPos().y);
        }
        if(getPos().y + getRadius() >= applet.height) {
            setVel(getVel().x,-getVel().y);
            setPos(getPos().x, applet.height - getRadius());
        } else if(getPos().y - getRadius() <= 0) {
            setVel(getVel().x,-getVel().y);
            setPos(getPos().x, getRadius());
        }
    }

    public void bounceBoundBall(Ball ball) {
        double dist = PApplet.dist(ball.getPos().x, ball.getPos().y, getPos().x, getPos().y);
        double minDist = getRadius() + ball.getRadius();
        if(dist <= minDist) {
            addPos(-getVel().x, -getVel().y);
            ball.addPos(-ball.getVel().x, -ball.getVel().y);
            PVector temp = getVel();
            setVel(ball.getVel());
            ball.setVel(temp);
        }
    }

    /*RADIUS*/
    public int getRadius() {
        return radius;
    }

    public void setRadius(int num) {
        radius = num;
    }

    public void addRadius(int num) {
        radius += num;
    }
}
