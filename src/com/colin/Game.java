package com.colin;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

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
        if(!applet.focused) {
            return;
        }
        updateMouseLocation();
        /*for(CoordinateObject i : coordinateObjects) {
            i.update();
        }*/
        updateCameraScroll();
    }

    private void updateMouseLocation() {
        mouseLocation = new PVector(applet.mouseX - cam.getPos().x, applet.mouseY - cam.getPos().y);
    }

    private void updateCameraScroll() {
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
        renderDefaultBackground();
        renderTileChunks();
        renderTileHighlight();
        renderCrossHair();
        renderInformation();
    }

    private void renderDefaultBackground() {
        applet.background(128);
    }

    /*
    Returns - Rendered chunk count
     */
    private int renderTileChunks() {
        int renderedObjects = 0;
        for(TileChunk i : tileChunks) {
            if(!cam.offCamera(i, TileChunk.TRUE_CHUNK_WIDTH)) {
                i.render();
                renderedObjects++;
            }
        }
        return renderedObjects;
    }

    public void renderInformation() {
        int marginX = 50;
        int marginY = 50;
        int spacing = 20;
        ArrayList<String> strings = new ArrayList<>();
        strings.add(getFPS());
        strings.add(getCoordinates());
        strings.add(getChunkLocation());
        strings.add(getMouseLocation());

        ArrayList<String> finalStrings = new ArrayList<>();
        for(String i : strings) {
            if(!i.equals(" ")) {
                finalStrings.add(i);
            }
        }

        applet.pushStyle();
        applet.fill(255);
        applet.noStroke();
        applet.textSize(18);
        for(int i = 0; i < finalStrings.size(); i++) {
            applet.text(strings.get(i), marginX, marginY + (i * spacing));
        }
        applet.popStyle();
    }

    private String getFPS() {
        return "FPS: ( " + PApplet.floor(applet.frameRate) + " )";
    }

    private String getChunkLocation() {
        TileChunk chunk = chunkMap.getChunk(mouseLocation);
        if(chunk != null) {
            return "Chunk: ( " + chunk.getCoord().x + ", " + chunk.getCoord().y + " )";
        } else {
            return " ";
        }
    }

    private String getCoordinates() {
        Tile tile = chunkMap.getTile(mouseLocation);
        if(tile != null) {
            return "Coordinates: ( " + tile.getCoordinate().x + ", " + tile.getCoordinate().y + " )";
        } else {
            return " ";
        }
    }

    private String getMouseLocation() {
        return "Mouse: ( " +  mouseLocation.x + ", " + mouseLocation.y + " )";
    }

    private void renderTileHighlight() {
        Tile tile = chunkMap.getTile(mouseLocation);
        if(tile != null) {
            tile.renderHighlight();
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
