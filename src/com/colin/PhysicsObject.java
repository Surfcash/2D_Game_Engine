package com.colin;

import processing.core.PApplet;
import processing.core.PVector;

public abstract class PhysicsObject extends CoordinateObject{

    private PVector vel, friction;
    private double weight;

    public PhysicsObject() {
        setVel(0, 0);
        setWeight(0);
    }

    public PhysicsObject(float x, float y) {
        this();
        setPos(x, y);
    }

    public PhysicsObject(float x, float y, float vx, float vy) {
        this(x,y);
        setVel(vx, vy);
    }

    public PhysicsObject(float x, float y, double weight) {
        this(x,y);
        setWeight(weight);
    }

    public PhysicsObject(float x, float y, float vx, float vy, double weight) {
        this(x,y,vx,vy);
        setWeight(weight);
    }

    /*VELOCITY*/
    public PVector getVel() {
        return new PVector(vel.x,vel.y);
    }

    public PVector getFriction() { return new PVector(friction.x, friction.y);}

    public void setVel(float x, float y) {
        this.vel = new PVector(x, y);
    }

    public void setVel(PVector vector) {
        setVel(vector.x, vector.y);
    }

    public void setFriction(float x, float y) {
        this.friction = new PVector(x, y);
    }

    public void setFriction(PVector vector) {
        setFriction(vector.x, vector.y);
    }

    public void addVel(float x, float y) {
        setVel(vel.x + x, vel.y + y);
    }

    public void addVel(PVector vector) {
        addVel(vector.x, vector.y);
    }

    public void applyFriction() {
        float fixX = 0;
        float fixY = 0;
        if(getVel().x != 0) {
            float fixedFriction = (PApplet.abs(getVel().x) >= getFriction().x) ? getFriction().x : PApplet.abs(getVel().x);
            fixX = (getVel().x > 0) ? -fixedFriction : fixedFriction;
        }
        if(getVel().y != 0) {
            float fixedFriction = (PApplet.abs(getVel().y) >= getFriction().y) ? getFriction().y : PApplet.abs(getVel().y);
            fixY = (getVel().y > 0) ? -fixedFriction : fixedFriction;
        }
        addVel(fixX, fixY);
    }

    /*WEIGHT*/
    public double getWeight() {
        return weight;
    }

    public void setWeight(double num) {
        weight = num;
    }

    public void addWeight(double num) {
        weight += num;
    }
}
