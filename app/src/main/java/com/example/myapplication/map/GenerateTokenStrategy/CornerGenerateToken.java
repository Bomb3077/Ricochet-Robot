package com.example.myapplication.map.GenerateTokenStrategy;

import com.example.myapplication.map.GameMap;
import com.example.myapplication.map.Location;
import com.example.myapplication.map.Token;

public class CornerGenerateToken implements GenerateTokenStrategy{
    @Override
    public void generateToken(GameMap map) {
        Location location = genearateTokenLocation(map);
        if(location==null) throw new RuntimeException("cannot find a location to generate");
        map.blocks[location.getX()][location.getY()].setToken(new Token(location, (byte)31, Token.tokenCollected));
    }
    public void generateToken(GameMap map, Location generatedLocation) {
        if(generatedLocation==null) throw new RuntimeException("cannot find a location to generate");
        map.blocks[generatedLocation.getX()][generatedLocation.getY()].setToken(new Token(generatedLocation, (byte)31, Token.tokenCollected));
    }

    @Override
    public Location genearateTokenLocation(GameMap map) {
        for(int x=0;x<16;x+=15){
            for(int y=0;y<16;y+=15) {
                if (map.blocks[x][y].getRobotID() == 0) {
                    return new Location(x, y);
                }
            }
        }
        return null;
    }
}
