package com.example.myapplication.map;

public class Token {
    Location location;
    int tokenNumber; // specify which robot is acceptable
    public Token(Location location, int tokenNumber){
        this.location = location;
        this.tokenNumber = tokenNumber;
    }

    @Override
    public String toString() {
        return "Token{" +
                "location=" + location.toString() +
                ", tokenNumber=" + tokenNumber +
                '}';
    }
}
