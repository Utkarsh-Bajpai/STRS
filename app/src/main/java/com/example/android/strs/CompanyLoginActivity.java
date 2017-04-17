package com.example.android.strs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CompanyLoginActivity extends AppCompatActivity
{

    CompanyDatabaseHelper helper = new CompanyDatabaseHelper(this);

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

        bCLogin.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                String username = etCUsername.getText().toString();
                String password = etCPassword.getText().toString();

                String password1 = helper.searchPassword(username);

                if(password.equals(password1))
                {
                    Intent registerIntent2 = new Intent(CompanyLoginActivity.this, CompanyAreaActivity.class);
                    CompanyLoginActivity.this.startActivity(registerIntent2);
                }
                else
                {
                    Toast pass = Toast.makeText(CompanyLoginActivity.this, "Invalid Credentials!!!", Toast.LENGTH_SHORT);
                    pass.show();
                }
            }
        });

    }
}
