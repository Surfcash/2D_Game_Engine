package com.colin;

import processing.core.PImage;

import static com.colin.MainApp.game;
import static com.colin.MainApp.spriteManager;

abstract class Entity extends CoordinateObject {

    private int width, height;
    private PImage highlightedSprite;

    public Entity(float x, float y, String id, int width, int height) {
        super(x, y);
        setSpriteRoot("e_");
        setSpriteID(id);
        setWidth(width);
        setHeight(height);
        setRenderPoint(getPos());
        loadHighlightedSprite();
    }

    public void renderWireframe() {
        getApplet().pushStyle();
        getApplet().fill(255,50);
        getApplet().stroke(255, 225);
        getApplet().strokeWeight(2);
        getApplet().rectMode(getApplet().CENTER);
        getApplet().rect(getRenderPoint().x + game.getCamera().getRealPos().x, getRenderPoint().y + game.getCamera().getRealPos().y, getWidth(), getHeight());
        getApplet().popStyle();
    }

    public void renderHighlight() {
        getApplet().pushStyle();
        getApplet().imageMode(getApplet().CENTER);
        getApplet().image(getHighlightedSprite(), getRenderPoint().x + game.getCamera().getRealPos().x, getRenderPoint().y + game.getCamera().getRealPos().y);
        getApplet().popStyle();
    }

    public void renderSprite() {
        getApplet().pushStyle();
        getApplet().imageMode(getApplet().CENTER);
        getApplet().tint(getTint());
        getApplet().image(getSprite(), getRenderPoint().x + game.getCamera().getRealPos().x, getRenderPoint().y + game.getCamera().getRealPos().y);
        getApplet().popStyle();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
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

    public void setHighlightedSprite(PImage img) {
        highlightedSprite = img;
    }

    public void loadHighlightedSprite() {
        setHighlightedSprite(spriteManager.getSprite(getSpriteRoot() + getSpriteID() + "_highlighted"));
    }
}