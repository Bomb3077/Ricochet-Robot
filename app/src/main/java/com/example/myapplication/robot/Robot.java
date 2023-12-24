package com.example.myapplication.robot;

import com.example.myapplication.map.Location;
import com.example.myapplication.map.GameMap;

public class Robot implements movement {


    private int ID;
    private static Robot robot1, robot2, robot3, robot4, robot5;
    Location location;

    private Robot(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }
    public static Robot getInstance(int ID) {
        switch (ID) {
            case 1:
                if (robot1 == null) robot1 = new Robot(1);
                return robot1;
            case 2:
                if (robot2 == null) robot2 = new Robot(2);
                return robot2;
            case 3:
                if (robot3 == null) robot3 = new Robot(3);
                return robot3;
            case 4:
                if (robot4 == null) robot4 = new Robot(4);
                return robot4;
            case 5:
                if (robot5 == null) robot5 = new Robot(5);
                return robot5;
            default:
                throw new IllegalArgumentException("Invalid robot ID: " + ID);
        }
    }

    @Override
    public void moveRobot(char direction, GameMap map) {
        Location currectLocation = getLocation(ID);
        int x = currectLocation.getX(), y = currectLocation.getY();
        switch (direction) {
            case 'w':
                while (y < GameMap.mapSize && !map.blocks[x][y].getTopWall()) {
                    if (map.blocks[x][y + 1].getBottomWall()) break;
                    if (map.blocks[x][y + 1].getRobotID() != 0) break;
                    y++;
                }
                break;
            case 'a':
                while (x > 0 && !map.blocks[x][y].getLeftWall()) {
                    if (map.blocks[x - 1][y].getRightWall()) break;
                    if (map.blocks[x - 1][y].getRobotID() != 0) break;
                    x--;
                }
                break;
            case 's':
                while (y > 0 && !map.blocks[x][y].getBottomWall()) {
                    if (map.blocks[x][y - 1].getTopWall()) break;
                    if (map.blocks[x][y - 1].getRobotID() != 0) break;
                    y--;
                }
                break;
            case 'd':
                while (x < GameMap.mapSize && !map.blocks[x][y].getRightWall()) {
                    if (map.blocks[x + 1][y].getLeftWall()) break;
                    if (map.blocks[x + 1][y].getRobotID() != 0) break;
                    x++;
                }
                break;
            default:
                return;
        }
        map.blocks[currectLocation.getX()][currectLocation.getY()].updateBlock(0);
        map.blocks[x][y].updateBlock(ID);
        this.setLocation(new Location(x, y));
    }

    public void setInitialLocation(Location start, GameMap map) {
        if (map.blocks[start.getX()][start.getY()].getRobotID() != 0)
            throw new IllegalArgumentException("there is robot already in this block");
        setLocation(start);
        map.blocks[start.getX()][start.getY()].updateBlock(this.ID);
    }

    public void setLocation(Location newLocation) {
        switch (this.ID) {
            case 1:
                robot1.location = newLocation;
                break;
            case 2:
                robot2.location = newLocation;
                break;
            case 3:
                robot3.location = newLocation;
                break;
            case 4:
                robot4.location = newLocation;
                break;
            case 5:
                robot5.location = newLocation;
                break;
            default:
                throw new IllegalArgumentException("Invalid robot ID: " + ID);
        }
    }

    public static Location getLocation(int ID) {
        switch (ID) {
            case 1:
                return robot1.location;
            case 2:
                return robot2.location;
            case 3:
                return robot3.location;
            case 4:
                return robot4.location;
            case 5:
                return robot5.location;
            default:
                throw new IllegalArgumentException("Invalid robot ID: " + ID);
        }
    }
}
