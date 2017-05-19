package com.example.android.strs;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static com.example.android.strs.CustomerAreaActivity.username;

public class Payment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        final Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Toast pass = Toast.makeText(Payment.this,"\t\t\t\t\t\tCongratulations!!!\nYour Booking has been confirmed!!!", Toast.LENGTH_SHORT);
                pass.show();

                Intent CompanyIntent = new Intent(Payment.this, CustomerAreaActivity.class);
                CompanyIntent.putExtra("username",username);
                Payment.this.startActivity(CompanyIntent);

            }
        });
    }

}
