package com.example.myapplication.robot;

import com.example.myapplication.map.GameMap;
import com.example.myapplication.map.Location;

public interface movement {
    void moveRobot(Location target, GameMap map);
}
