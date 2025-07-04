/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.espe.edu.wargame.model;

/**
 *
 * @author berna
 */
import java.util.Timer;
import java.util.TimerTask;

public class GameTimer {
    private Timer timer;
    private long duration; // Duration of the timer in milliseconds

    public GameTimer(long duration) {
        this.duration = duration;
        this.timer = new Timer();
    }

    // Start the timer
    public void startTimer() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Time has finished!");
            }
        };

        // Schedule the timer to execute after the duration
        timer.schedule(task, duration);
        System.out.println("Timer started. It will last " + duration / 1000 + " seconds.");
    }

    // Stop the timer
    public void stopTimer() {
        if (timer != null) {
            timer.cancel();
            System.out.println("Timer stopped.");
        }
    }
}

