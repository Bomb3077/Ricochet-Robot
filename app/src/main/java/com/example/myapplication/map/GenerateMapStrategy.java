package com.example.myapplication.map;

public interface GenerateMapStrategy {
    void generateMap(Block[][] blocks);
}

class defaultGenerate implements GenerateMapStrategy {
    @Override
    public void generateMap(Block[][] blocks) {
        int mapSize = GameMap.mapSize;
        for (int x = 0; x < mapSize; x++) {
            for (int y = 0; y < mapSize; y++) {
                blocks[x][y] = new Block(new Location(x, y));
                if(x==0) blocks[x][y].setLeftWall(true);
                if(y==0) blocks[x][y].setBottomWall(true);
                if(x==mapSize-1) blocks[x][y].setRightWall(true);
                if(y==mapSize-1) blocks[x][y].setTopWall(true);
            }
        }
    }
}
