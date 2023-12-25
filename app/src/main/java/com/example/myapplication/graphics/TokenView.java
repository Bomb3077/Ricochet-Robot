package com.example.myapplication.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.myapplication.R;
import com.example.myapplication.map.Token;

public class TokenView extends View {
    private final int tokenWidth = 896;
    private final int tokenHeight = 1010;
    private final Bitmap tokenSheet = BitmapFactory.decodeResource(getResources(), R.drawable.token_sheet);
    private Rect destination;
    private Token token;

    public TokenView(Context context, Rect destination, Token token) {
        super(context);
        this.destination = destination;
        this.token = token;
    }


    public Token getToken() {
        return token;
    }

    private void drawToken(Canvas canvas, int tokenX, int tokenY) {

        int srcX = tokenX * tokenWidth;
        int srcY = tokenY * tokenHeight;
        Rect srcRect;
        srcRect = new Rect(srcX, srcY, srcX + tokenWidth, srcY + tokenHeight); //adjust for alignment issue in the sheet
        canvas.drawBitmap(tokenSheet, srcRect, destination, null);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        drawToken(canvas, 0, 0);
    }
}
