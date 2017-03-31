package com.example.android.strs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class CustomerAreaActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_area);

        final TextView welcomemessage = (TextView) findViewById(R.id.tvWelcomeMsg);
    }
}
