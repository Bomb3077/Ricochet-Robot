package com.example.myapplication.robot;

import com.example.myapplication.map.Location;

public class Robot {
    int ID;
    Robot robot1, robot2, robot3, robot4, robot5;
    Location location;
    private Robot(int ID, Location startLocation) {
        this.ID = ID;
        this.location = startLocation;
    }
    public Robot getInstance(int ID){
        switch (ID){
            case 1:
                if(robot1 == null) return new Robot(1, new Location(0, 0));
                return robot1;
            case 2:
                if(robot2 == null) return new Robot(2, new Location(0, 0));
                return robot2;
            case 3:
                if(robot3 == null) return new Robot(2, new Location(0, 0));
                return robot3;
            case 4:
                if(robot4 == null) return new Robot(2, new Location(0, 0));
                return robot4;
            case 5:
                if(robot5 == null) return new Robot(2, new Location(0, 0));
                return robot5;
            default:
                throw new IllegalArgumentException("Invalid robot ID: " + ID);
        }
    }
    public void moveRobot(int ID, Location newLocation){
        setLocation(ID, newLocation);
    }
    private void setLocation(int ID, Location newLocation){
        switch (ID){
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
}
