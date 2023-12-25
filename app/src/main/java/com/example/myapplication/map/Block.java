package com.example.myapplication.map;

import androidx.annotation.Nullable;

import java.util.Arrays;

public class Block {
    private Location location;
    private boolean walls[];
    // top bottom left right
    private int robotID = 0;
    // 0 means no robot
    // 1 to 5 means the robot occupy the block
    private Token token;

    public Block(Location location) {
        this.location = location;
        setWalls(new boolean[]{false, false, false, false});
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

    public void setWalls(boolean[] walls) {
        this.walls = walls;
    }

    public void setLeftWall(boolean leftWall) {
        this.walls[2] = leftWall;
    }

    public void setTopWall(boolean topWall) {
        this.walls[0] = topWall;
    }

    public void setBottomWall(boolean bottomWall) {
        this.walls[1] = bottomWall;
    }

    public void setRightWall(boolean rightWall) {
        this.walls[3] = rightWall;
    }

    public boolean[] getWalls() {
        return walls;
    }

    public boolean getLeftWall() {
        return walls[2];
    }

    public boolean getRightWall() {
        return walls[3];
    }

    public boolean getTopWall() {
        return walls[0];
    }

    public boolean getBottomWall() {
        return walls[1];
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof Block)) return false;
        Block other = (Block) obj;
        return this.location.equals(other.location) && this.robotID == other.robotID &&
                Arrays.equals(this.walls, other.walls);
    }

    public boolean existTokenAtBlock() {
        return token!=null;
    }

    public boolean isTokenCollectable() {
        return (token.getTokenNumber() & (1<<(robotID-1)))!=0;
    }
}

