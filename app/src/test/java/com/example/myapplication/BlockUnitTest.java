package com.example.myapplication;

import com.example.myapplication.map.Block;
import com.example.myapplication.map.Location;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BlockUnitTest {
    @Test
    public void setRightWall1(){
        Block block = new Block(new Location(0, 0));
        block.setRightWall(true);
        assertEquals(block.getWallFlags(),(byte)1);
    }
    @Test
    public void setRightWall2(){
        Block block = new Block(new Location(0, 0));
        block.setRightWall(true);
        block.setRightWall(false);
        assertEquals(block.getWallFlags(),(byte)0);
    }
}
