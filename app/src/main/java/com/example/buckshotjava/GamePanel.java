package com.example.buckshotjava;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private final Paint redPaint = new Paint();
    private final Paint blackPaint = new Paint();

    public GamePanel(Context context) {
        super(context);
        getHolder().addCallback(this);
        redPaint.setColor(Color.RED);
        blackPaint.setColor((Color.BLACK));
        blackPaint.setTextSize(80);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        event.
        System.out.println("User event!");
        return true;
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        Canvas c = surfaceHolder.lockCanvas();

        c.drawRect(0, 0, c.getWidth(), (int)(c.getHeight()/2), redPaint);

        c.drawText("I was drinking earlier,", 100, 800, blackPaint);
        c.drawText("now I'm driving", 100, 900, blackPaint);

        surfaceHolder.unlockCanvasAndPost(c);
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }
}
