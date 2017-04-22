package com.example.android.strs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.example.android.strs.data_customer.CustomerDatabaseHelper;

public class CustomerLoginActivity extends AppCompatActivity
{
    CustomerDatabaseHelper helper = new CustomerDatabaseHelper(this);

    private EditText etUsername;
    private EditText etPassword;

    Boolean isValid = true;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
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

        bLogin.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                isValid= validateAccount();

                if(isValid)
                {
                    String username = etUsername.getText().toString();
                    String password = etPassword.getText().toString();

                    String password1 = helper.searchPassword(username);

                    if(password.equals(password1))
                    {
                        Intent registerIntent2 = new Intent(CustomerLoginActivity.this, CustomerAreaActivity.class);
                        CustomerLoginActivity.this.startActivity(registerIntent2);
                    }
                    else
                    {
                        Toast pass = Toast.makeText(CustomerLoginActivity.this, "Invalid Credentials!!!", Toast.LENGTH_SHORT);
                        pass.show();
                    }
                }
            }
        });
    }

    private boolean validateAccount()
    {
        isValid = true;

        if (etUsername.length() < 3) {
            etUsername.setError("Company Username should have atleast 3 characters");
            isValid = false;
            return isValid;
        } else {
            etUsername.setError(null);
        }

        if (etPassword.length() < 4 || etPassword.length() > 10) {
            etPassword.setError("Password should  have 4 to 10 alphanumeric characters");
            isValid = false;
            return isValid;
        } else {
            etPassword.setError(null);
        }

        return isValid;
    }
}
