package com.example.myapplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.example.myapplication.map.GameMap;
import com.example.myapplication.map.GenerateMapStrategy.DefaultGenerate;
import com.example.myapplication.map.Location;
import com.example.myapplication.robot.Robot;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class RobotUnitTest {
    GameMap map1 = new GameMap();
    @Before
    public void setUp(){
        GameMap.mapSize = 2;
        map1.setGenerateMapStrategy(new DefaultGenerate());
        map1.generateMap();
    }
    @Test
    public void robotIsUnique(){
        Robot robot1 = Robot.getInstance(1);
        Robot robot2 = Robot.getInstance(1);
        assertTrue(robot1.equals(robot2));
    }
    @Test
    public void robotWithDifferentID(){
        Robot robot1 = Robot.getInstance(1);
        Robot robot2 = Robot.getInstance(2);
        assertFalse(robot1.equals(robot2));
    }
    @Test
    public void robotLocationTest1(){
        Robot robot1 = Robot.getInstance(1);
        robot1.setInitialLocation(new Location(0, 0), map1);
        robot1.moveRobot(new Location(1, 0), map1);
        Location robotLocation = Robot.getLocation(1);
        assertTrue(robotLocation.equals(new Location(1, 0)));
    }
    @Test
    public void blockUpdateTest1(){
        Robot robot1 = Robot.getInstance(1);
        robot1.setInitialLocation(new Location(0, 0), map1);
        robot1.moveRobot(new Location(1, 0), map1);
        assertEquals(map1.blocks[1][0].getRobotID(), 1);
    }
    @Test
    public void robotMoveTest2(){
        Robot robot1 = Robot.getInstance(1);
        robot1.setInitialLocation(new Location(0, 0), map1);
        robot1.moveRobot(new Location(1, 0), map1);
        Robot robot2 = Robot.getInstance(2);
        robot2.setInitialLocation(new Location(0, 0), map1);
        robot2.moveRobot(new Location(1, 0), map1);
        Location robotLocation = Robot.getLocation(2);
        assertTrue(robotLocation.equals(new Location(0,0)));
    }
    @Test
    public void blockUpdateTest2(){
        Robot robot1 = Robot.getInstance(1);
        robot1.setInitialLocation(new Location(0, 0), map1);
        robot1.moveRobot(new Location(1, 0), map1);
        Robot robot2 = Robot.getInstance(2);
        robot2.setInitialLocation(new Location(0, 0), map1);
        robot2.moveRobot(new Location(1, 0), map1);
        Location robotLocation = Robot.getLocation(2);
        assertEquals(map1.blocks[0][0].getRobotID(), 2);
    }
    @Test
    public void robotMoveTest3(){
        Robot robot1 = Robot.getInstance(1);
        robot1.setInitialLocation(new Location(0,0), map1);
        robot1.moveRobot(new Location(0, 1), map1);
        robot1.moveRobot(new Location(1, 0), map1);
        Location robotLocation = Robot.getLocation(1);
        assertEquals(map1.blocks[0][1].getRobotID(), 0);
    }
}