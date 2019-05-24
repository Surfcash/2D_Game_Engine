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
    private TileChunk hoveredChunk;
    private Tile hoveredTile;

    public Game(PApplet app) {
        applet = app;
        cam = new Camera(PApplet.floor(applet.width / 2F), PApplet.floor(applet.height / 2F));
        chunkMap = new ChunkMap(80, 80);
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
        /*for(CoordinateObject i : coordinateObjects) {
            i.update();
        }*/
        updateCameraScroll();
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
        float scalar = MainApp.getDeltaTime() / 60F;
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
        applet.background(128);
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
        applet.stroke(160,0,0);
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
            return "Chunk: ( " + getHoveredChunk().getCoord().x + ", " + getHoveredChunk().getCoord().y + " )";
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

    private String getTileData() {
        if(getHoveredTile() != null) {
            return "Tile: ( " + getHoveredTile().getType().toString() + " )";
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
