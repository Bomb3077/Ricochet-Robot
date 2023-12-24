package com.example.myapplication.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.map.Block;
import com.example.myapplication.robot.Robot;

public class RobotView extends View {
    private final int robotWidth = 896;
    private final int robotHeight = 1020;
    private final Bitmap robotSheet = BitmapFactory.decodeResource(getResources(), R.drawable.robots_sheet);
    private Rect destination;
    private Robot robot;

    public RobotView(Context context, Rect destination, Robot robot) {
        super(context);
        this.destination = destination;
        this.robot = robot;
    }

    public Robot getRobot() {
        return robot;
    }

    private void drawTile(Canvas canvas, int robotX, int robotY) {

        int srcX = robotX * robotWidth;
        int srcY = robotY * robotHeight;
        Rect srcRect;
        if (robotY == 1)
            srcRect = new Rect(srcX, srcY + 172, srcX + robotWidth, srcY + robotHeight + 172); //adjust for alignment issue in the sheet
        else srcRect = new Rect(srcX, srcY, srcX + robotWidth, srcY + robotHeight);
        canvas.drawBitmap(robotSheet, srcRect, destination, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int robotID = robot.getID();
        //robotID starts with 1
        drawTile(canvas, (robotID - 1) % 3, (robotID - 1) / 3);
    }
}
