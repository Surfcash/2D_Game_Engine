package com.colin.UI;

import processing.core.PImage;

import static com.colin.MainApp.spriteManager;

public class Slot extends UIObject {

    public static final int SLOT_SIZE = 112;

    int slotID;
    ItemUI item;
    private PImage highlightedSprite;

    public Slot(float x, float y, int num, ItemUI obj) {
        super(x, y, "inventory_slot");
        setSpriteRoot("ui_");
        setRenderPoint(getPos().x, getPos().y);
        loadHighlightedSprite();
        slotID = num;
        item = obj;
    }

    public void update() {

    }

    public void render() {
        renderSprite();
        item.render();
    }

    public void renderSelected() {
        renderHighlight();
        item.render();
    }

    public void renderHighlight() {
        getApplet().pushStyle();
        getApplet().imageMode(getApplet().CORNER);
        getApplet().image(getHighlightedSprite(), getRenderPoint().x, getRenderPoint().y);
        getApplet().popStyle();
    }

    public int getSlotID() {
        return slotID;
    }

    public ItemUI getItem() {
        return item;
    }

    public PImage getHighlightedSprite() {
        return highlightedSprite;
    }

    public void setHighlightedSprite(PImage img) {
        highlightedSprite = img;
    }

    public void loadHighlightedSprite() {
        setHighlightedSprite(spriteManager.getSprite(getSpriteRoot() + getSpriteID() + "_highlighted"));
    }

}