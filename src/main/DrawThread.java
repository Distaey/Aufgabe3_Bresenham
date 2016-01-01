package main;

import draw.Draw;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by BSU on 01.01.2016.
 */
public class DrawThread implements Runnable {
    private Thread runThread;
    private boolean running = false;
    private boolean paused = false;

    private ArrayList<Point> drawn = new ArrayList<>();
    private ArrayList<Line> newLines = new ArrayList<>();
    private ArrayList<Line> newLineBuffer = new ArrayList<>();
    private Draw draw;
    JTextArea xTextArea;
    JTextArea yTextArea;

    public DrawThread(Draw draw, JTextArea xTextArea, JTextArea yTextArea) {
        this.draw = draw;
        this.xTextArea = xTextArea;
        this.yTextArea = yTextArea;
    }

    public void start() {
        running = true;
        paused = false;
        if(runThread == null || !runThread.isAlive())
            runThread = new Thread(this);
        else if(runThread.isAlive())
            throw new IllegalStateException("Thread already started.");
        runThread.start();
    }

    public void stop() {
        if(runThread == null)
            throw new IllegalStateException("Thread not started.");
        synchronized (runThread) {
            try {
                running = false;
                runThread.notify();
                runThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void pause() {
        if(runThread == null)
            throw new IllegalStateException("Thread not started.");
        synchronized (runThread) {
            paused = true;
        }
    }

    public void resume() {
        if(runThread == null)
            throw new IllegalStateException("Thread not started.");
        synchronized (runThread) {
            paused = false;
            runThread.notify();
        }
    }

    public void run() {
        long sleep = 0, before;
        while(running) {

            while (newLines.size() > 0){
                Line line = newLines.get(0);
                for (int i = 0; i < line.getCoords().size(); i++) {
                    Point point = line.getCoords().get(i);
                    drawn.add(point);
                    draw.setCoords(drawn);
                    draw.repaint();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                    }
                }
                newLines.remove(0);

                xTextArea.setText(null);
                yTextArea.setText(null);
                for(Line thisLine: newLines) {
                    xTextArea.append(thisLine.getxPoint());
                    yTextArea.append(thisLine.getyPoint());
                }
            }
            if(newLineBuffer.size() > 0) {
                newLines.addAll(newLineBuffer);
                newLineBuffer = new ArrayList<>();
            }

            synchronized (runThread) {
                if(paused) {
                    try {
                        runThread.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        paused = false;
    }

    public void addLines(ArrayList<Line> lines) {
        newLineBuffer.addAll(lines);
    }

    public boolean isRunning() {
        return running;
    }
}
