package com.example.myapplication.map.Compressed;

import com.example.myapplication.map.GameMap;

public class CompressedClassicMap {
    short[] row;
    short[] col;

    public CompressedClassicMap(short[] row, short[] col) {
        if(row.length!=16||col.length!=16) throw new IllegalArgumentException("classic map size should be 16");
        this.row = row;
        this.col = col;
    }
    public CompressedClassicMap(GameMap map){

    }
}
