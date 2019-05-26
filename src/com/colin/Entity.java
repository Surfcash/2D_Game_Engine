package com.colin;

import processing.core.PVector;

import static com.colin.MainApp.game;

public abstract class Entity extends PhysicsObject {

    private int width, height;
    private PVector renderPoint;

    public Entity(float x, float y) {
        super(x, y);
        setSpriteRoot("e_");
        loadSprite();
    }

    public void renderWireframe() {
        getApplet().pushStyle();
        getApplet().noFill();
        getApplet().stroke(225, 200);
        getApplet().strokeWeight(1);
        getApplet().rectMode(getApplet().CORNER);
        getApplet().rect(getRenderPoint().x + game.getCamera().getPos().x, getRenderPoint().y + game.getCamera().getPos().y, getWidth(), getHeight());
        getApplet().popStyle();
    }

    public void renderHighlight() {
        getApplet().pushStyle();
        getApplet().fill(225, 200);
        getApplet().noStroke();
        getApplet().rectMode(getApplet().CORNER);
        getApplet().rect(getRenderPoint().x + game.getCamera().getPos().x, getRenderPoint().y + game.getCamera().getPos().y, getWidth(), getHeight());
        getApplet().popStyle();
    }

    public void renderSprite() {
        getApplet().pushStyle();
        getApplet().imageMode(getApplet().CORNER);
        getApplet().image(getSprite(), getRenderPoint().x + game.getCamera().getPos().x, getRenderPoint().y + game.getCamera().getPos().y, getWidth(), getHeight());
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
