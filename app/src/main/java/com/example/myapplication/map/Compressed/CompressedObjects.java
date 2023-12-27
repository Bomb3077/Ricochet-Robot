package com.example.myapplication.map.Compressed;

public class CompressedObjects {
    byte[] robotLocations;
    short target;

    public CompressedObjects(byte[] robotLocations, short target) {
        this.robotLocations = robotLocations;
        this.target = target;
    }

    public static CompressedObjects moveRobot(CompressedObjects objects, CompressedClassicMap map, char direction, int robotID) {
        byte robotLocation = objects.robotLocations[robotID - 1];
        byte currColumn, currRow;
        int count = 0;
        switch (direction) {
            case 'N':
                currColumn = map.col[robotLocation & 15];
                while (getDigit(currColumn, (robotLocation >> 4) + count)) count++;
                return newObjects(count, objects, robotID);
            case 'S':
                currColumn = map.col[robotLocation & 15];
                while (getDigit(currColumn, (robotLocation >> 4) - count)) count--;
                return newObjects(count, objects, robotID);
            case 'W':
                currRow = map.row[robotLocation >> 4];
                while (getDigit(currRow, (robotLocation & 15) - count)) count--;
                return newObjects(count, objects, robotID);
            case 'E':
                currRow = map.row[robotLocation >> 4];
                while (getDigit(currRow, (robotLocation & 15) + count)) count++;
                return newObjects(count, objects, robotID);
            default:
                throw new RuntimeException("Wrong Direction");
        }
    }

    private static boolean getDigit(byte a, int i) {
        if(i>=0)
            return (byte) ((a) & 1 << i) == (byte) 1 << i;
        return (byte) ((a) & 1 << (16+i)) == (byte) 1 << (16+i);
    }

    private static CompressedObjects newObjects(int count, CompressedObjects objects, int robotID) {
        CompressedObjects compressedObjects = new CompressedObjects(objects.robotLocations.clone(), objects.target);
        compressedObjects.robotLocations[robotID - 1] = newRobotLocation(compressedObjects.robotLocations[robotID - 1], count);
        return compressedObjects;
    }

    private static byte newRobotLocation(byte b, int count) {
        int unsignedB = b & 0xFF;
        unsignedB -= 16 * count;
        return (byte) unsignedB;
    }
}
