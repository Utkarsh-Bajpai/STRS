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

public class CompanyRegisterActivity extends AppCompatActivity {

        private EditText etCMode;
        private EditText etCName;
        private EditText etCUsername;
        private EditText etCPassword;
        private Button bCRegister;
        private Spinner modeSpinner;

    private int mode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_register);

        // Find all relevant views that we will need to read user input from
        etCMode = (EditText) findViewById(R.id.etCMode);
        etCName = (EditText) findViewById(R.id.etCName);
        etCUsername = (EditText) findViewById(R.id.etCUsername);
        etCPassword = (EditText) findViewById(R.id.etCPassword);
        bCRegister = (Button) findViewById(R.id.bCRegister);
        modeSpinner = (Spinner) findViewById(R.id.spinner_Cmode);

        setupSpinner();

    }

    /**
     * Setup the dropdown spinner that allows the user to select the gender of the pet.
     */
    private void setupSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_mode_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        modeSpinner.setAdapter(genderSpinnerAdapter);

        // Set the integer mSelected to the constant values
        modeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.mode_cab))) {
                        mode = 1;//Cab
                    } else if (selection.equals(getString(R.string.mode_bus))) {
                        mode = 2;//Bus
                    } else if (selection.equals(getString(R.string.mode_train))) {
                        mode = 3;//Train
                    } else if (selection.equals(getString(R.string.mode_airplane))) {
                        mode = 4;//Train
                    } else {
                        mode = 0;//Unknown
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mode = 0;//Unknown
            }
        });
    }
}
