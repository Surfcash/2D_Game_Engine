package com.colin.UI;

import com.colin.Renderable;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

import static com.colin.MainApp.game;
import static com.colin.MainApp.getDeltaTime;

public class UI extends Renderable {

    Hotbar hotbar;

    public UI() {
        hotbar = new Hotbar();
    }

    public void update() {

    }

    public void render() {
        renderInformation();
        renderCrossHair();
        getHotbar().render();
    }

    public void renderInformation() {
        int marginX = 50;
        int marginY = 50;
        int spacing = 20;
        ArrayList<String> strings = new ArrayList<>();
        strings.add("DetlaT: ( " + getDeltaTime() + ")");
        strings.add("FPS: ( " + PApplet.floor(getApplet().frameRate) + " )");
        strings.add("Mouse: ( " +  PApplet.floor(game.getMouseLocation().x) + ", " + PApplet.floor(game.getMouseLocation().y) + " )");
        strings.add(game.getClock().toString());
        strings.add(game.getCamera().toString());
        if(game.getHoveredChunk() != null) {
            strings.add(game.getHoveredChunk().toString());
        }
        if(game.getHoveredChunk() != null) {
            strings.add(game.getHoveredTile().toString());
        }

        getApplet().pushStyle();
        getApplet().fill(255);
        getApplet().noStroke();
        getApplet().textSize(18);
        for(int i = 0; i < strings.size(); i++) {
            getApplet().text(strings.get(i), marginX, marginY + (i * spacing));
        }
        getApplet().popStyle();
    }

    private void renderCrossHair() {
        PVector center = new PVector(getApplet().width / 2F, getApplet().height / 2F);
        int lineLength = 25;
        getApplet().pushStyle();
        getApplet().noFill();
        getApplet().stroke(225,200);
        getApplet().strokeWeight(3);
        getApplet().line(center.x - lineLength, center.y, center.x + lineLength, center.y);
        getApplet().line(center.x, center.y - lineLength, center.x, center.y + lineLength);
        getApplet().popStyle();
    }

    public Hotbar getHotbar() {
        return hotbar;
    }
}
