package com.colin;

import processing.core.PImage;

import static com.colin.MainApp.spriteManager;

public abstract class Renderable extends AppletObject {

    private PImage sprite;
    private String spriteRoot;
    private String spriteID;

    public abstract void render();
    public abstract void update();

    public PImage getSprite() {
        if(sprite == null) {
            loadSprite();
        }
        return sprite;
    }

    public String getSpriteRoot() {
        return spriteRoot;
    }

    public String getSpriteID() {
        return spriteID;
    }

    public void setSprite(PImage img) {
        sprite = img;
    }

    public void setSpriteRoot(String str) {
        spriteRoot = str;
    }

    public void setSpriteID(String str) {
        spriteID = str;
    }

    public void loadSprite() {
        setSprite(spriteManager.getSprite(getSpriteRoot() + getSpriteID()));
    }
}
