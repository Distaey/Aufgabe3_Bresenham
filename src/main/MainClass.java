package main;

import gui.GUI;

/**
 * Created by Tristan-PC on 06.12.2015.
 */
public class MainClass {

    private static class DrawLoop implements Runnable {
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        GUI gui = new GUI();

        Thread t = new Thread(new DrawLoop());
        t.start();
    }
}