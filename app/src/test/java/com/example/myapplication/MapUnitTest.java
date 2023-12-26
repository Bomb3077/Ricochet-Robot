package com.example.myapplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.example.myapplication.map.Block;
import com.example.myapplication.map.GameMap;
import com.example.myapplication.map.GenerateMapStrategy.DefaultGenerate;
import com.example.myapplication.map.Location;

public class MapUnitTest {
    @Before
    public void setUp(){
        GameMap.mapSize = 2;
    }

    @Test
    public void defaultMap(){
//        GameMap map1 = new GameMap();
//        map1.setGenerateMapStrategy(new DefaultGenerate());
//        map1.generateMap();
//        Block[][] blocks = new Block[2][2];
//        // top bottom left right
//        blocks[0][0] = new Block(new Location(0, 0));
//        blocks[0][0].setWalls(new boolean[]{false, true, true, false});
//        blocks[0][1] = new Block(new Location(0, 1));
//        blocks[0][1].setWalls(new boolean[]{true, false, true, false});
//        blocks[1][0] = new Block(new Location(1, 0));
//        blocks[1][0].setWalls(new boolean[]{false, true, false, true});
//        blocks[1][1] = new Block(new Location(1, 1));
//        blocks[1][1].setWalls(new boolean[]{true, false, false, true});
//
//        GameMap map2 = new GameMap(blocks);
//        assertTrue(map1.equals(map2));
    }
}
