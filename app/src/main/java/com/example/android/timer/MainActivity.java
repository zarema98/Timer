package com.example.android.timer;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextView txtTime;
    private Button start, stop, restart;

    private int seconds = 0;
    private boolean isRun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtTime = findViewById(R.id.txt_time);
        start = findViewById(R.id.btn_start);
        stop = findViewById(R.id.btn_stop);
        restart = findViewById(R.id.btn_restart);

        if(savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            isRun = savedInstanceState.getBoolean("isRun");
        }

        start();
        stop();
        restart();

        runTimer();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                Intent intent = new Intent(getBaseContext(), Main2Activity.class);
                startActivity(intent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("seconds", seconds);
        outState.putBoolean("isRun", isRun);
    }

    private void runTimer() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int sec = seconds % 60;

                String curTime = String.format(Locale.getDefault(), "%d: %02d: %02d", hours, minutes, sec);
                txtTime.setText(curTime);
                if(isRun) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }


   private void start() {
       start.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               isRun = true;
           }
       });
    }

    private void stop () {
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isRun = false;
            }
        });
    }

    private void restart() {
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isRun = false;
                seconds = 0;
            }
        });
    }


}
