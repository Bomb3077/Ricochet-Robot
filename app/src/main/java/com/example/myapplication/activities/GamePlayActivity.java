package com.example.myapplication.activities;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.graphics.RobotView;
import com.example.myapplication.graphics.TileView;
import com.example.myapplication.map.GameMap;
import com.example.myapplication.map.GenerateMapStrategy.BasicGenerate;
import com.example.myapplication.map.GenerateMapStrategy.DefaultGenerate;
import com.example.myapplication.map.Location;
import com.example.myapplication.robot.Robot;

public class GamePlayActivity extends AppCompatActivity {
    LinearLayout gameMapLayout;
    GameMap gameMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialGameMap();
        initialRobots();

        setContentView(R.layout.activity_game_play);
        gameMapLayout = findViewById(R.id.game_map);

        initialGameMapLayout();

    }

    private void initialRobots() {
        Location[] locations = new Location[]{new Location(8, 6)
                , new Location(9, 8), new Location(7, 9), new Location(6, 7)};
        for (int i = 1; i < 5; i++) {
            Robot robot = Robot.getInstance(i);
            robot.setInitialLocation(locations[i - 1], gameMap);
        }
    }

    private void initialGameMap() {
        gameMap = new GameMap();
        gameMap.setGenerateMapStrategy(new BasicGenerate());
        gameMap.generateMap();
    }

    private void initialGameMapLayout() {
        LayoutInflater inflater = getLayoutInflater();
        for (int y = GameMap.mapSize - 1; y >= 0; y--) {// add the 0th row at last since we need coordinates
            LinearLayout mapRow = (LinearLayout) inflater.inflate(R.layout.map_row, gameMapLayout, false);
            for (int x = 0; x < GameMap.mapSize; x++) {
                FrameLayout mapItem = (FrameLayout) inflater.inflate(R.layout.map_item, mapRow, false);
                TileView tileView = new TileView(mapItem.getContext(),
                        new Rect(0, 0, 64, 64), gameMap.blocks[x][y]);
                tileView.setId(1234500 + x * GameMap.mapSize + y);// 1234500 is arbitrary number to ensure unqique ID;
                mapItem.addView(tileView);

                int robotIDAtTile = gameMap.blocks[x][y].getRobotID();
                if (robotIDAtTile != 0) {
                    RobotView robotView = new RobotView(mapItem.getContext(),
                            new Rect(0, 0, 64, 64), Robot.getInstance(robotIDAtTile));
                    robotView.setId(1324500 + robotIDAtTile);//remember ID starts at 1
                    mapItem.addView(robotView);
                }

                mapRow.addView(mapItem);
            }

            gameMapLayout.addView(mapRow);
        }
    }
}