package com.colin;

import processing.core.PApplet;

public class Clock extends AppletObject {

    int days, hours, minutes, seconds;
    int currentTint;

    public Clock(int num) {
        setHours(num);
    }

    public void update() {
        //Seconds To Minutes
        if(seconds >= 60) {
            minutes++;
            seconds -= 60;
        }

        //Minutes To Hours
        if(minutes >= 60) {
            hours++;
            minutes -= 60;
        }

        //Hours To Days
        if(hours >= 24) {
            days++;
            hours -= 24;
        }
        updateCurrentTint();
    }

    public void addSeconds(float num) {
        seconds += num;
    }

    public void addMinutes(float num) {
        minutes += num;
    }

    public void addHours(float num) {
        hours += num;
    }

    public void addDays(float num) {
        days += num;
    }

    public int getMinutesPassedToday() {
        return minutes + (hours * 60);
    }

    public int getMinutes() {
        return minutes;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int num) {
        hours = num;
    }

    //Returns an int color value that represents the current day time tint overlay for rendering
    public void updateCurrentTint() {
        float MINUTES_IN_HALF_A_DAY = 720;

        //Float that represents from 0 to 1 the height of the sun in the sky
        float sunPosition = PApplet.abs(getMinutesPassedToday() - MINUTES_IN_HALF_A_DAY) / MINUTES_IN_HALF_A_DAY;

        int minR = 65;
        int minG = 20;
        int minB = 65;

        int maxR = 255;
        int maxG = 255;
        int maxB = 230;

        float r = PApplet.map(sunPosition,1, 0, minR, maxR);
        float g = PApplet.map(sunPosition,1, 0, minG, maxG);
        float b = PApplet.map(sunPosition,1, 0, minB, maxB);
        currentTint = getApplet().color(r, g, b);
    }

    public int getCurrentTint() {
        return currentTint;
    }

    public String toString() {
        String string = "[Time] [Days: " + days + "] [Time: " + hours + ":";
        string = (minutes < 10) ? string + "0" + minutes : string + minutes;
        string += ":";
        string = (seconds < 10) ? string + "0" + seconds : string + seconds;
        string += "]";
        return string;
    }
}
