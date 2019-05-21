package com.colin;

import processing.core.PApplet;

import static processing.core.PApplet.floor;

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
                            temp.setColor(0);
                        } else if(noiseVal < 0.4) {
                            temp.setColor(1);
                        } else if(noiseVal < 0.45) {
                            temp.setColor(2);
                        } else if(noiseVal < 0.6) {
                            temp.setColor(3);
                        } else {
                            temp.setColor(4);
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
}
