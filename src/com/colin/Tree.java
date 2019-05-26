package com.colin;

public class Tree extends Entity {


    public Tree(float x, float y) {
        super(x, y);
        setSpriteID("tree");
        setHeight(112);
        setWidth(64);
        setRenderPoint(getPos().x, getPos().y - Tile.TILE_SIZE);
        loadSprite();
    }

    public void update() {

    }

    public void render() {
        renderSprite();
    }
}
