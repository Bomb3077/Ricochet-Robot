package com.example.myapplication.map;

import androidx.annotation.Nullable;

import com.example.myapplication.map.GenerateMapStrategy.GenerateMapStrategy;

public class GameMap {
    public Block[][] blocks;
    public static int mapSize = 16;
    GenerateMapStrategy generateMapStrategy;

    public GameMap() {
        this.blocks = new Block[mapSize][mapSize];
    }

    public GameMap(Block[][] blocks) {
        if (blocks.length != mapSize)
            throw new IllegalArgumentException("Illegal map size is" + blocks.length);
        this.blocks = blocks;
    }

    public void setGenerateMapStrategy(GenerateMapStrategy generateMapStrategy) {
        this.generateMapStrategy = generateMapStrategy;
    }

    public void generateMap() {
        generateMapStrategy.generateMap(blocks);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(!(obj instanceof GameMap)) return false;
        GameMap other = (GameMap) obj;
        for(int x=0;x<GameMap.mapSize;x++){
            for(int y=0;y<GameMap.mapSize;y++){
                if(!(this.blocks[x][y].equals(other.blocks[x][y]))) return false;
            }
        }
        return true;
    }

}
