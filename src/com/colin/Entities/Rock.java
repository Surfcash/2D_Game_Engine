package com.colin.Entities;

import com.colin.Entity;
import com.colin.Tile;

public class Rock extends Entity {

    public Rock(float x, float y) {
        super(x, y, "rock");
        setHeight(64);
        setWidth(64);
        setRenderPoint(getPos().x  + (Tile.TILE_SIZE / 2F), getPos().y + (Tile.TILE_SIZE / 2F));
    }
}
