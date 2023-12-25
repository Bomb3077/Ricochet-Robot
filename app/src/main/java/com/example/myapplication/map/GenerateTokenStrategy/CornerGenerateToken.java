package com.example.myapplication.map.GenerateTokenStrategy;

import com.example.myapplication.map.GameMap;
import com.example.myapplication.map.Location;
import com.example.myapplication.map.Token;

public class CornerGenerateToken implements GenerateTokenStrategy{
    @Override
    public void generateToken(GameMap map) {
        map.blocks[0][0].setToken(new Token(new Location(0,0), (byte) 31));
    }
}
