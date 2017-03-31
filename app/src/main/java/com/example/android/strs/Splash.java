package com.example.android.strs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;

public class Splash extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Thread timer = new Thread()
        {
            public void run()
            {
                try
                {
                    sleep(4000);
                } catch (InterruptedException e)
                {
                    Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();
                } finally
                {
                    Intent openMainActivity = new Intent(Splash.this, MainActivity.class);
                    Splash.this.startActivity(openMainActivity);
                }
            }
        };
        timer.start();
    }

        @Override
        protected void onPause()
        {
            super.onPause();
            finish();
        }
    }