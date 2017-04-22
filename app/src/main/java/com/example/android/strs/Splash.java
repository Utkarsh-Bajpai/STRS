package com.example.android.strs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
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

        final TypeWriter tw = (TypeWriter) findViewById(R.id.textView3);

        Thread timer = new Thread()
        {
            public void run()
            {
                try
                {
                    tw.setCharacterDelay(120);
                    tw.animateText("\tSmart Transport\nReservation System");
                    sleep(6000);

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