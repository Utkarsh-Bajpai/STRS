package com.example.android.strs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CompanyLoginActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_login);

        final EditText etCUsername = (EditText) findViewById(R.id.etCUsername);
        final EditText etCPassword = (EditText) findViewById(R.id.etCPassword);
        final Button bCLogin = (Button) findViewById(R.id.bCLogin);
        final TextView tvCRegisterHere = (TextView) findViewById(R.id.tvCRegisterHere);

        tvCRegisterHere.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                Intent registerIntent1 = new Intent(CompanyLoginActivity.this, CompanyRegisterActivity.class);
                CompanyLoginActivity.this.startActivity(registerIntent1);
            }
        });
    }
}
