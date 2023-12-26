package com.example.myapplication.map;

public class Token {
    public static final int totalToken = 8;
    public static int tokenCollected = 0;
    Location location;
    private int tokenID;
    private byte tokenCollectionFlags;

    public int getTokenID() {
        return tokenID;
    }

    public Token(Location location, byte tokenCollectionFlags, int tokenID){
        this.location = location;
        this.tokenCollectionFlags = tokenCollectionFlags;
        this.tokenID = tokenID;
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
