package com.example.android.strs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class CustomerRegisterActivity extends AppCompatActivity {

        private EditText etAge;
        private EditText etName;
        private EditText etUsername;
        private EditText etPassword;
        private Button bRegister;
        private Spinner mGenderSpinner;

        private int mGender = 0;

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
}
