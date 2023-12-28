package com.example.myapplication.map.Compressed;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.Arrays;

public class CompressedObjects {
    public byte[] robotLocations;
    short target;

    public CompressedObjects(byte[] robotLocations, short target) {
        this.robotLocations = robotLocations.clone();
        this.target = target;
    }

    public CompressedObjects(CompressedObjects compressedObjects) {
        this.robotLocations = compressedObjects.robotLocations.clone();
        this.target = compressedObjects.target;
    }

    public static CompressedObjects moveRobot(CompressedClassicMap map, CompressedObjects objects, char direction, int robotID) {
        byte robotLocation = objects.robotLocations[robotID - 1];
        byte newRobotLocation;
        byte[] robotLocationsClone = objects.robotLocations.clone();
        short currColumn, currRow, robotsAndWall;
        int count = 0;
        switch (direction) {
            case 'N':
                currColumn = (short) (map.col[15 - robotLocation & 15] << 1);
                robotsAndWall = helper1(currColumn, robotLocation, objects.robotLocations);
                while (getDigit(robotsAndWall, ((robotLocation >> 4) & 15) + count + 1)) count++;
                if (count == 0) return new CompressedObjects(objects);
                newRobotLocation = (byte) (((((robotLocation >> 4) & 15) + count) << 4) | (robotLocation & 15));
                robotLocationsClone[robotID - 1] = newRobotLocation;

                return new CompressedObjects(robotLocationsClone, objects.target);
            case 'S':
                currColumn = map.col[15 - robotLocation & 15];
                robotsAndWall = helper1(currColumn, robotLocation, objects.robotLocations);
                while (getDigit(robotsAndWall, ((robotLocation >> 4) & 15) - count - 1)) count++;
                if (count == 0) return new CompressedObjects(objects);
                newRobotLocation = (byte) (((((robotLocation >> 4) & 15) - count) << 4) | (robotLocation & 15));
                robotLocationsClone[robotID - 1] = newRobotLocation;

                return new CompressedObjects(robotLocationsClone, objects.target);
            case 'W':
                currRow = map.row[15 - ((robotLocation >> 4) & 15)];
                robotsAndWall = helper2(currRow, robotLocation, objects.robotLocations);
                while (getDigit(robotsAndWall, (robotLocation & 15) - count - 1)) count++;
                if (count == 0) return new CompressedObjects(objects);
                newRobotLocation = (byte) (((robotLocation & 15) - count) | (robotLocation & (15 << 4)));
                robotLocationsClone[robotID - 1] = newRobotLocation;

                return new CompressedObjects(robotLocationsClone, objects.target);
            case 'E':
                currRow = (short) (map.row[15 - ((robotLocation >> 4) & 15)] << 1);
                robotsAndWall = helper2(currRow, robotLocation, objects.robotLocations);
                while (getDigit(robotsAndWall, (robotLocation & 15) + count + 1)) count++;
                if (count == 0) return new CompressedObjects(objects);
                newRobotLocation = (byte) (((robotLocation & 15) + count) | (robotLocation & (15 << 4)));
                robotLocationsClone[robotID - 1] = newRobotLocation;

                return new CompressedObjects(robotLocationsClone, objects.target);
            default:
                throw new RuntimeException("Wrong Direction");
        }
    }

    private static short helper2(short currRow, byte currRobot, byte[] robotLocations) {
        for (int i = 0; i < robotLocations.length; i++) {
            if ((currRobot >> 4) == (robotLocations[i] >> 4))
                currRow |= 1 << (robotLocations[i] & 15);
        }
        return currRow;
    }

    private static short helper1(short currColumn, byte currRobot, byte[] robotLocations) {
        for (int i = 0; i < robotLocations.length; i++) {
            if ((currRobot & 15) == (robotLocations[i] & 15))
                currColumn |= 1 << (15 - (robotLocations[i] >> 4));
        }
        return currColumn;
    }


    private static boolean getDigit(short a, int i) {
        if (i > 15 || i < 0) return false;
        return (short) ((a) & 1 << i) != (short) 1 << i;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof CompressedObjects obj_)) return false;
        return Arrays.equals(obj_.robotLocations, this.robotLocations) && obj_.target == this.target;
    }
}
