package com.example.myapplication.map.GenerateTokenStrategy;

import com.example.myapplication.map.GameMap;
import com.example.myapplication.map.Location;

public interface GenerateTokenStrategy {
    public void generateToken(GameMap map);
    public void generateToken(GameMap map, Location location);
    public Location genearateTokenLocation(GameMap map);
}
