package com.colin;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

import static com.colin.TileChunk.globalTileChunks;

public class Game {

    public static PApplet applet;

    private Camera cam;
    private ChunkMap chunkMap;
    private PVector mouseLocation;

    public Game(PApplet app) {
        applet = app;
        cam = new Camera(PApplet.floor(applet.width / 2F), PApplet.floor(applet.height / 2F));
        chunkMap = new ChunkMap(100, 100);
    }

    public void frame(long deltaTime) {
        update(deltaTime);
        render();
    }

    private void update(long deltaTime) {
        if(!applet.focused) {
            return;
        }
        updateMouseLocation();
        /*for(CoordinateObject i : coordinateObjects) {
            i.update();
        }*/
        updateCameraScroll(deltaTime);
    }

    private void updateMouseLocation() {
        mouseLocation = new PVector(applet.mouseX - getCamera().getPos().x, applet.mouseY - getCamera().getPos().y);
    }

    private void updateCameraScroll(long deltaTime) {
        float scalar = deltaTime / 60F;
        float moveAmount = 30 * scalar;

        if(applet.mouseX < 200) {
            getCamera().addPos(-moveAmount, 0);
        } else if(applet.mouseX > applet.width - 200) {
            getCamera().addPos(moveAmount, 0);
        }
        if(applet.mouseY < 200) {
            getCamera().addPos(0, -moveAmount);
        } else if(applet.mouseY > applet.height - 200) {
            getCamera().addPos(0, moveAmount);
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
        for(TileChunk i : globalTileChunks) {
            if(!getCamera().offCamera(i, TileChunk.TRUE_CHUNK_WIDTH)) {
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
        strings.add(getFPSString());
        strings.add(getCoordinatesString());
        strings.add(getChunkLocationString());
        strings.add(getMouseLocationString());

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

    private String getFPSString() {
        return "FPS: ( " + PApplet.floor(applet.frameRate) + " )";
    }

    private String getChunkLocationString() {
        TileChunk chunk = getChunkMap().getChunk(getMouseLocation());
        if(chunk != null) {
            return "Chunk: ( " + chunk.getCoord().x + ", " + chunk.getCoord().y + " )";
        } else {
            return " ";
        }
    }

    private String getCoordinatesString() {
        Tile tile = getChunkMap().getTile(getMouseLocation());
        if(tile != null) {
            return "Coordinates: ( " + tile.getCoordinate().x + ", " + tile.getCoordinate().y + " )";
        } else {
            return " ";
        }
    }

    private String getMouseLocationString() {
        return "Mouse: ( " +  getMouseLocation().x + ", " + getMouseLocation().y + " )";
    }

    private void renderTileHighlight() {
        Tile tile = getChunkMap().getTile(getMouseLocation());
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

    public Camera getCamera() {
        return cam;
    }

    public ChunkMap getChunkMap() {
        return chunkMap;
    }

    public PVector getMouseLocation() {
        return mouseLocation;
    }
}
