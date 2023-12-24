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
        blocks[mapSizeDivideBy2-1][mapSizeDivideBy2-1].setWalls(new boolean[]{false, true, true, false});
        blocks[mapSizeDivideBy2][mapSizeDivideBy2-1].setWalls(new boolean[]{false, true, false, true});
        blocks[mapSizeDivideBy2-1][mapSizeDivideBy2].setWalls(new boolean[]{true, false, true, false});
        blocks[mapSizeDivideBy2][mapSizeDivideBy2].setWalls(new boolean[]{true, false, false, true});
    }
}
