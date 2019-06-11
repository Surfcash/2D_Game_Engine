package com.colin;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

import static com.colin.MainApp.game;

public class TileChunk extends CoordinateObject{

    /*
     * VARS
     */

    public static ArrayList<TileChunk> globalTileChunks = new ArrayList<>();

    public static final int CHUNK_WIDTH = 16;
    public static int TRUE_CHUNK_WIDTH = CHUNK_WIDTH * Tile.TILE_SIZE;

    private Tile[][] tilemap = new Tile[CHUNK_WIDTH][CHUNK_WIDTH];

    /*
     * CONSTRUCTORS
     */

    public TileChunk(float x, float y) {
        setCoordinate(x, y);
        setPos(getCoordinate().x * TRUE_CHUNK_WIDTH, getCoordinate().y * TRUE_CHUNK_WIDTH);
        initTileMap();
        globalTileChunks.add(this);
    }

    /*
     * INIT
     */

    private void initTileMap() {
        for(int i = 0; i < CHUNK_WIDTH; i++) {
            for(int j = 0; j < CHUNK_WIDTH; j++) {
                float tileLocX = (getCoordinate().x > 0) ? (getCoordinate().x * CHUNK_WIDTH) - (CHUNK_WIDTH - (i)) : (getCoordinate().x * CHUNK_WIDTH) + (CHUNK_WIDTH - (i + 1));
                float tileLocY = (getCoordinate().y > 0) ? (getCoordinate().y * CHUNK_WIDTH) - (CHUNK_WIDTH - (j)) : (getCoordinate().y * CHUNK_WIDTH) + (CHUNK_WIDTH - (j + 1));
                getTilemap()[i][j] = new Tile(tileLocX, tileLocY, Tile.Tiles.GRASS);
            }
        }
    }

    /*
     * UPDATERS
     */

    public void update() {
        for(Tile[] i : tilemap) {
            for(Tile j : i){
                j.update();
            }
        }
    }

    /*
     * RENDERS
     */

    public void render() {
        for (int i = 0; i < getTilemap().length; i++) {
            for (int j = 0; j < getTilemap()[i].length; j++) {
                //Keeps the render back to front to counter negative coordinate values
                Tile tile = (getCoordinate().y > 0) ? getTilemap()[i][j] : getTilemap()[i][PApplet.abs(j - CHUNK_WIDTH + 1)];
                if(!game.getCamera().coordinateOffCamera(tile, Tile.TILE_SIZE * 2)) {
                   tile.render();
                }
            }
        }
    }

    /*
     * GETTERS
     */

    public Tile getTile(PVector vec) {

        int x = PApplet.floor(PApplet.abs(vec.x) - ((PApplet.abs(getCoordinate().x) - 1) * TRUE_CHUNK_WIDTH)) / Tile.TILE_SIZE;
        int y = PApplet.floor(PApplet.abs(vec.y) - ((PApplet.abs(getCoordinate().y) - 1) * TRUE_CHUNK_WIDTH)) / Tile.TILE_SIZE;
        return getTilemap()[x][y];
    }

    public Tile[][] getTilemap() {
        return tilemap;
    }

    /*
     * QUERIES
     */

    public boolean inChunk(PVector vec) {
        boolean outBoundX = (getCoordinate().x > 0) ? (vec.x < getPos().x && vec.x > getPos().x - TRUE_CHUNK_WIDTH) : (vec.x > getPos().x && vec.x < getPos().x + TRUE_CHUNK_WIDTH);
        boolean outBoundY = (getCoordinate().y > 0) ? (vec.y < getPos().y && vec.y > getPos().y - TRUE_CHUNK_WIDTH) : (vec.y > getPos().y && vec.y < getPos().y + TRUE_CHUNK_WIDTH);
        return(outBoundX && outBoundY);
    }

    @Override
    public String toString() {
        return "Chunk: ( " + PApplet.floor(getCoordinate().x) + ", " + PApplet.floor(getCoordinate().y) + " )";
    }
}
