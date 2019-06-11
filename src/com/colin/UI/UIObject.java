package com.colin.UI;

import com.colin.CoordinateObject;
import processing.core.PVector;

public abstract class UIObject extends CoordinateObject {

    private int width, height;
    private PVector renderPoint;

    public UIObject(float x, float y, String id) {
        super(x, y);
        setSpriteRoot("ui_");
        setSpriteID(id);
    }


    public void update() {

    }

    public void render() {
        renderSprite();
    }

    public void renderWireframe() {
        getApplet().pushStyle();
        getApplet().fill(255,50);
        getApplet().stroke(255, 225);
        getApplet().strokeWeight(2);
        getApplet().rectMode(getApplet().CORNER);
        getApplet().rect(getRenderPoint().x, getRenderPoint().y, getWidth(), getHeight());
        getApplet().popStyle();
    }

    public void renderSprite() {
        getApplet().pushStyle();
        getApplet().imageMode(getApplet().CORNER);
        getApplet().image(getSprite(), getRenderPoint().x, getRenderPoint().y);
        getApplet().popStyle();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public PVector getRenderPoint() {
        return renderPoint;
    }

    public void setWidth(int num) {
        width = num;
    }

    public void setHeight(int num) {
        height = num;
    }

    public void setRenderPoint(float x, float y) {
        this.renderPoint = new PVector(x, y);
    }

    public void setRenderPoint(PVector vector) {
        setRenderPoint(vector.x, vector.y);
    }
}
