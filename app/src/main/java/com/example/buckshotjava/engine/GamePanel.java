package com.example.buckshotjava.engine;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private final Paint redPaint = new Paint();
    private final Paint blackPaint = new Paint();
    private final GameLoop gameLoop;

    private SurfaceHolder holder;

    public GamePanel(Context context) {
        super(context);

        gameLoop = new GameLoop(this);
        gameLoop.start();

        holder = getHolder();
        holder.addCallback(this);
        redPaint.setColor(Color.RED);
        blackPaint.setColor((Color.BLACK));
        blackPaint.setTextSize(80);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (true) {
            var canvas = getHolder().lockCanvas();

            float x = event.getX();
            float y = event.getY();

            canvas.drawColor(blackPaint.getColor());
            canvas.drawRect(x, y, x + 25, y + 25, redPaint);

            getHolder().unlockCanvasAndPost(canvas);

            performClick();
        }

        return true;
    }

    @Override
    public boolean performClick() {

        return super.performClick();
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        //Canvas c = surfaceHolder.lockCanvas();

        //c.drawRect(0, 0, c.getWidth(), (int)(c.getHeight()/2), redPaint);

        //surfaceHolder.unlockCanvasAndPost(c);
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        gameLoop.stop();
    }

    public void update(double deltaTime) {
    }

    public void render() {
    }
}
