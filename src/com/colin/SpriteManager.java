package com.colin;

import processing.core.PApplet;
import processing.core.PImage;

import java.io.File;
import java.util.ArrayList;

public final class SpriteManager {
    private static int TICKS_PER_ANIMATION = 20;

    public static PApplet applet;
    private ArrayList<Sprite> sprites = new ArrayList<>();
    private ArrayList<SpriteSheet> spriteSheets = new ArrayList<>();
    private int tick;

    SpriteManager(PApplet app) {
        applet = app;
        loadSpriteFolders();
    }

    private void loadSpritesFromFolder(String filePath, String prefix) {
        File folder = new File(applet.sketchPath(filePath));
        File[] files = folder.listFiles();
        PApplet.println("Loading '" + filePath + "/...'");
        if (files != null) {
            for (File i : files) {
                String fileName = i.getName();
                int pos = fileName.lastIndexOf(".");
                fileName = pos > 0 ? fileName.substring(0, pos) : fileName;
                if (i.isFile()) {
                    Sprite sprite = new Sprite(applet.loadImage(i.getPath()), prefix + fileName);
                    sprites.add(sprite);
                    if(fileName.contains("spritesheet")) {
                        String sections[] = PApplet.split(fileName, ".");
                        PApplet.println("Loading Spritesheet " + sections[0] + " " + sections[1] + " by " + sections[2]);
                        spriteSheets.add(new SpriteSheet(prefix + sections[0], Integer.parseInt(sections[1]), Integer.parseInt(sections[2]), sprite.img));
                    }
                    System.out.println(fileName);
                }
            }
        }
    }

    public void tick() {
        if(tick < TICKS_PER_ANIMATION) {
            tick++;
        } else {
            updateSpriteSheetAnimations();
            tick = 0;
        }
    }

    private void loadSpriteFolders() {
        sprites.clear();
        loadSpritesFromFolder("assets/sprites/tiles", "t_");
        loadSpritesFromFolder("assets/sprites/entities", "e_");
        loadSpritesFromFolder("assets/sprites/UI", "ui_");
        loadSpritesFromFolder("assets/sprites/items", "i_");
        loadSpritesFromFolder("assets/sprites/overlay", "o_");
    }

    public void updateSpriteSheetAnimations() {
        for(SpriteSheet i : spriteSheets) {
            i.nextAnimationState();
        }
    }

    public PImage getSprite(String reference) {
        for(Sprite i : sprites) {
            if(i.reference.equals(reference)) {
                return i.img;
            }
        }
        return null;
    }

    public PImage getSprite(String reference, int num) {
        for(SpriteSheet i : spriteSheets) {
            if(i.getReference().equals(reference + "_spritesheet")) {
                return i.getSprite(num);
            }
        }
        return null;
    }

    public SpriteSheet getSpriteSheet(String reference) {
        for(SpriteSheet i : spriteSheets) {
            if(i.getReference().equals(reference + "_spritesheet")) {
                return i;
            }
        }
        return null;
    }

    public boolean hasSpriteSheet(String reference) {
        for(SpriteSheet i : spriteSheets) {
            if(i.getReference().equals(reference + "_spritesheet")) {
                return true;
            }
        }
        return false;
    }

    private class Sprite {
        PImage img;
        SpriteSheet sheet;
        String reference;

        Sprite(PImage sprite, String reference) {
            this.img = sprite;
            this.reference = reference;
        }

        Sprite(PImage sprite, PImage sheet, String reference) {
            this.img = sprite;
            this.reference = reference;
        }
    }
}