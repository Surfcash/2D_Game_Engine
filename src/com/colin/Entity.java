package com.colin;

import processing.core.PImage;
import processing.core.PVector;

import static com.colin.MainApp.game;
import static com.colin.MainApp.spriteManager;

public abstract class Entity extends PhysicsObject {

    private int width, height;
    private PVector renderPoint;
    private PImage highlightedSprite;

    public Entity(float x, float y, String id) {
        super(x, y);
        setSpriteRoot("e_");
        setSpriteID(id);
        loadSprite();
        loadHighlightedSprite();
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
        getApplet().rectMode(getApplet().CENTER);
        getApplet().rect(getRenderPoint().x + game.getCamera().getPos().x, getRenderPoint().y + game.getCamera().getPos().y, getWidth(), getHeight());
        getApplet().popStyle();
    }

    public void renderHighlight() {
        getApplet().pushStyle();
        getApplet().imageMode(getApplet().CENTER);
        getApplet().image(getHighlightedSprite(), getRenderPoint().x + game.getCamera().getPos().x, getRenderPoint().y + game.getCamera().getPos().y, getWidth(), getHeight());
        getApplet().popStyle();
    }

    public void renderSprite() {
        getApplet().pushStyle();
        getApplet().imageMode(getApplet().CENTER);
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

    public PImage getHighlightedSprite() {
        return highlightedSprite;
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

    public void setHighlightedSprite(PImage img) {
        highlightedSprite = img;
    }

    public void loadHighlightedSprite() {
        setHighlightedSprite(spriteManager.getSprite(getSpriteRoot() + getSpriteID() + "_highlighted"));
    }
}
