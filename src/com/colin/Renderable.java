package com.colin;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import static com.colin.MainApp.spriteManager;

public abstract class Renderable extends AppletObject {

    private String spriteRoot;
    private String spriteID;
    private PVector renderPoint;
    private int tint;

    public abstract void render();
    public abstract void update();

    public PImage getSprite() {
        return (hasSpriteSheet()) ? spriteManager.getSpriteSheet(getSpriteRoot() + getSpriteID()).getSprite() : spriteManager.getSprite(getSpriteRoot() + getSpriteID());
    }

    public PImage getSprite(int num) {
        return spriteManager.getSprite(getSpriteRoot() + getSpriteID(), num);
    }

    public String getSpriteRoot() {
        return spriteRoot;
    }

    public String getSpriteID() {
        return spriteID;
    }

    public PVector getRenderPoint() {
        return renderPoint;
    }

    public int getTint() {
        return tint;
    }

    public void setSpriteRoot(String str) {
        spriteRoot = str;
    }

    public void setSpriteID(String str) {
        spriteID = str;
    }

    public void setRenderPoint(float x, float y) {
        renderPoint = new PVector(x, y);
    }

    public void setRenderPoint(PVector vec) {
        setRenderPoint(vec.x, vec.y);
    }

    public void setTint(int num) {
        tint = num;
    }

    public boolean hasSpriteSheet() {
        return spriteManager.hasSpriteSheet(getSpriteRoot() + getSpriteID());
    }
}
