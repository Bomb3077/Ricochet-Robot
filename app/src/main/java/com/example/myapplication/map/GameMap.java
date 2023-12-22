package com.example.myapplication.map;

public class GameMap {
    public Block[][] blocks;
    public final static int mapSize = 16;
    GenerateMapStrategy generateMapStrategy;
    public GameMap() {
        this.blocks = new Block[mapSize][mapSize];
    }
    public GameMap(Block[][] blocks){
        if(blocks.length!=mapSize) throw new IllegalArgumentException("Illegal map size is" + blocks.length);
        this.blocks = blocks;
    }
    public void setGenerateMapStrategy(GenerateMapStrategy generateMapStrategy) {
        this.generateMapStrategy = generateMapStrategy;
    }
    public void generateMap(){
        generateMapStrategy.generateMap(blocks);
    }
}
