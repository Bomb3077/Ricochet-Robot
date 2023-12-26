package com.example.myapplication.graphics;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.myapplication.R;
import com.example.myapplication.map.Block;

public class TileView extends ImageView {
    private Block block;

    @SuppressLint("RestrictedApi")
    public TileView(Context context, Block block) {
        super(context);
        this.block = block;
        init();
        scaleToParent();
    }
    private void init(){
        switch (this.getBlock().getWallFlags()){
            case 0:
                setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.tile));
                break;
            case 1:
                setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.tile_right));
                break;
            case 2:
                setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.tile_but));
                break;
            case 3:
                setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.tile_but_right));
                break;
            case 4:
                setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.tile_left));
                break;
            case 6:
                setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.tile_but_left));
                break;
            case 8:
                setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.tile_top));
                break;
            case 9:
                setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.tile_top_right));
                break;
            case 12:
                setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.tile_top_left));
                break;
            default:
                throw new RuntimeException("cannot find matching image for tile");
        }
    }
    private void scaleToParent() {
        setAdjustViewBounds(true); // If you want to adjust the bounds of the ImageView to preserve the aspect ratio of its drawable
        setScaleType(ImageView.ScaleType.CENTER_CROP); // Change as needed

        // Set the layout parameters to match the parent size
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );
        setLayoutParams(params);
    }
    public Block getBlock() {
        return block;
    }
}

