package com.example.buckshotjava.engine;

public class GameLoop implements Runnable {

    private double prevTime = 0;
    private double deltaTime;
    private Thread gameLoopThread;
    private GamePanel gamePanel;
    private int fpsCounter;
    private boolean stop = false;

    public GameLoop(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        gameLoopThread = new Thread(this);
    }

    @Override
    public void run() {
        prevTime = System.currentTimeMillis();
        deltaTime = 1;

        long lastDelta = System.nanoTime();
        long nanoSec = 1_000_000_000;


        while (!stop) {

            long nowDelta = System.nanoTime();
            long timeSinceLastDelta = nowDelta - lastDelta;
            deltaTime = (double)timeSinceLastDelta / (double)nanoSec;

            gamePanel.update(deltaTime);
            gamePanel.render();

            lastDelta = nowDelta;
        }
    }

    public void start() {
        gameLoopThread.start();
    }

    public void stop() {
        stop = true;
    }

    public double getDeltaTime() {
        return deltaTime;
    }
}
