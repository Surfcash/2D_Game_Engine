package com.colin;

import processing.core.PVector;


public class ChunkMap extends AppletObject{

    private TileChunk[][] chunkMap;
    private int chunkMapHeight, chunkMapWidth;

    public ChunkMap(int x, int y) {
        setChunkMap(x, y);
        chunkMap = new TileChunk[getChunkMapWidth()][getChunkMapHeight()];
        initChunkMap();
        setChunkMapNoise();
    }

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
                            k.setType(Tile.Tiles.DEEP_WATER);
                        } else if(noiseVal < 0.4) {
                            //WATER
                            k.setType(Tile.Tiles.WATER);
                        } else if(noiseVal < 0.425) {
                            //SAND
                            k.setType(Tile.Tiles.SAND);
                        } else if(noiseVal < 0.43) {
                            //SANDY GRASS
                            k.setType(Tile.Tiles.SANDY_GRASS);
                        } else if(noiseVal < 0.55) {
                            //GRASS
                            k.setType(Tile.Tiles.GRASS);
                        } else if(noiseVal < 0.65){
                            //DEEP GRASS
                            k.setType(Tile.Tiles.DEEP_GRASS);
                        } else if(noiseVal < 0.7){
                            //SLATE
                            k.setType(Tile.Tiles.SLATE);
                        } else if(noiseVal < 1) {
                            //DEEP SLATE
                            k.setType(Tile.Tiles.DEEP_SLATE);
                        }
                    }
                }
            }
        }
    }

    public int getChunkMapHeight() {
        return chunkMapHeight;
    }

    public int getChunkMapWidth() {
        return chunkMapWidth;
    }

    public TileChunk[][] getChunkMap() {
        return chunkMap;
    }

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

    public Tile getTile(PVector vec) {
        TileChunk chunk = getChunk(vec);
        if(chunk == null) {
            return null;
        } else {
            return chunk.getTile(vec);
        }
    }

    public TileChunk getChunk(PVector vec) {
        for(TileChunk[] i : getChunkMap()) {
            for(TileChunk j : i) {
                if(j.inChunk(vec)) {
                    return j;
                }
            }
        }
        return null;
    }
}
