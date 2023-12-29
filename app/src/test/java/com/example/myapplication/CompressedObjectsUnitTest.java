package com.example.myapplication;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.example.myapplication.map.Compressed.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CompressedObjectsUnitTest {
    CompressedClassicMap map;
    @Before
    public void setup(){
        short[] row = {
                0b0010000000010000,
                0b0000100001000000,
                0b0010000000000001,
                0b0000000100000000,

                0b0000000000010000,
                0b0000000000000000,
                0b0001000000001000,
                0b0000000101000000,

                0b0000010101000000,
                0b0010000000000000,
                0b0000000010000100,
                0b0000000000010000,

                0b0000000000000010,
                0b0000010000000000,
                0b0010000000100000,
                0b0000001000001000
        };
        short[] col = {
                0b0000001000001000,
                0b0010000000000001,
                0b0000000010000000,
                0b0000000100000000,

                0b0100000000000100,
                0b0000000001000000,
                0b0000100000000000,
                0b0000000101000000,

                0b0000000101100000,
                0b0010000000000010,
                0b0000010000001000,
                0b0000000000000000,

                0b0000001000000000,
                0b0000000000100000,
                0b0010000000000100,
                0b0000010001000000
        };
        map = new CompressedClassicMap(row, col);
    }
    @Test
    public void moveRobotNorth(){
        byte[] robotLocations = {(byte) 0b10000000};
        byte target = 0;
        CompressedObjects objects = new CompressedObjects(robotLocations, target);
        CompressedObjects newObjects = CompressedObjects.moveRobot(map, objects, 'N', 1);
        assertTrue((newObjects.robotLocations[0])==(byte) 0b10100000);
    }
    @Test
    public void moveRobotNorth2(){
        byte[] robotLocations = {(byte) 0b10111010};
        byte target = 0;
        CompressedObjects objects = new CompressedObjects(robotLocations, target);
        CompressedObjects newObjects = CompressedObjects.moveRobot(map, objects, 'N', 1);
        assertTrue((newObjects.robotLocations[0])==(byte) 0b11111010);
    }
    @Test
    public void moveRobotSouth(){
        byte[] robotLocations = {(byte) 0b10111010};
        byte target = 0;
        CompressedObjects objects = new CompressedObjects(robotLocations, target);
        CompressedObjects newObjects = CompressedObjects.moveRobot(map, objects, 'S', 1);
        assertTrue((newObjects.robotLocations[0])==(byte) 0b01111010);
    }
    @Test
    public void moveRobotSouthTwoRobot(){
        byte[] robotLocations = {(byte) 0b10111010,
                (byte) 0b10011010};
        byte target = 0;
        CompressedObjects objects = new CompressedObjects(robotLocations, target);
        CompressedObjects newObjects = CompressedObjects.moveRobot(map, objects, 'S', 1);
        assertTrue((newObjects.robotLocations[0])==(byte) 0b10101010);
    }
    @Test
    public void moveRobotEast(){
        byte[] robotLocations = {(byte) 0b10010111};
        byte target = 0;
        CompressedObjects objects = new CompressedObjects(robotLocations, target);
        CompressedObjects newObjects = CompressedObjects.moveRobot(map, objects, 'E', 1);
        assertTrue((newObjects.robotLocations[0])==(byte) 0b10011100);
    }
    @Test
    public void moveRobotWest(){
        byte[] robotLocations = {(byte) 0b01001101};
        byte target = 0;
        CompressedObjects objects = new CompressedObjects(robotLocations, target);
        CompressedObjects newObjects = CompressedObjects.moveRobot(map, objects, 'W', 1);
        assertTrue((newObjects.robotLocations[0])==(byte) 0b01000101);
    }@Test
    public void moveRobotWestTwoRobot(){
        byte[] robotLocations = {(byte) 0b01001101,
                (byte) 0b01000111};
        byte target = 0;
        CompressedObjects objects = new CompressedObjects(robotLocations, target);
        CompressedObjects newObjects = CompressedObjects.moveRobot(map, objects, 'W', 1);
        assertTrue((newObjects.robotLocations[0])==(byte) 0b01001000);
    }
}
