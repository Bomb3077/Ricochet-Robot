package com.example.myapplication.map.Compressed;

import com.example.myapplication.map.GameMap;

public class CompressedClassicMap {
    byte[] row;
    byte[] col;

    public CompressedClassicMap(byte[] row, byte[] col, byte robotLocations, byte robotPermutation, byte targetID, byte targetLocation) {
        this.row = row;
        this.col = col;
    }
    public CompressedClassicMap(GameMap map){

    }
}
