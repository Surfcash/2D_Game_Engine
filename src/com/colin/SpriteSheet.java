package com.colin;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;


class SpriteSheet{

    private String reference;
    private PImage[] sprites;
    private PVector size;
    private int animationState;
    private int maxAnimationState;

    SpriteSheet(String ref, int spriteCountX, int spriteCountY, PImage spriteSheet) {
        setReference(ref);
        setMaxAnimationState(PApplet.floor(spriteCountX * spriteCountY - 1));
        sprites = new PImage[spriteCountX * spriteCountY];
        int spriteCount = 0;
        size = new PVector(spriteSheet.width / (float)spriteCountX, spriteSheet.height / (float)spriteCountY);
        for(int i = 0; i < spriteCountX; i++) {
            for(int j = 0; j < spriteCountY; j++) {
                sprites[spriteCount] = spriteSheet.get(i * (int)size.x, j * (int)size.y, (int)size.x, (int)size.y);
                spriteCount++;
            }
        }
    }

    public PImage getSprite(int num) {
        return sprites[num];
    }

    public PImage getSprite() {
        return sprites[animationState];
    }

    public String getReference() {
        return reference;
    }

    public int getMaxAnimationState() {
        return maxAnimationState;
    }

    public int getAnimationState() {
        return animationState;
    }

    public void setReference(String str) {
        reference = str;
    }

    public void setAnimationState(int num) {
        animationState = num;
    }

    public void setMaxAnimationState(int num) {
        maxAnimationState = num;
    }

    public void nextAnimationState() {
        if (getAnimationState() < getMaxAnimationState()) {
            setAnimationState(getAnimationState() + 1);
        } else {
            setAnimationState(0);
        }
    }
}
