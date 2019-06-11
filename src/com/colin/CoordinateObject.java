package com.colin;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public abstract class CoordinateObject extends Renderable{
    private PVector pos;
    private PVector coordinate;

    public static ArrayList<CoordinateObject> coordinateObjects = new ArrayList<>();

    public CoordinateObject() {
        setPos(0, 0);
        coordinateObjects.add(this);
    }

    public CoordinateObject(float x, float y) {
        setPos(x, y);
        coordinateObjects.add(this);
    }



    public PVector getPos() {
        return new PVector(pos.x,pos.y);
    }

    public PVector getCoordinate() {
        return new PVector(coordinate.x,coordinate.y);
    }

    public int getCoordX() {
        return PApplet.floor(getCoordinate().x);
    }

    public int getCoordY() {
        return PApplet.floor(getCoordinate().y);
    }

    public void setPos(float x, float y) {
        this.pos = new PVector(x, y);
    }

    public void setPos(PVector vector) {
        setPos(vector.x, vector.y);
    }

    public void setCoordinate(float x, float y) {
        this.coordinate = new PVector(x, y);
    }

    public void setCoordinate(PVector vector) {
        setCoordinate(vector.x, vector.y);
    }

    public void addPos(float x, float y) {
        setPos(pos.x + x, pos.y + y);
    }

    public void addPos(PVector vector) {
        addPos(vector.x, vector.y);
    }

    public void subPos(float x, float y) {
        setPos(pos.x - x, pos.y - y);
    }

    public void subPos(PVector vector) {
        subPos(vector.x, vector.y);
    }

    public void addCoordinate(float x, float y) {
        setCoordinate(coordinate.x + x, coordinate.y + y);
    }

    public void addCoordinate(PVector vector) {
        addCoordinate(vector.x, vector.y);
    }
}
