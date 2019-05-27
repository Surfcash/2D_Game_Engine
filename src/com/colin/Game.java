package com.colin;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

import static com.colin.TileChunk.globalTileChunks;

public class Game {

    public static final int MAP_WIDTH = 80;
    public static final int MAP_HEIGHT = 80;

    public static PApplet applet;

    private Camera cam;
    private ChunkMap chunkMap;
    private PVector mouseLocation;
    private PVector screenCenter;
    private TileChunk hoveredChunk;
    private Tile hoveredTile;

    public Game(PApplet app) {
        applet = app;
        cam = new Camera(PApplet.floor(applet.width / 2F), PApplet.floor(applet.height / 2F));
        chunkMap = new ChunkMap(MAP_WIDTH, MAP_HEIGHT);
        mouseLocation = new PVector();
        screenCenter = new PVector(applet.width / 2F, applet.height / 2F);
    }

    public void frame() {
        update();
        render();
    }

    /*
     * UPDATES
     */

    private void update() {
        if(!applet.focused) {
            return;
        }
        updateMouseLocation();
        updateHoveredChunk();
        updateHoveredTile();
        updateMousePressed();
        //updateCameraScroll();
    }

    private void updateMouseLocation() {
        mouseLocation = new PVector(applet.mouseX - getCamera().getPos().x, applet.mouseY - getCamera().getPos().y);
    }

    private void updateHoveredChunk() {
        hoveredChunk = getChunkMap().getChunk(getMouseLocation());
    }

    private void updateHoveredTile() {
        hoveredTile = getChunkMap().getTile(getMouseLocation());
    }

    private void updateCameraScroll() {
        PVector mouse = new PVector(applet.mouseX, applet.mouseY);
        PVector vel = mouse.sub(screenCenter).div(15).limit(40);

        getCamera().subPos(vel);
        getCamera().update();
    }

    private void updateMousePressed() {
        Tile.Tiles[] tileTypes = Tile.Tiles.values();
        if (applet.mousePressed) {
            if(getHoveredTile() != null) {
                if(applet.mouseButton == applet.LEFT) {
                    getHoveredTile().setType(tileTypes[MainApp.getType()]);
                    if(getHoveredTile().getDepth() < 0) {
                        getHoveredTile().delEntity();
                    }
                } else if(applet.mouseButton == applet.RIGHT) {
                    if(getHoveredTile().hasEntity()) {
                        getHoveredTile().delEntity();
                    }
                } else if(applet.mouseButton == applet.CENTER) {
                    updateCameraScroll();
                }
            }
        }
    }

    /*
     * RENDERS
     */

    private void render() {
        renderDefaultBackground();
        renderTileChunks();
        renderTileHighlight();
        renderCrossHair();
        renderInformation();
    }

    private void renderDefaultBackground() {
        applet.background(223, 202, 159);
    }

    private void renderTileChunks() {
        for(TileChunk i : globalTileChunks) {
            if(!getCamera().offCamera(i, TileChunk.TRUE_CHUNK_WIDTH)) {
                i.render();
            }
        }
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
        strings.add(getCameraLocationString());
        strings.add(getTileData());

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

    private void renderTileHighlight() {
        if(getHoveredTile() != null) {
            getHoveredTile().renderHighlight();
        }
    }

    private void renderCrossHair() {
        PVector center = new PVector(applet.width / 2F, applet.height / 2F);
        int lineLength = 25;
        applet.pushStyle();
        applet.noFill();
        applet.stroke(225,200);
        applet.strokeWeight(3);
        applet.line(center.x - lineLength, center.y, center.x + lineLength, center.y);
        applet.line(center.x, center.y - lineLength, center.x, center.y + lineLength);
        applet.popStyle();
    }

    /*
     * GETTERS
     */

    private String getFPSString() {
        return "FPS: ( " + PApplet.floor(applet.frameRate) + " )";
    }

    private String getChunkLocationString() {
        if(getHoveredChunk() != null) {
            return "Chunk: ( " + getHoveredChunk().getCoordinate().x + ", " + getHoveredChunk().getCoordinate().y + " )";
        } else {
            return " ";
        }
    }

    private String getCoordinatesString() {
        if(getHoveredTile() != null) {
            return "Coordinates: ( " + getHoveredTile().getCoordinate().x + ", " + getHoveredTile().getCoordinate().y + " )";
        } else {
            return " ";
        }
    }

    private String getMouseLocationString() {
        return "Mouse: ( " +  getMouseLocation().x + ", " + getMouseLocation().y + " )";
    }

    private String getCameraLocationString() {
        return "Camera: ( " +  getCamera().getPos().x + ", " + getCamera().getPos().y + " )" + " ( " + getCamera().getCamBorder().x + ", " + getCamera().getCamBorder().y + " )";
    }

    private String getTileData() {
        String temp;
        if(getHoveredTile() != null) {
            temp = "Tile: ( " + getHoveredTile().getType().toString() + " ) ( " + getHoveredTile().getDepth() + " )";
            if(getHoveredTile().hasEntity()) {
                temp = temp + " ( " + getHoveredTile().getEntity().getSpriteID() + " )";
            }
            return temp;
        } else {
            return " ";
        }
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

    public TileChunk getHoveredChunk() {
        return hoveredChunk;
    }

    public Tile getHoveredTile() {
        return hoveredTile;
    }
}
