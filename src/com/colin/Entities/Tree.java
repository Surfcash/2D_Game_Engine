package com.colin.Entities;

import com.colin.Entity;
import com.colin.Tile;

public class Tree extends Entity {

    public Tree(float x, float y) {
        super(x, y, "tree");
        setHeight(128);
        setWidth(76);
        setRenderPoint(getPos().x + (Tile.TILE_SIZE / 2F), getPos().y);
    }
}
