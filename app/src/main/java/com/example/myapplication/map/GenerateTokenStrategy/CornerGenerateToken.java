package com.example.myapplication.map.GenerateTokenStrategy;

import com.example.myapplication.map.Block;
import com.example.myapplication.map.GameMap;
import com.example.myapplication.map.Location;
import com.example.myapplication.map.Token;

public class CornerGenerateToken implements GenerateTokenStrategy{
    @Override
    public void generateToken(GameMap map) {
        Block cornerBlocks[] = new Block[]{map.blocks[0][0], map.blocks[15][0],
                map.blocks[0][15], map.blocks[15][15]};
        int index = 0;
        for(int i=0;i<4;i++){
            if(cornerBlocks[i].getRobotID()==0){
                cornerBlocks[i].setToken(new Token(new Location(0,0), (byte) 31));
                break;
            }
        }
    }
}
