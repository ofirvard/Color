package com.example.color;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;

import java.util.Random;

public class SeizureRunnable implements Runnable {
    private View view;
    private boolean run = false;
    private Random rnd = new Random();
    private MediaPlayer mediaPlayer;

    public SeizureRunnable(Context context, View view) {
        this.view = view;
        mediaPlayer = MediaPlayer.create(context, R.raw.dubstep);
        mediaPlayer.setLooping(true);
    }

    @Override
    public void run() {
        try {
            while (true)
                if (this.run) {
                    Thread.sleep(2000);
                    int time = 500;
                    int count = 0;
                    mediaPlayer.start();

                    while (this.run) {
                        Thread.sleep(time);
                        randomColor();

                        time = Math.max(time - 23, 50);
                        Log.d("seizure_runnable", "c: " + count++ + ", t: " + time);
                    }

                    mediaPlayer.reset();
                }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        this.run = true;
    }

    public void stop() {
        this.run = false;
    }

    private void randomColor() {
        view.setBackgroundColor(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
    }
}
