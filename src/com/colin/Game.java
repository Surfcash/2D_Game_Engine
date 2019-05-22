package com.colin;

import processing.core.PApplet;
import processing.core.PVector;

import static com.colin.CoordinateObject.coordinateObjects;
import static com.colin.TileChunk.tileChunks;

public class Game {

    public static PApplet applet;

    public Camera cam;
    public ChunkMap chunkMap;
    public PVector mouseLocation;

    public Game(PApplet app) {
        applet = app;
        cam = new Camera(PApplet.floor(applet.width / 2F), PApplet.floor(applet.height / 2F));
        chunkMap = new ChunkMap(100, 100);
    }

    public void frame() {
        update();
        render();
    }

    private void update() {
        mouseLocation = new PVector(applet.mouseX - cam.getPos().x, applet.mouseY - cam.getPos().y);
        /*for(CoordinateObject i : coordinateObjects) {
            i.update();
        }*/
        if(applet.mouseX < 200) {
            cam.addPos(15, 0);
        } else if(applet.mouseX > applet.width - 200) {
            cam.addPos(-15, 0);
        }
        if(applet.mouseY < 200) {
            cam.addPos(0, 15);
        } else if(applet.mouseY > applet.height - 200) {
            cam.addPos(0, -15);
        }
    }

    private void render() {
        applet.background(128);
        int renderedObjects = 0;
        for(TileChunk i : tileChunks) {
            if(!cam.offCamera(i, TileChunk.TRUE_CHUNK_WIDTH)) {
                i.render();
                renderedObjects++;
            }
        }
        renderTileHighlight();
        renderFPS();
        renderChunkLocation();
        renderCoordinates();
        renderMouseLocation();
        renderRenderedObjectsCount(renderedObjects);
        renderCrossHair();
    }

    private void renderFPS() {
        applet.pushStyle();
        applet.fill(0);
        applet.noStroke();
        applet.text(applet.frameRate, 50, 50);
        applet.popStyle();
    }

    private void renderChunkLocation() {
        PVector chunkLoc = new PVector(0, 0);
        for(int i = 0; i < chunkMap.chunkMap.length; i++) {
            for(int j = 0; j < chunkMap.chunkMap[i].length; j++) {
                TileChunk temp = chunkMap.chunkMap[i][j];
                if(temp.inChunk(new PVector(mouseLocation.x, mouseLocation.y))) {
                    chunkLoc = new PVector(temp.getCoord().x, temp.getCoord().y);
                }
            }

        }
        applet.pushStyle();
        applet.fill(0);
        applet.noStroke();
        applet.text("Chunk: " + chunkLoc.toString(), 50, 100);
        applet.popStyle();
    }

    private void renderCoordinates() {
        Tile tempTile = null;
        for(int i = 0; i < chunkMap.chunkMap.length; i++) {
            for(int j = 0; j < chunkMap.chunkMap[i].length; j++) {
                TileChunk temp = chunkMap.chunkMap[i][j];
                if(temp.inChunk(new PVector(mouseLocation.x, mouseLocation.y))) {
                    tempTile = temp.getTile(mouseLocation);
                }
            }
        }
        if(tempTile == null) return;
        applet.pushStyle();
        applet.fill(0);
        applet.noStroke();
        applet.text("Coordinates: " + tempTile.getPos().x / 64 + ", " + tempTile.getPos().y / 64, 50, 150);
        applet.popStyle();
    }

    private void renderMouseLocation() {
        applet.pushStyle();
        applet.fill(0);
        applet.noStroke();
        applet.text("Mouse: " + mouseLocation.toString(), 50, 200);
        applet.popStyle();
    }

    private void renderRenderedObjectsCount(int num) {
        applet.pushStyle();
        applet.fill(0);
        applet.noStroke();
        applet.text("Rendered Objects: " + num, 50, 250);
        applet.popStyle();
    }

    private void renderTileHighlight() {
        for(int i = 0; i < chunkMap.chunkMap.length; i++) {
            for(int j = 0; j < chunkMap.chunkMap[i].length; j++) {
                TileChunk temp = chunkMap.chunkMap[i][j];
                if(temp.inChunk(new PVector(mouseLocation.x, mouseLocation.y))) {
                    temp.getTile(mouseLocation).renderHighlight();
                    break;
                }
            }
        }
    }

    private void renderCrossHair() {
        PVector center = new PVector(applet.width / 2F, applet.height / 2F);
        int lineLength = 50;
        applet.pushStyle();
        applet.noFill();
        applet.stroke(255,0,0);
        applet.strokeWeight(3);
        applet.line(center.x - lineLength, center.y, center.x + lineLength, center.y);
        applet.line(center.x, center.y - lineLength, center.x, center.y + lineLength);
        applet.popStyle();
    }
}
