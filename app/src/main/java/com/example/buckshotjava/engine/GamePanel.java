package com.example.buckshotjava.engine;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Random;

public final class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private final Paint redPaint = new Paint();
    private final Paint blackPaint = new Paint();
    private final GameLoop gameLoop;

    private final ArrayList<RndSquare> squares;
    private final Random rand;

    private final SurfaceHolder holder;

    public GamePanel(Context context) {
        super(context);

        holder = getHolder();
        holder.addCallback(this);
        redPaint.setColor(Color.RED);
        blackPaint.setColor((Color.BLACK));
        blackPaint.setTextSize(80);

        squares = new ArrayList<>();
        rand = new Random();

        gameLoop = new GameLoop(this);
        gameLoop.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            PointF pos = new PointF(event.getX(), event.getY());
            int color = Color.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
            int size = 25 + rand.nextInt(101);
            int speed = 200 + rand.nextInt(200);

            synchronized (squares) {
                squares.add(new RndSquare(pos, color, size, speed));
            }

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
        synchronized (squares) {
            for (RndSquare square : squares) {
                square.move(deltaTime);
            }
        }
    }

    public void render() {
        var c = holder.lockCanvas();
        if (c == null) return;
        c.drawColor(Color.BLACK);
        for (var square : squares) {
            square.drawSquare(c);
        }
        holder.unlockCanvasAndPost(c);
    }

    public class RndSquare {
        private PointF pos;
        private int size;
        private Paint paint;
        private int speed;
        private int xDir = 1, yDir = 1;
        public RndSquare(PointF pos, int color, int size, int speed) {
            this.pos = pos;
            this.size = size;
            this.speed = speed;
            paint = new Paint();
            paint.setColor(color);
        }

        public void move(double deltaTime) {
            pos.x += xDir * deltaTime * speed;
            pos.y += yDir * deltaTime * speed;

            if (pos.x >= holder.getSurfaceFrame().width() || pos.x <= 0) {
                xDir *= -1;
            }
            if (pos.y >= holder.getSurfaceFrame().height() || pos.y <= 0) {
                yDir *= -1;
            }
        }

        public Canvas drawSquare(Canvas c) {
            c.drawRect(pos.x, pos.y, pos.x + size, pos.y + size, paint);
            return c;
        }
    }
}
