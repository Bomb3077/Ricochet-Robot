package com.example.myapplication.map.Compressed;

public class CompressedObjects {
    public byte[] robotLocations;
    short target;

    public CompressedObjects(byte[] robotLocations, short target) {
        this.robotLocations = robotLocations;
        this.target = target;
    }

    public static CompressedObjects moveRobot(CompressedClassicMap map, CompressedObjects objects,  char direction, int robotID) {
        byte robotLocation = objects.robotLocations[robotID - 1];
        short currColumn, currRow;
        int count = 0;
        switch (direction) {
            case 'N':
                currColumn = (short) (map.col[15-(robotLocation & 15)] << 1);
                short robotsAndWall = helper1(currColumn, robotLocation, objects.robotLocations);
                while (getDigit(robotsAndWall, Math.abs(robotLocation >> 4) + count + 1)) count++;

                byte newRobotLocation = (byte) (((Math.abs(robotLocation >> 4) + (count))<<4) | (robotLocation & 15));
                byte[] robotLocationsClone = objects.robotLocations.clone();
                robotLocationsClone[robotID-1] = newRobotLocation;

                return new CompressedObjects(robotLocationsClone,objects.target);
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

    private static short helper1(short currColumn, byte currRobot, byte[] robotLocations) {
        for (int i = 0; i < robotLocations.length; i++) {
            if ((currRobot & 15) == (robotLocations[i] & 15))
                currColumn |= 1 << (15 - (robotLocations[i] >> 4));
        }
        return currColumn;
    }


    private static boolean getDigit(short a, int i) {
        if(i>16||i<-16) return false;
        if (i >= 0)
            return (short) ((a) & 1 << i) != (short) 1 << i;
        return (short) ((a) & 1 << (15 + i)) != (short) 1 << (15 + i); // only uses first 15 bit of short
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
