package com.colin;

public class Item extends TileEntity {

    public static final String[] ITEM_IDS = {"water", "deep_water", "sand", "sandy_grass", "grass", "deep_grass", "slate", "deep_slate", "tree", "rock", "sunflower", "lilac", "long_grass", "lamp_post"};

    public static int ITEM_SIZE = 104;
    String id;

    public Item(float x, float y, String str) {
        super(x, y, str, ITEM_SIZE, ITEM_SIZE, 0, 0);
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
