package com.example.myapplication.robot;

import com.example.myapplication.map.GameMap;

public interface Action {
    void moveRobot(char direction, GameMap map);
    void collectToken(GameMap map);
}
