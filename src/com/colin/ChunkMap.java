package com.colin;

import processing.core.PApplet;
import processing.core.PVector;


public class ChunkMap {

    private final PApplet applet = Game.applet;

    public TileChunk[][] chunkMap;
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
                    chunkMap[i + halfChunkX][j + halfChunkY] = new TileChunk(x, y);
            }
        }
    }

    private void setChunkMapNoise() {
        float noiseScale = 0.05F;
        int trueMapWidth = getChunkMapWidth() * TileChunk.CHUNK_WIDTH * Tile.TILE_SIZE;
        int trueMapHeight = getChunkMapHeight() * TileChunk.CHUNK_WIDTH * Tile.TILE_SIZE;

        for(int i = 0; i < chunkMap.length; i++) {
            for(int j = 0; j < chunkMap[i].length; j++) {
                for(int k = 0; k < chunkMap[i][j].tilemap.length; k++) {
                    for(int l = 0; l < chunkMap[i][j].tilemap[k].length; l++) {
                        Tile temp = chunkMap[i][j].tilemap[k][l];
                        float noiseVal = applet.noise(((temp.getPos().x + trueMapWidth) / 64F) * noiseScale, ((temp.getPos().y + trueMapHeight) / 64F) * noiseScale);

                        if(noiseVal < 0.3) {
                            //DEEP WATER
                            temp.setColor(applet.color(25, 25, 112));
                        } else if(noiseVal < 0.4) {
                            //WATER
                            temp.setColor(applet.color(	0, 0, 128));
                        } else if(noiseVal < 0.425) {
                            //SAND
                            temp.setColor(applet.color(235, 192, 143));
                        } else if(noiseVal < 0.44) {
                            //SANDY GRASS
                            temp.setColor(applet.color(180, 183, 90));
                        } else if(noiseVal < 0.55) {
                            //GRASS
                            temp.setColor(applet.color(30, 120, 5));
                        } else if(noiseVal < 0.65){
                            //DEEP GRASS
                            temp.setColor(applet.color(0, 100, 0));
                        } else if(noiseVal < 0.7){
                            //SLATE
                            temp.setColor(applet.color(	105, 105, 105));
                        } else if(noiseVal < 1) {
                            //DEEP SLATE
                            temp.setColor(applet.color(	75, 75, 75));
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
        for(int i = 0; i < chunkMap.length; i++) {
            for(int j = 0; j < chunkMap[i].length; j++) {
                if(chunkMap[i][j].inChunk(vec)) {
                    return chunkMap[i][j];
                }
            }
        }
        return null;
    }
}
