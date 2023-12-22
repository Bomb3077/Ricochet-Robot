package com.example.myapplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import com.example.myapplication.map.Location;

public class LocationUnitTest {
    final Location c1 = new Location(0, 0);
    final Location c2 = new Location(0, 1);
    final Location c3 = new Location(1, 0);
    final Location c4 = new Location(2, 1);
    @Before
    public void setUp(){

    }
    @Test
    public void direction(){
        assertEquals(Location.direction(c1, c2), 'w');
        assertEquals(Location.direction(c1, c3), 'd');
        assertEquals(Location.direction(c4, c2), 'a');
        assertEquals(Location.direction(c1, c4), 'd');
    }
}
