package com.example.myapplication.map;

public class Token {
    Location location;
    private byte tokenNumber; // specify which robot is acceptable for first five robot number
    // 00011000 means accept 4 and 5
    public Token(Location location, byte tokenNumber){
        this.location = location;
        this.tokenNumber = tokenNumber;
    }

    public byte getTokenNumber() {
        return tokenNumber;
    }

    @Override
    public String toString() {
        return "Token{" +
                "location=" + location.toString() +
                ", tokenNumber=" + tokenNumber +
                '}';
    }
}
