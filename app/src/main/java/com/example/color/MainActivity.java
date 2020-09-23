package com.example.color;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

public class MainActivity extends AppCompatActivity {

    static Random rnd = new Random();
    SeizureRunnable seizureRunnable;
    Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View view = findViewById(R.id.imageView);

        view.setBackgroundColor(randomColor());

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    view.setBackgroundColor(randomColor());

                    seizureRunnable.start();
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    seizureRunnable.stop();
                }

                return true;
            }
        });

        seizureRunnable = new SeizureRunnable(getApplicationContext(), view);
        thread = new Thread(seizureRunnable);
        thread.start();
    }

    public int randomColor() {
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }
}