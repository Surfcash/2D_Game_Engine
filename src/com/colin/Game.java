package com.colin;

import com.colin.UI.UI;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

import static com.colin.MainApp.getDeltaTime;
import static com.colin.TileChunk.globalTileChunks;

public class Game {

    public static final int MAP_WIDTH = 80;
    public static final int MAP_HEIGHT = 80;

    public static PApplet applet;

    private Camera cam;
    private ChunkMap chunkMap;
    private UI ui;
    private PVector mouseLocation;
    private PVector screenCenter;
    private TileChunk hoveredChunk;
    private Tile hoveredTile;
    private Clock clock;

    private ArrayList<TileChunk> chunksOnScreen;

    public Game(PApplet app) {
        applet = app;
        cam = new Camera(0, 0);
        chunkMap = new ChunkMap(MAP_WIDTH, MAP_HEIGHT);
        mouseLocation = new PVector();
        screenCenter = new PVector(applet.width / 2F, applet.height / 2F);
        ui = new UI();
        clock = new Clock(22);
    }

    public void frame() {
        update();
        render();
    }

    /*
     * UPDATES
     */

    private void update() {
        if(applet.focused) {
            updateTime();
            updateMouseLocation();
            updateChunksOnScreen();
            updateHoveredChunk();
            updateHoveredTile();
            updateMousePressed();
            getCamera().update();
            updateTileChunks();
        }

    }

    private void updateTime() {
        clock.addSeconds(getDeltaTime() * 0.5F);
        clock.update();
    }

    private void updateMouseLocation() {
        mouseLocation = new PVector(applet.mouseX - getCamera().getRealPos().x, applet.mouseY - getCamera().getRealPos().y);
    }

    private void updateHoveredChunk() {
        hoveredChunk = getChunkMap().getChunk(getMouseLocation());
    }

    private void updateHoveredTile() {
        hoveredTile = (getHoveredChunk() != null) ? getHoveredChunk().getTile(getMouseLocation()) : getChunkMap().getTile(getMouseLocation());
    }

    private void updateCameraScroll() {
        int accelerationLimiter = 8;
        int velocityLimit = PApplet.floor(15 * getDeltaTime());

        PVector mouse = new PVector(applet.mouseX, applet.mouseY);
        PVector vel = mouse.sub(screenCenter).div(accelerationLimiter).limit(velocityLimit);
        getCamera().setVel(-vel.x, -vel.y);
    }

    private void updateMousePressed() {
        if (applet.mousePressed) {
            if(getHoveredTile() != null) {
                if(applet.mouseButton == applet.LEFT) {
                    String selectedSlotID = getUI().getHotbar().getSelectedSlot().getItem().getID();
                    Tile.Tiles type = Tile.getTileType(selectedSlotID);
                    TileEntity.TileEntities entity = TileEntity.getEntityType(selectedSlotID);
                    if(type != null) {
                        getHoveredTile().setType(type);
                    } else if(entity != null && getHoveredTile().getDepth() >= 0) {
                        getHoveredTile().setEntity(new TileEntity(getHoveredTile().getPos().x, getHoveredTile().getPos().y, entity));
                        if(entity == TileEntity.TileEntities.LAMP_POST) {
                            getChunkMap().getLightMap().setSource(getHoveredTile().getCoordX(), getHoveredTile().getCoordY());
                        }
                    }
                } else if(applet.mouseButton == applet.RIGHT) {
                    if(getHoveredTile().hasEntity()) {
                        if(getHoveredTile().getEntity().getType() == TileEntity.TileEntities.LAMP_POST) {
                            getChunkMap().getLightMap().delSource(getHoveredTile().getCoordX(), getHoveredTile().getCoordY());
                        }
                        getHoveredTile().delEntity();
                    }
                } else if(applet.mouseButton == applet.CENTER) {
                    updateCameraScroll();
                }
            }
        }
    }

    private void updateTileChunks() {
        for(TileChunk i : getChunksOnScreen()) {
            i.update();
        }
    }

    /*
     * RENDERS
     */

    private void render() {
        renderTileChunks();
        renderTileHighlight();
        getUI().render();
    }

    private void renderTileChunks() {
        if(getChunksOnScreen() != null) {
            for (TileChunk i : getChunksOnScreen()) {
                i.render();
            }
        }
    }

    private void renderTileHighlight() {
        if (getHoveredTile() != null) {
            getHoveredTile().renderHighlight();
        }
    }

    /*
     * GETTERS
     */

    public Camera getCamera() {
        return cam;
    }

    public ChunkMap getChunkMap() {
        return chunkMap;
    }

    public PVector getMouseLocation() {
        return mouseLocation;
    }

    public PVector getScreenCenter() {
        return screenCenter;
    }

    public TileChunk getHoveredChunk() {
        return hoveredChunk;
    }

    public ArrayList<TileChunk> getChunksOnScreen() {
        return chunksOnScreen;
    }

    private void updateChunksOnScreen() {
        ArrayList<TileChunk> tileChunks = new ArrayList<>();
        for(TileChunk i : globalTileChunks) {
            if(!getCamera().chunkOffCamera(i, TileChunk.TRUE_CHUNK_WIDTH + Tile.TILE_SIZE)) {
                tileChunks.add(i);
            }
        }
        chunksOnScreen = tileChunks;
    }

    public Tile getHoveredTile() {
        return hoveredTile;
    }

    public UI getUI() {
        return ui;
    }

    public Clock getClock() {
        return clock;
    }
}
