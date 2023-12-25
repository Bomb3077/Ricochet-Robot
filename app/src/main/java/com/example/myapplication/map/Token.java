package com.example.myapplication.map;

public class Token {
    public static final int totalToken = 8;
    public static int tokenCollected;
    Location location;
    private byte tokenCollectionFlags;
    public Token(Location location, byte tokenCollectionFlags){
        this.location = location;
        this.tokenCollectionFlags = tokenCollectionFlags;
    }
    public static void countCollectedToken(){
        tokenCollected++;
    }

    public byte getTokenNumber() {
        return tokenCollectionFlags;
    }

    @Override
    public String toString() {
        return "Token{" +
                "location=" + location.toString() +
                ", tokenNumber=" + tokenCollectionFlags +
                '}';
    }
}
