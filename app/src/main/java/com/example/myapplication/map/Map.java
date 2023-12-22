package com.example.myapplication.map;

public class Map {
    public Block[][] blocks;
    final int mapSize = 16;
    GenerateMapStrategy generateMapStrategy;
    public Map() {
        this.blocks = new Block[mapSize][mapSize];
    }
    public Map(Block[][] blocks){
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
