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
        while (!stop) {

            deltaTime = (System.currentTimeMillis() - prevTime);
            prevTime = System.currentTimeMillis();

            gamePanel.update(deltaTime);
            gamePanel.render();

            System.out.println(deltaTime);
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
