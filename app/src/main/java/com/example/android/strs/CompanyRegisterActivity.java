package com.example.android.strs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.strs.data_company.CompanyContact;
import com.example.android.strs.data_company.CompanyDatabaseHelper;

public class CompanyRegisterActivity extends AppCompatActivity
{

    CompanyDatabaseHelper helper = new CompanyDatabaseHelper(this);

    private EditText CLocation;
    private EditText CName;
    private EditText CUsername;
    private EditText CPassword;
    private Button CRegister;
    private Spinner modeSpinner;

    private int mode = 0;

    boolean isValid = true;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_register);

        // Find all relevant views that we will need to read user input from
        CLocation = (EditText) findViewById(R.id.etCLocation);
        CName = (EditText) findViewById(R.id.etCName);
        CUsername = (EditText) findViewById(R.id.etCUsername);
        CPassword = (EditText) findViewById(R.id.etCPassword);
        CRegister = (Button) findViewById(R.id.bCRegister);
        modeSpinner = (Spinner) findViewById(R.id.spinner_Cmode);

        setupSpinner();

        CRegister.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                isValid= validateAccount();

                if(isValid)
                {
                    String CLocationstr = CLocation.getText().toString();
                    String CNamestr = CName.getText().toString();
                    String CUsernamestr = CUsername.getText().toString();
                    String CPasswordstr = CPassword.getText().toString();
                    String modeSpinnerstr = modeSpinner.getSelectedItem().toString();

                    //Insert the details in database
                    CompanyContact c = new CompanyContact();
                    c.setcusername(CUsernamestr);
                    c.setclocation(CLocationstr);
                    c.setcmode(modeSpinnerstr);
                    c.setcname(CNamestr);
                    c.setcpassword(CPasswordstr);

                    helper.insertContact(c);

                    Intent registerIntent = new Intent(CompanyRegisterActivity.this, CompanyLoginActivity.class);
                    CompanyRegisterActivity.this.startActivity(registerIntent);

                    Toast pass = Toast.makeText(CompanyRegisterActivity.this, "Registration Successfull!!!", Toast.LENGTH_SHORT);
                    pass.show();
                }

            }

        });

    }

    /**
     * Setup the dropdown spinner that allows the user to select the gender of the pet.
     */
    private void setupSpinner()
    {
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

    private boolean validateAccount()
    {
        isValid = true;

        if (CName.length() < 3) {
            CName.setError("Company Name should have atleast 3 characters");
            isValid = false;
            return isValid;
        } else {
            CName.setError(null);
        }

        if (CUsername.length() < 3) {
            CUsername.setError("Company Username should have atleast 3 characters");
            isValid = false;
            return isValid;
        } else {
            CUsername.setError(null);
        }

        if (CPassword.length() < 4 || CPassword.length() > 10) {
            CPassword.setError("Password should  have 4 to 10 alphanumeric characters");
            isValid = false;
            return isValid;
        } else {
            CPassword.setError(null);
        }

        if (CLocation.length() < 3) {
            CLocation.setError("Location should have atleast 3 characters");
            isValid = false;
            return isValid;
        } else {
            CLocation.setError(null);
        }

        return isValid;
    }
}
