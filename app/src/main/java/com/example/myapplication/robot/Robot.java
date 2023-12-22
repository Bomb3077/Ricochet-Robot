package com.example.myapplication.robot;

import com.example.myapplication.map.Location;
import com.example.myapplication.map.GameMap;

public class Robot implements movement {
    int ID;
    private static Robot robot1, robot2, robot3, robot4, robot5;
    Location location;

    private Robot(int ID, Location startLocation) {
        this.ID = ID;
        this.location = startLocation;
    }

    public static Robot getInstance(int ID) {
        switch (ID) {
            case 1:
                if (robot1 == null) robot1 = new Robot(1, new Location(0, 0));

                return robot1;
            case 2:
                if (robot2 == null) robot2 = new Robot(2, new Location(0, 0));

                return robot2;
            case 3:
                if (robot3 == null) robot3 = new Robot(3, new Location(0, 0));

                return robot3;
            case 4:
                if (robot4 == null) robot4 = new Robot(4, new Location(0, 0));
                return robot4;
            case 5:
                if (robot5 == null) robot5 = new Robot(5, new Location(0, 0));
                return robot5;
            default:
                throw new IllegalArgumentException("Invalid robot ID: " + ID);
        }
    }

    @Override
    public void moveRobot(int ID, Location target, GameMap map) {
        char direction = Location.direction(getLocation(ID), target);
        Location currectLocation = getLocation(ID);
        int x, y;
        switch (direction) {
            case 'w':
                x = currectLocation.getX();
                for (y = currectLocation.getY(); y < GameMap.mapSize; y++) {
                    if (map.blocks[x][y].getTopWall() ||
                            (y + 1 < GameMap.mapSize && map.blocks[x][y].getBottomWall()
                                    && map.blocks[x][y].getRobotID() == 0))
                        break;
                }
                break;
            case 'a':
                y = currectLocation.getY();
                for (x = currectLocation.getX(); x > 0; x--) {
                    if (map.blocks[x][y].getLeftWall() ||
                            (x - 1 > 0 && map.blocks[x - 1][y].getRightWall()))
                        break;
                }
                break;
            case 's':
                x = currectLocation.getX();
                for (y = currectLocation.getY(); y > 0; y--) {
                    if (map.blocks[x][y].getTopWall() ||
                            (y + 1 < GameMap.mapSize && map.blocks[x][y].getBottomWall()))
                        break;
                }
                break;
            case 'd':
                y = currectLocation.getY();
                for (x = currectLocation.getX(); x < GameMap.mapSize; x++) {
                    if (map.blocks[x][y].getRightWall() ||
                            (x + 1 < GameMap.mapSize && map.blocks[x + 1][y].getLeftWall()))
                        break;
                }
                break;
            default:
                return;
        }
        map.blocks[x][y].updateBlock(ID);
        Robot.setLocation(ID, new Location(x, y));
    }

    public static void setLocation(int ID, Location newLocation) {
        switch (ID) {
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
