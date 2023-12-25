package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Rect;
import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.graphics.TokenView;
import com.example.myapplication.map.Location;
import com.example.myapplication.map.Token;

public class TestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TokenView tokenView= new TokenView(this, new Rect(0,0,512,512), new Token(new Location(1, 1), (byte)0));
        setContentView(tokenView);
    }
}