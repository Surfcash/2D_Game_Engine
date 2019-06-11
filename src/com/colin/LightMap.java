package com.colin;

import processing.core.PApplet;
import processing.core.PVector;

public class LightMap {

    private int width, height;
    private float[][] map;

    public LightMap(int x, int y) {
        width = x;
        height = y;
        map = new float[getWidth()][getHeight()];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public float[][] getMap() {
        return map;
    }

    public float getLight(int x, int y) {
        int coordX = x + getWidth() / 2;
        int coordY = y + getHeight() / 2;
        if((coordX < getWidth() && coordX > 0) && (coordY < getWidth() && coordY > 0)) {
            return getMap()[coordX][coordY];
        }
        return 0F;
    }

    public void setLight(int x, int y, float num) {
        int coordX = x + getWidth() / 2;
        int coordY = y + getHeight() / 2;
        if((coordX < getWidth() && coordX > 0) && (coordY < getWidth() && coordY > 0)) {
            getMap()[coordX][coordY] = num;
        }
}

    public void setSource(int x, int y) {
        setLight(x, y, 1);
        initNearbyLights(x, y, 1);
    }

    public void delSource(int x, int y) {
        setLight(x, y, 0);
        removeNearbyLights(x, y);
    }

    public void initNearbyLights(int x, int y, int sourceVal) {
        PVector original = new PVector(x, y);
        int maxSource = 16;
        for(int i = -maxSource; i <= maxSource; i++) {
            for(int j = -maxSource ; j <= maxSource; j++) {
                if((x + i) + getWidth() / 2 >= 0 && y + j + getHeight() / 2 >= 0) {
                    PVector target = new PVector(x + i, y + j);
                    float dist = original.dist(target);
                    float newLightValue = PApplet.map(dist, maxSource, 0, 0, sourceVal);
                    if(newLightValue > 0 && getLight(x + i, y + j) < newLightValue) {
                        setLight(x + i, y + j, newLightValue);
                    }
                }
            }
        }
    }

    public void removeNearbyLights(int x, int y) {
        int maxSource = 16;
        for(int i = -maxSource; i <= maxSource; i++) {
            for(int j = -maxSource; j <= maxSource; j++) {
                if((x + i) + getWidth() / 2 >= 0 && y + j + getHeight() / 2 >= 0) {
                    float targetLight = getLight(x + i, y + j);
                    if(targetLight < 1 && targetLight > 0) {
                        setLight(x + i, y + j, 0);
                    }
                }
            }
        }
        int maxDistForSources = maxSource * 2 + 1;
        for(int i = -maxDistForSources; i <= maxDistForSources; i++) {
            for(int j = -maxDistForSources; j <= maxDistForSources; j++) {
                if((x + i) + getWidth() / 2 >= 0 && y + j + getHeight() / 2 >= 0) {
                    if(getLight(x + i, y + j) == 1F) {
                        initNearbyLights(x + i, y + j, 1);
                    }
                }
            }
        }
    }
}
