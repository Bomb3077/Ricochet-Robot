package com.example.myapplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.myapplication.graphics.SpriteSheet;
import org.junit.Before;
import org.junit.Test;

public class SpriteSheetUnitTest {
    @Test
    public void booleansToIntTest1(){
        boolean[] walls = {false, true, false, true};
        assertEquals(SpriteSheet.booleanArrayToInt(walls), 5);
    }
    @Test
    public void booleansToIntTes2(){
        boolean[] walls = {true, true, false, true};
        assertEquals(SpriteSheet.booleanArrayToInt(walls), 13);
    }
}
