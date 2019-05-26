package com.colin;

import processing.core.PApplet;
import processing.core.PImage;

import java.io.File;
import java.util.ArrayList;

final class SpriteManager {
    public static PApplet applet;
    private ArrayList<Sprite> sprites = new ArrayList<>();

    SpriteManager(PApplet app) {
        applet = app;
        loadSpriteFolders();
    }

    private void loadSpritesFromFolder(String filePath, String prefix) {
        File folder = new File(applet.sketchPath(filePath));
        File[] files = folder.listFiles();
        if (files != null) {
            for (File i : files) {
                String fileName = i.getName();
                int pos = fileName.lastIndexOf(".");
                fileName = pos > 0 ? fileName.substring(0, pos) : fileName;
                if (i.isFile()) {
                    Sprite sprite = new Sprite(applet.loadImage(i.getPath()), prefix + fileName);
                    sprites.add(sprite);
                    System.out.println(fileName);
                }
            }
        }
    }

    private void loadSpriteFolders() {
        sprites.clear();
        loadSpritesFromFolder("assets/sprites/tiles", "t_");
        loadSpritesFromFolder("assets/sprites/entities", "e_");
    }

    PImage getSprite(String reference) {
        for(Sprite i : sprites) {
            if(i.reference.equals(reference)) {
                return i.img;
            }
        }
        return null;
    }

    private class Sprite {
        PImage img;
        String reference;

        Sprite(PImage sprite, String reference) {
            this.img = sprite;
            this.reference = reference;
        }
    }
}