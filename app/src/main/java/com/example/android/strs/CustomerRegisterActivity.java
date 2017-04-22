package com.example.android.strs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.strs.data_customer.CustomerContact;
import com.example.android.strs.data_customer.CustomerDatabaseHelper;

public class CustomerRegisterActivity extends AppCompatActivity {

        CustomerDatabaseHelper helper = new CustomerDatabaseHelper(this);

        private EditText etAge;
        private EditText etName;
        private EditText etUsername;
        private EditText etPassword;
        private Button bRegister;
        private Spinner mGenderSpinner;

        private int mGender = 0;

        boolean isValid = true;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_customer_register);

            // Find all relevant views that we will need to read user input from
            etAge = (EditText) findViewById(R.id.etAge);
            etName = (EditText) findViewById(R.id.etName);
            etUsername = (EditText) findViewById(R.id.etUsername);
            etPassword = (EditText) findViewById(R.id.etPassword);
            bRegister = (Button) findViewById(R.id.bRegister);
            mGenderSpinner = (Spinner) findViewById(R.id.spinner_gender);

            setupSpinner();

            bRegister.setOnClickListener(new View.OnClickListener()
            {

                public void onClick(View v)
                {
                    isValid = validateAccount();

                    if(isValid)
                    {
                        String etAgestr = etAge.getText().toString();
                        Integer etAgeint = Integer.parseInt(etAgestr);
                        String etNamestr = etName.getText().toString();
                        String etUsernamestr = etUsername.getText().toString();
                        String etPasswordstr = etPassword.getText().toString();
                        String GenderSpinnerstr = mGenderSpinner.getSelectedItem().toString();

                        //Insert the details in database
                        CustomerContact c = new CustomerContact();
                        c.setetusername(etUsernamestr);
                        c.setetname(etNamestr);
                        c.setetpassword(etPasswordstr);
                        c.setetAge(etAgeint);
                        c.setgender(GenderSpinnerstr);

                        helper.insertContact(c);

                        Intent registerIntent = new Intent(CustomerRegisterActivity.this, CustomerLoginActivity.class);
                        CustomerRegisterActivity.this.startActivity(registerIntent);

                        Toast pass = Toast.makeText(CustomerRegisterActivity.this, "Registration Successfull!!!", Toast.LENGTH_SHORT);
                        pass.show();
                    }

                }

            });

        }

        /**
         * Setup the dropdown spinner that allows the user to select the gender of the pet.
         */
        private void setupSpinner() {
            // Create adapter for spinner. The list options are from the String array it will use
            // the spinner will use the default layout
            ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
                    R.array.array_gender_options, android.R.layout.simple_spinner_item);

            // Specify dropdown layout style - simple list view with 1 item per line
            genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // Apply the adapter to the spinner
            mGenderSpinner.setAdapter(genderSpinnerAdapter);

            // Set the integer mSelected to the constant values
            mGenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String selection = (String) parent.getItemAtPosition(position);
                    if (!TextUtils.isEmpty(selection)) {
                        if (selection.equals(getString(R.string.gender_male))) {
                            mGender = 1;//Male
                        } else if (selection.equals(getString(R.string.gender_female))) {
                            mGender = 2;//Female
                        } else {
                            mGender = 0;//Unknown
                        }
                    }
                }

                // Because AdapterView is an abstract class, onNothingSelected must be defined
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    mGender = 0;
                }
            });
        }

    private boolean validateAccount()
    {
        isValid = true;

        if (etName.length() < 3) {
            etName.setError("Customer Name should have atleast 3 characters");
            isValid = false;
            return isValid;
        } else {
            etName.setError(null);
        }

        if (etUsername.length() < 3) {
            etUsername.setError("Customer Username should have atleast 3 characters");
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

        if (etAge.length() != 2) {
            etAge.setError("Age should be between 10 to 99 years");
            isValid = false;
            return isValid;
        } else {
            etAge.setError(null);
        }

        return isValid;
    }

}
