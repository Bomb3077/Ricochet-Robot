package com.example.myapplication.map;

import androidx.annotation.Nullable;

import java.util.Arrays;

public class Block {
    private Location location;
    private byte wallFlags = 0;
    // top bottom left right
    private int robotID = 0;
    // 0 means no robot
    // 1 to 5 means the robot occupy the block
    private Token token;

    public Block(Location location) {
        this.location = location;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public int getRobotID() {
        return robotID;
    }

    public void setRobotID(int robotID) {
        this.robotID = robotID;
    }

    public void setWalls(byte wallFlags) {
        this.wallFlags = wallFlags;
    }

    private void setBit(boolean wallStatus, int i) {
        wallFlags = (byte) (wallStatus
                ? wallFlags | (1 << i)
                : wallFlags & ~(1 << i));
    }

    public void setTopWall(boolean topWall) {
        setBit(topWall, 3);
    }

    public void setLeftWall(boolean leftWall) {
        setBit(leftWall, 2);
    }

    public void setBottomWall(boolean bottomWall) {
        setBit(bottomWall, 1);
    }

    public void setRightWall(boolean rightWall) {
        setBit(rightWall, 0);
    }

    public byte getWallFlags() {
        return wallFlags;
    }

    private boolean getBit(int i) {
        return (byte) (this.wallFlags >> i) == (byte) 1;
    }

    public boolean getTopWall() {
        return getBit(3);
    }

    public boolean getLeftWall() {
        return getBit(2);
    }

    public boolean getBottomWall() {
        return getBit(1);
    }

    public boolean getRightWall() {
        return getBit(0);
    }


    @Override
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof Block)) return false;
        Block other = (Block) obj;
        return this.location.equals(other.location) && this.robotID == other.robotID &&
                this.wallFlags == other.wallFlags;
    }

    public boolean existTokenAtBlock() {
        return token != null;
    }

    public boolean isTokenCollectable() {
        return (token.getTokenNumber() & (1 << (robotID - 1))) != 0;
    }
}

