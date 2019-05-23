package com.colin;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class TileChunk extends CoordinateObject{

    public static ArrayList<TileChunk> globalTileChunks = new ArrayList<>();

    public static final int CHUNK_WIDTH = 16;
    public static int TRUE_CHUNK_WIDTH = CHUNK_WIDTH * Tile.TILE_SIZE;

    private PVector coord;
    private Tile[][] tilemap = new Tile[CHUNK_WIDTH][CHUNK_WIDTH];

    public TileChunk() {
        setCoord(0, 0);
        initTileMap();
    }

    public TileChunk(float x, float y) {
        setCoord(x, y);
        setPos(getCoord().x * TRUE_CHUNK_WIDTH, getCoord().y * TRUE_CHUNK_WIDTH);
        initTileMap();
        globalTileChunks.add(this);
    }

    public void render() {
        for(Tile[] i : tilemap) {
            for(Tile j : i) {
                j.render();
            }
        }
    }

    public void update() {
    }

    private void initTileMap() {
        for(int i = 0; i < CHUNK_WIDTH; i++) {
            for(int j = 0; j < CHUNK_WIDTH; j++) {
                float tileLocX = (getCoord().x > 0) ? (getCoord().x * CHUNK_WIDTH) - (CHUNK_WIDTH - (i)) : (getCoord().x * CHUNK_WIDTH) + (CHUNK_WIDTH - (i + 1));
                float tileLocY = (getCoord().y > 0) ? (getCoord().y * CHUNK_WIDTH) - (CHUNK_WIDTH - (j)) : (getCoord().y * CHUNK_WIDTH) + (CHUNK_WIDTH - (j + 1));
                tilemap[i][j] = new Tile(tileLocX, tileLocY, Tile.Tiles.GRASS);
            }
        }
    }

    public PVector getCoord() {
        return new PVector(coord.x, coord.y);
    }

    public void setCoord(float x, float y) {
        coord = new PVector(x, y);
    }

    public void setCoord(PVector vec) {
        setCoord(vec.x, vec.y);
    }

    public boolean inChunk(PVector vec) {
        boolean outBoundX = (getCoord().x > 0) ? (vec.x < getPos().x && vec.x > getPos().x - TRUE_CHUNK_WIDTH) : (vec.x > getPos().x && vec.x < getPos().x + TRUE_CHUNK_WIDTH);
        boolean outBoundY = (getCoord().y > 0) ? (vec.y < getPos().y && vec.y > getPos().y - TRUE_CHUNK_WIDTH) : (vec.y > getPos().y && vec.y < getPos().y + TRUE_CHUNK_WIDTH);
        return(outBoundX && outBoundY);
    }

    public Tile getTile(PVector vec) {

        int x = PApplet.floor(PApplet.abs(vec.x) - ((PApplet.abs(getCoord().x) - 1) * TRUE_CHUNK_WIDTH)) / Tile.TILE_SIZE;
        int y = PApplet.floor(PApplet.abs(vec.y) - ((PApplet.abs(getCoord().y) - 1) * TRUE_CHUNK_WIDTH)) / Tile.TILE_SIZE;
        return tilemap[x][y];
    }

    public Tile[][] getTilemap() {
        return tilemap;
    }
}
