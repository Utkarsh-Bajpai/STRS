package com.example.android.strs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.strs.data_company.CompanyDatabaseHelper;

public class CompanyLoginActivity extends AppCompatActivity
{

    CompanyDatabaseHelper helper = new CompanyDatabaseHelper(this);

    Boolean isValid = true;

    private EditText etCUsername;
    private EditText etCPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_login);

        etCUsername = (EditText) findViewById(R.id.etCUsername);
        etCPassword = (EditText) findViewById(R.id.etCPassword);
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
                isValid= validateAccount();

                if(isValid)
                {
                    String cusername = etCUsername.getText().toString();
                    String password = etCPassword.getText().toString();

                    String password1 = helper.searchPassword(cusername);

                    if(password.equals(password1))
                    {
                        Intent registerIntent2 = new Intent(CompanyLoginActivity.this, CompanyAreaActivity.class);
                        registerIntent2.putExtra("cusername",cusername);
                        CompanyLoginActivity.this.startActivity(registerIntent2);
                    }
                    else
                    {
                        Toast pass = Toast.makeText(CompanyLoginActivity.this, "Invalid Credentials!!!", Toast.LENGTH_SHORT);
                        pass.show();
                    }
                }
            }
        });

    }

    private boolean validateAccount()
    {
        isValid = true;

        if (etCUsername.length() < 3) {
            etCUsername.setError("Username should have atleast 3 characters");
            isValid = false;
            return isValid;
        } else {
            etCUsername.setError(null);
        }

        if (etCPassword.length() < 4 || etCPassword.length() > 10) {
            etCPassword.setError("Password should  have 4 to 10 alphanumeric characters");
            isValid = false;
            return isValid;
        } else {
            etCPassword.setError(null);
        }

        return isValid;
    }
}
