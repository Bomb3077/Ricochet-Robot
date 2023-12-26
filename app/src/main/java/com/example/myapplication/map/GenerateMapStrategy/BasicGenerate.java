package com.example.myapplication.map.GenerateMapStrategy;

import com.example.myapplication.map.Block;
import com.example.myapplication.map.GameMap;

public class BasicGenerate implements GenerateMapStrategy{
    // this generates default and starting point
    @Override
    public void generateMap(Block[][] blocks) {
        GameMap map = new GameMap(blocks);
        map.setGenerateMapStrategy(new DefaultGenerate());
        map.generateMap();
        int mapSizeDivideBy2 = GameMap.mapSize/2; //it will throw exception at defaultGenerate so simply assume block size equals mapSize
        blocks[mapSizeDivideBy2-1][mapSizeDivideBy2-1].setWalls((byte)6);
        blocks[mapSizeDivideBy2][mapSizeDivideBy2-1].setWalls((byte)3);
        blocks[mapSizeDivideBy2-1][mapSizeDivideBy2].setWalls((byte)12);
        blocks[mapSizeDivideBy2][mapSizeDivideBy2].setWalls((byte)9);
    }
}
