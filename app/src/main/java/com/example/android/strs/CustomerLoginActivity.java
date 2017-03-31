package com.example.android.strs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class CustomerLoginActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);

        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button bLogin = (Button) findViewById(R.id.bLogin);
        final TextView tvRegisterHere = (TextView) findViewById(R.id.tvRegisterHere);

        tvRegisterHere.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                Intent registerIntent = new Intent(CustomerLoginActivity.this, CustomerRegisterActivity.class);
                CustomerLoginActivity.this.startActivity(registerIntent);
            }
        });
    }
}
