package com.example.myapplication.map;

import android.graphics.Path;

import androidx.annotation.Nullable;

public class Location {
    private int x;
    private int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static char direction(Location start, Location end) {
        // top left bottom right
        // w a s d
        // the respect key in keyboard
        if (start.equals(end)) return 'p';
        int distanceX = end.x - start.x;
        int distanceY = end.y - start.y;
        if (Math.abs(distanceX) >= Math.abs(distanceY)) {
            if (distanceX >= 0) return 'd';
            return 'a';
        }
        if (distanceY >= 0) return 'w';
        return 's';
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof Location)) return false;
        Location other = (Location) obj;
        return this.x == other.x && this.y == other.y;
    }
}
