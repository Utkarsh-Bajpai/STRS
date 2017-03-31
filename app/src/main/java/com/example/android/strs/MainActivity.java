package com.example.android.strs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button bCustomerLogin = (Button) findViewById(R.id.bCustomerLogin);
        final Button bCompanyLogin = (Button) findViewById(R.id.bCompanyLogin);


        bCompanyLogin.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent CompanyIntent = new Intent(MainActivity.this, CustomerLoginActivity.class);
                MainActivity.this.startActivity(CompanyIntent);
            }
        });

        bCustomerLogin.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent CustomerIntent = new Intent(MainActivity.this, CompanyLoginActivity.class);
                MainActivity.this.startActivity(CustomerIntent);
            }
        });
    }
}
