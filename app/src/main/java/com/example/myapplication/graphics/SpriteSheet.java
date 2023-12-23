package com.example.myapplication.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.myapplication.R;

public class SpriteSheet {
    private static final int SPRITE_WIDTH_PIXELS = 32;
    private static final int SPRITE_HEIGHT_PIXELS = 32;
    private Bitmap bitmap;

    public SpriteSheet(Context context) {
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.tile_sheet, bitmapOptions);
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public Sprite getSpriteByWalls(boolean[] walls) {
        int row = booleanArrayToInt(walls) / 4;
        int col = booleanArrayToInt(walls) % 4;
        return new Sprite(this, new Rect(col * SPRITE_WIDTH_PIXELS,
                row * SPRITE_HEIGHT_PIXELS, (col + 1) * SPRITE_WIDTH_PIXELS,
                (row + 1) * SPRITE_HEIGHT_PIXELS));
    }

    public static int booleanArrayToInt(boolean[] walls) {
        int result = 0;
        for (int i = 0; i < walls.length; i++) {
            if (walls[walls.length - i - 1]) {
                result |= (1<<i);
            }
        }
        return result;
    }

}
