package com.example.myapplication.activities;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.graphics.RobotView;
import com.example.myapplication.graphics.TileView;
import com.example.myapplication.map.GameMap;
import com.example.myapplication.map.GenerateMapStrategy.BasicGenerate;
import com.example.myapplication.map.Location;
import com.example.myapplication.robot.Robot;

public class GamePlayActivity extends AppCompatActivity {
    private static final int SWIPE_THRESHOLD = 30; // Adjust this value based on testing
    private static final int SWIPE_VELOCITY_THRESHOLD = 10;
    LinearLayout gameMapLayout;
    GameMap gameMap;
    private float x1, x2, y1, y2; // for swipe
    static final int MIN_DISTANCE = 150;

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

                mapItem.addView(tileView);

                int robotIDAtTile = gameMap.blocks[x][y].getRobotID();
                if (robotIDAtTile != 0) {
                    RobotView robotView = new RobotView(mapItem.getContext(),
                            new Rect(0, 0, 64, 64), Robot.getInstance(robotIDAtTile));
                    robotView.setId(1324500 + robotIDAtTile);//remember ID starts at 1
                    mapItem.addView(robotView);
                }

                mapItem.setId(1234500 + x * GameMap.mapSize + y);// 1234500 is arbitrary number to ensure unqique ID;
                mapRow.addView(mapItem);
            }

            gameMapLayout.addView(mapRow);
        }
    }

    private void moveRobotView(char direction) {
        Location currentLocation = Robot.getLocation(1);
        Robot robot = Robot.getInstance(1);
        FrameLayout mapItemCurrent = findViewById(1234500 + currentLocation.getX() * GameMap.mapSize + currentLocation.getY());
        robot.moveRobot(direction, gameMap);
        Location afterLocation = Robot.getLocation(1);
        if (!afterLocation.equals(currentLocation)) {
            RobotView robotView = findViewById(1324500 + robot.getID());
            mapItemCurrent.removeView(robotView);

            FrameLayout mapItemAfter = findViewById(1234500 + afterLocation.getX() * GameMap.mapSize + afterLocation.getY());
            mapItemAfter.addView(robotView);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                y1 = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                y2 = event.getY();
                float deltaX = x2 - x1;
                float deltaY = y1 - y2; // screen is top to bottom
                if (!(Math.abs(deltaX) > MIN_DISTANCE || Math.abs(deltaY) > MIN_DISTANCE)) break;
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    if (deltaX > 0) {
                        moveRobotView('d');
                    } else {
                        moveRobotView('a');
                    }
                } else {
                    if (deltaY > 0) {
                        moveRobotView('w');
                    } else {
                        moveRobotView('s');
                    }
                }
                break;
        }
        return super.onTouchEvent(event);
    }


}