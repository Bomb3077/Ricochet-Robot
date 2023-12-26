package com.example.myapplication.activities;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.graphics.RobotView;
import com.example.myapplication.graphics.TileView;
import com.example.myapplication.graphics.TokenView;
import com.example.myapplication.map.Block;
import com.example.myapplication.map.GameMap;
import com.example.myapplication.map.GenerateMapStrategy.BasicGenerate;
import com.example.myapplication.map.GenerateTokenStrategy.CornerGenerateToken;
import com.example.myapplication.map.GenerateTokenStrategy.GenerateTokenStrategy;
import com.example.myapplication.map.Location;
import com.example.myapplication.map.Token;
import com.example.myapplication.robot.Robot;

public class GamePlayActivity extends AppCompatActivity {
    private static final int MIN_DISTANCE_FOR_SWIPE = 150;
    private float x1, x2, y1, y2; // for swipe
    LinearLayout gameMapLayout;
    GameMap gameMap;
    MediaPlayer movingRobotPlayer, collectTokenPlayer;
    public static int robotIDSelecting = 0;
    GenerateTokenStrategy generateTokenStrategy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialGameMap();
        initialRobots();
        initialTokens();

        setContentView(R.layout.activity_game_play);
        gameMapLayout = findViewById(R.id.game_map);

        initialGameMapLayout();
        setupMediaPlayer();
    }

    private void setupMediaPlayer() {
        movingRobotPlayer = MediaPlayer.create(this, R.raw.robot_moving_sound);
        collectTokenPlayer = MediaPlayer.create(this, R.raw.collect_token);
    }

    private void initialTokens() {
        generateTokenStrategy = new CornerGenerateToken();
        generateToken();
    }

    private void generateToken() {
        if (Token.tokenCollected < Token.totalToken) generateTokenStrategy.generateToken(gameMap);
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

    @SuppressLint("ResourceType")
    private void initialGameMapLayout() {
        LayoutInflater inflater = getLayoutInflater();
        int totalHeight = gameMapLayout.getHeight();
        int rowHeight = totalHeight/GameMap.mapSize;
        int colWidth = rowHeight;
        for (int y = GameMap.mapSize - 1; y >= 0; y--) {// add the 0th row at last since we need coordinates
            LinearLayout mapRow = (LinearLayout) inflater.inflate(R.layout.map_row, gameMapLayout, false);
            for (int x = 0; x < GameMap.mapSize; x++) {
                FrameLayout mapItem = (FrameLayout) inflater.inflate(R.layout.map_item, mapRow, false);
                TileView tileView = new TileView(mapItem.getContext(),gameMap.blocks[x][y]);

                mapItem.addView(tileView);

                int robotIDAtTile = gameMap.blocks[x][y].getRobotID();
                if (robotIDAtTile != 0) {
                    RobotView robotView = new RobotView(mapItem.getContext(),
                            new Rect(0, 0, 64, 64), Robot.getInstance(robotIDAtTile));
                    robotView.setId(1324500 + robotIDAtTile);//remember ID starts at 1
                    mapItem.addView(robotView);
                }

                Token token = gameMap.blocks[x][y].getToken();
                if (token != null) {
                    TokenView tokenView = new TokenView(mapItem.getContext(), new Rect(0, 0, 64, 64), token);
                    tokenView.setId(1512400 + tokenView.getToken().getTokenID());
                    mapItem.addView(tokenView);
                }

                mapItem.setId(1234500 + x * GameMap.mapSize + y);// 1234500 is arbitrary number to ensure unqique ID;
                mapRow.addView(mapItem);
            }

            gameMapLayout.addView(mapRow);
        }
    }

    private void moveRobotView(char direction) {
        int robotID = robotIDSelecting;
        Location currentLocation = Robot.getLocation(robotID);
        Robot robot = Robot.getInstance(robotID);
        FrameLayout mapItemCurrent = findViewById(1234500 + currentLocation.getX() * GameMap.mapSize + currentLocation.getY());
        robot.moveRobot(direction, gameMap);
        Location afterLocation = Robot.getLocation(robotID);
        if (!afterLocation.equals(currentLocation)) {
            if (!movingRobotPlayer.isPlaying()) movingRobotPlayer.start();
            RobotView robotView = findViewById(1324500 + robotID);
            mapItemCurrent.removeView(robotView);

            FrameLayout mapItemAfter = findViewById(1234500 + afterLocation.getX() * GameMap.mapSize + afterLocation.getY());
            mapItemAfter.addView(robotView);
        }
    }

    private void collectTokenView(Location location) {
        int currentX = location.getX();
        int currentY = location.getY();
        Block currentBlock = gameMap.blocks[currentX][currentY];
        if (!(collectTokenPlayer.isPlaying())) collectTokenPlayer.start();
        Robot robot = Robot.getInstance(robotIDSelecting);
        int tokenID = currentBlock.getToken().getTokenID(); // need to get ID before set it to null;
        robot.collectToken(gameMap);
        TokenView tokenView = findViewById(1512400 + tokenID);
        FrameLayout mapItem = findViewById(1234500 + currentX * GameMap.mapSize + currentY);
        mapItem.removeView(tokenView);
        LinearLayout tokenFirstRow = findViewById(R.id.token_first_row);
        tokenFirstRow.addView(tokenView);
    }

    private void addGeneratedTokenView() {
        Location location = generateTokenStrategy.genearateTokenLocation(gameMap);
        generateTokenStrategy.generateToken(gameMap, location);
        FrameLayout mapItem = findViewById(1234500 + location.getX() * GameMap.mapSize + location.getY());
        Token token = gameMap.blocks[location.getX()][location.getY()].getToken();
        TokenView tokenView = new TokenView(mapItem.getContext(), new Rect(0, 0, 64, 64), token);
        tokenView.setId(1512400 + Token.tokenCollected);
        mapItem.addView(tokenView);
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
                float deltaY = y1 - y2;
                if (robotIDSelecting != 0 && (Math.abs(deltaX) > MIN_DISTANCE_FOR_SWIPE || Math.abs(deltaY) > MIN_DISTANCE_FOR_SWIPE)) {
                    char direction = Math.abs(deltaX) > Math.abs(deltaY) ?
                            (deltaX > 0 ? 'd' : 'a') :
                            (deltaY > 0 ? 'w' : 's');
                    moveRobotView(direction);
                    if (tokenIsCollectable(Robot.getLocation(robotIDSelecting))) {
                        collectTokenView(Robot.getLocation(robotIDSelecting));
                        addGeneratedTokenView();
                    }
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    private boolean tokenIsCollectable(Location location) {
        int currentX = location.getX();
        int currentY = location.getY();
        Block currentBlock = gameMap.blocks[currentX][currentY];
        if (!(currentBlock.existTokenAtBlock())) return false;
        return currentBlock.isTokenCollectable();
    }
}