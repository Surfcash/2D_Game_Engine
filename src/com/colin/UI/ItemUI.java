package com.colin.UI;

public class ItemUI extends UIObject{

    public static int ITEM_SIZE = 104;
    String id;

    public ItemUI(float x, float y, String str) {
        super(x, y, str);
        setRenderPoint(getPos().x, getPos().y);
        setWidth(ITEM_SIZE);
        setHeight(ITEM_SIZE);
        setSpriteRoot("i_");
        setID(str);
    }

    public void update() {

    }

    public void render() {
        renderSprite();
    }

    public String getID() {
        return id;
    }

    public void setID(String str) {
        id = str;
    }
}
