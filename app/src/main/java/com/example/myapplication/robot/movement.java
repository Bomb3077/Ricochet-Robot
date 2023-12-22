package com.example.myapplication.robot;

import com.example.myapplication.map.GameMap;
import com.example.myapplication.map.Location;

public interface movement {
    void moveRobot(int ID, Location target, GameMap map);
}
