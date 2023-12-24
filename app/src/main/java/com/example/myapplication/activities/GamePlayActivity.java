package com.example.myapplication.activities;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.graphics.TileView;
import com.example.myapplication.map.GameMap;
import com.example.myapplication.map.GenerateMapStrategy.DefaultGenerate;

public class GamePlayActivity extends AppCompatActivity {
    LinearLayout gameMapLayout;
    GameMap gameMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialGameMap();

        setContentView(R.layout.activity_game_play);
        gameMapLayout = findViewById(R.id.game_map);

        LayoutInflater inflater = getLayoutInflater();
        for (int y = GameMap.mapSize-1; y >= 0; y--) {// add the 0th row at last since we need coordinates
            LinearLayout mapRow = (LinearLayout) inflater.inflate(R.layout.map_row, gameMapLayout, false);
            for (int x = 0; x < GameMap.mapSize; x++) {
                LinearLayout mapItem = (LinearLayout) inflater.inflate(R.layout.map_item, mapRow, false);
                TileView tileView = new TileView(mapItem.getContext(),
                        new Rect(0, 0, 64, 64), gameMap.blocks[x][y]);
                tileView.setId(1234500 + x * GameMap.mapSize + y);// 1234500 is arbitrary number to ensure unqique ID;
                mapItem.addView(tileView);
                mapRow.addView(mapItem);
            }

            gameMapLayout.addView(mapRow);
        }
    }

    private void initialGameMap() {
        gameMap = new GameMap();
        gameMap.setGenerateMapStrategy(new DefaultGenerate());
        gameMap.generateMap();
    }

}