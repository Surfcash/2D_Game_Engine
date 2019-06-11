package com.colin;

import processing.core.PVector;

import java.util.ArrayList;

import static com.colin.MainApp.game;


public class ChunkMap extends AppletObject{

    /*
     * VARS
     */

    private TileChunk[][] chunkMap;
    private int chunkMapHeight, chunkMapWidth;
    private LightMap lightMap;

    /*
     * CONSTRUCTOR
     */

    public ChunkMap(int x, int y) {
        setChunkMap(x, y);
        chunkMap = new TileChunk[getChunkMapWidth()][getChunkMapHeight()];
        lightMap = new LightMap(x * TileChunk.CHUNK_WIDTH, y * TileChunk.CHUNK_WIDTH);
        initChunkMap();
        setChunkMapNoise();
    }

    /*
     * INIT
     */

    private void initChunkMap() {
        int halfChunkX = getChunkMapWidth() / 2;
        int halfChunkY = getChunkMapHeight() / 2;
        for(int i = -halfChunkX; i < halfChunkX; i++) {
            for(int j = -halfChunkY; j < halfChunkY; j++) {
                int x = (i < 0) ? i : i + 1;
                int y = (j < 0) ? j : j + 1;
                    getChunkMap()[i + halfChunkX][j + halfChunkY] = new TileChunk(x, y);
            }
        }
    }

    /*
     * GETTERS
     */

    public int getChunkMapHeight() {
        return chunkMapHeight;
    }

    public int getChunkMapWidth() {
        return chunkMapWidth;
    }

    public TileChunk[][] getChunkMap() {
        return chunkMap;
    }

    public LightMap getLightMap() {
        return lightMap;
    }

    public ArrayList<Tile> getSurroundingTiles(Tile tile) {
        ArrayList<Tile> surroundingTiles = new ArrayList<>();
        int tileSizeHalf = Tile.TILE_SIZE / 2;
        int tileSize = Tile.TILE_SIZE;
        PVector tileCenter = new PVector(tile.getPos().x + tileSizeHalf, tile.getPos().y + tileSizeHalf);
        surroundingTiles.add(getTile(new PVector(tileCenter.x + tileSize, tileCenter.y)));
        surroundingTiles.add(getTile(new PVector(tileCenter.x - tileSize, tileCenter.y)));
        surroundingTiles.add(getTile(new PVector(tileCenter.x, tileCenter.y + tileSize)));
        surroundingTiles.add(getTile(new PVector(tileCenter.x, tileCenter.y - tileSize)));
        return surroundingTiles;
    }

    public Tile getTile(PVector vec) {
        TileChunk chunk = getChunk(vec);
        if(chunk == null) {
            return null;
        } else {
            return chunk.getTile(vec);
        }
    }

    public TileChunk getChunk(PVector vec) {
        for(TileChunk i : game.getChunksOnScreen()) {
            if(i.inChunk(vec)) {
                return i;
            }
        }
        return null;
    }

    /*
     * SETTERS
     */

    public void setChunkMapHeight(int num) {
        chunkMapHeight = num;
    }

    public void setChunkMapWidth(int num) {
        chunkMapWidth = num;
    }

    public void setChunkMap(int numx, int numy) {
        setChunkMapHeight(numy);
        setChunkMapWidth(numx);
    }

    /*
     * Needs to be improved. Too hard coded. Needs more control and based on map depth rather tile assignment.
     */

    private void setChunkMapNoise() {
        float noiseScale = 0.05F;
        int trueMapWidth = getChunkMapWidth() * TileChunk.CHUNK_WIDTH * Tile.TILE_SIZE;
        int trueMapHeight = getChunkMapHeight() * TileChunk.CHUNK_WIDTH * Tile.TILE_SIZE;

        for(TileChunk[] i : getChunkMap()) {
            for(TileChunk j : i) {
                for(Tile[] l : j.getTilemap()) {
                    for(Tile k : l) {
                        float noiseVal = getApplet().noise(((k.getPos().x + trueMapWidth) / 64F) * noiseScale, ((k.getPos().y + trueMapHeight) / 64F) * noiseScale);

                        if(noiseVal < 0.3) {
                            //DEEP WATER
                            k.setDepth(-2);
                            k.setType(Tile.Tiles.DEEP_WATER);
                        } else if(noiseVal < 0.4) {
                            //WATER
                            k.setDepth(-1);
                            k.setType(Tile.Tiles.WATER);
                        } else if(noiseVal < 0.425) {
                            //SAND
                            k.setDepth(0);
                            k.setType(Tile.Tiles.SAND);
                        } else if(noiseVal < 0.43) {
                            //SANDY GRASS
                            k.setDepth(0);
                            k.setType(Tile.Tiles.SANDY_GRASS);
                        } else if(noiseVal < 0.55) {
                            //GRASS
                            k.setDepth(0);
                            k.setType(Tile.Tiles.GRASS);
                            if(getApplet().random(1) > 0.9) {
                                k.setEntity(new TileEntity(k.getPos().x, k.getPos().y, TileEntity.TileEntities.TREE));
                            } else if(getApplet().random(1) > 0.95) {
                                k.setEntity(new TileEntity(k.getPos().x, k.getPos().y, TileEntity.TileEntities.LONG_GRASS));
                            } else if(getApplet().random(1) > 0.97) {
                                k.setEntity(new TileEntity(k.getPos().x, k.getPos().y, TileEntity.TileEntities.SUNFLOWER));
                            } else if(getApplet().random(1) > 0.97) {
                                k.setEntity(new TileEntity(k.getPos().x, k.getPos().y, TileEntity.TileEntities.LILAC));
                            } else if(getApplet().random(1) > 0.99) {
                                k.setEntity(new TileEntity(k.getPos().x, k.getPos().y, TileEntity.TileEntities.ROCK));
                            }
                        } else if(noiseVal < 0.65){
                            //DEEP GRASS
                            k.setDepth(0);
                            k.setType(Tile.Tiles.DEEP_GRASS);
                            if(getApplet().random(1) > 0.75) {
                                k.setEntity(new TileEntity(k.getPos().x, k.getPos().y, TileEntity.TileEntities.TREE));
                            } else if(getApplet().random(1) > 0.95) {
                                k.setEntity(new TileEntity(k.getPos().x, k.getPos().y, TileEntity.TileEntities.LONG_GRASS));
                            } else if(getApplet().random(1) > 0.99) {
                                k.setEntity(new TileEntity(k.getPos().x, k.getPos().y, TileEntity.TileEntities.SUNFLOWER));
                            } else if(getApplet().random(1) > 0.99) {
                                k.setEntity(new TileEntity(k.getPos().x, k.getPos().y, TileEntity.TileEntities.LILAC));
                            } else if(getApplet().random(1) > 0.99) {
                                k.setEntity(new TileEntity(k.getPos().x, k.getPos().y, TileEntity.TileEntities.ROCK));
                            }

                        } else if(noiseVal < 0.7){
                            //SLATE
                            k.setDepth(1);
                            k.setType(Tile.Tiles.SLATE);
                            if(getApplet().random(1) > 0.9) {
                                k.setEntity(new TileEntity(k.getPos().x, k.getPos().y, TileEntity.TileEntities.ROCK));
                            }
                        } else if(noiseVal < 1) {
                            //DEEP SLATE
                            k.setDepth(1);
                            k.setType(Tile.Tiles.DEEP_SLATE);
                            if(getApplet().random(1) > 0.75) {
                                k.setEntity(new TileEntity(k.getPos().x, k.getPos().y, TileEntity.TileEntities.ROCK));
                            }
                        }
                    }
                }
            }
        }
    }
}
