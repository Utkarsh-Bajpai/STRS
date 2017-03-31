package com.example.android.strs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class CompanyRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_register);

        final EditText etCMode = (EditText) findViewById(R.id.etCMode);
        final EditText etCName = (EditText) findViewById(R.id.etCName);
        final EditText etCUsername = (EditText) findViewById(R.id.etCUsername);
        final EditText etCPassword = (EditText) findViewById(R.id.etCPassword);
        final Button bCRegister = (Button) findViewById(R.id.bCRegister);

    }
}
