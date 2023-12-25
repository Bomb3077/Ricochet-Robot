package com.example.myapplication.map.GenerateTokenStrategy;

import android.util.Log;

import com.example.myapplication.map.GameMap;
import com.example.myapplication.map.Location;
import com.example.myapplication.map.Token;

import java.util.Random;


public class DefaultGenerateToken implements GenerateTokenStrategy{
    @Override
    public void generateToken(GameMap map) {
        Random random = new Random();
        int x, y;
        do {
            x = random.nextInt(16);
            y = random.nextInt(16);
        } while (map.blocks[x][y].getRobotID() != 0 || map.blocks[x][y].getToken() != null);
        Token token = new Token(new Location(x, y), (byte)(random.nextInt(5)+1));
        Log.d("token", token.toString());
        map.blocks[x][y].setToken(token);
    }
}
