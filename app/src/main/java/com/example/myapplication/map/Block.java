package com.example.myapplication.map;

public class Block implements BlockFactory {
    private Location location;
    private boolean walls[] = {false, false, false, false};
    // top bottom left right
    private int robotID = 0;
    // 0 means no robot
    // 1 to 5 means the robot occupy the block

    public int getRobotID() {
        return robotID;
    }

    private void setRobotID(int robotID) {
        this.robotID = robotID;
    }
    public void updateBlock(int robotID){
        setRobotID(robotID);
    }

    public Block(Location location) {
        this.location = location;
    }

    @Override
    public Block create(Location location) {
        return new Block(location);
    }
    public void setWalls(boolean[] walls) {
        this.walls = walls;
    }
    public void setLeftWall(boolean leftWall){
        this.walls[2] = leftWall;
    }
    public void setTopWall(boolean topWall){
        this.walls[0] = topWall;
    }
    public void setBottomWall(boolean bottomWall){
        this.walls[1] = bottomWall;
    }
    public void setRightWall(boolean rightWall){
        this.walls[3] = rightWall;
    }
    public boolean[] getWalls() {
        return walls;
    }
    public boolean getLeftWall(){
        return walls[2];
    }
    public boolean getRightWall(){
        return walls[3];
    }
    public boolean getTopWall(){
        return walls[0];
    }
    public boolean getBottomWall(){
        return walls[1];
    }

}

