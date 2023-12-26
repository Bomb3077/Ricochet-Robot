package com.example.myapplication.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;
import android.widget.ImageView;

import com.example.myapplication.R;
import com.example.myapplication.map.Block;

public class TileView extends View {
    private final int tileSize = 84;
    private final Bitmap tileSheet = BitmapFactory.decodeResource(getResources(), R.drawable.tile_sheet_32);
    private Rect destination;
    private Block block;

    public TileView(Context context, Rect destination, Block block) {
        super(context);
        this.destination = destination;
        this.block = block;
    }

    public Block getBlock() {
        return block;
    }

    private void drawTile(Canvas canvas, int tileX, int tileY) {

        // Calculate the source rectangle of the tile in the tileset
        int srcX = tileX * tileSize;
        int srcY = tileY * tileSize; // the coordinate of bitmap is top to bottom
        Rect srcRect = new Rect(srcX, srcY, srcX + tileSize, srcY + tileSize);

        canvas.drawBitmap(tileSheet, srcRect, destination, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int blockType = (int)(block.getWallFlags());
        drawTile(canvas, blockType % 4, blockType / 4);
    }

}

