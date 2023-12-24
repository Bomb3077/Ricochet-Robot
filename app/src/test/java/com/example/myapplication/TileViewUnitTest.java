package com.example.myapplication;

import static org.junit.Assert.assertEquals;

import com.example.myapplication.graphics.TileView;

import org.junit.Test;

public class TileViewUnitTest {
    @Test
    public void booleansToIntTest1(){
        boolean[] walls = {false, true, false, true};
        assertEquals(TileView.booleanArrayToInt(walls), 5);
    }
    @Test
    public void booleansToIntTes2(){
        boolean[] walls = {true, true, false, true};
        assertEquals(TileView.booleanArrayToInt(walls), 13);
    }
    @Test
    public void booleansToIntTes3(){
        boolean[] walls = {false, true, false, false};
        assertEquals(TileView.booleanArrayToInt(walls), 4);
    }
}
