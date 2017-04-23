package com.example.android.strs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.media.MediaCodec;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.strs.data_customer.CustomerContact;
import com.example.android.strs.data_customer.CustomerDatabaseHelper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerRegisterActivity extends AppCompatActivity
{

        CustomerDatabaseHelper helper = new CustomerDatabaseHelper(this);

        private EditText etName;
        private EditText etUsername;
        private EditText etPassword;
        private EditText etDate;
        private EditText etEmail;
        private EditText etContact;
        private Spinner mGenderSpinner;
        private Button bRegister;

        private Button btn;
        int year_x, month_x,day_x;
        static final int DIALOG_ID = 0;

        private int mGender = 0;

        boolean isValid = true;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_customer_register);

            // Find all relevant views that we will need to read user input from
            etName = (EditText) findViewById(R.id.etName);
            etUsername = (EditText) findViewById(R.id.etUsername);
            etPassword = (EditText) findViewById(R.id.etPassword);
            etDate = (EditText) findViewById(R.id.etDate);
            etContact = (EditText) findViewById(R.id.etContact);
            etEmail = (EditText) findViewById(R.id.etEmail);
            mGenderSpinner = (Spinner) findViewById(R.id.spinner_gender);
            bRegister = (Button) findViewById(R.id.bRegister);

            setupSpinner();

            final Calendar cal = Calendar.getInstance();
            year_x = cal.get(Calendar.YEAR);
            month_x = cal.get(Calendar.MONTH);
            day_x = cal.get(Calendar.DAY_OF_MONTH);


            showDialogOnButtonClick();

            bRegister.setOnClickListener(new View.OnClickListener()
            {

                public void onClick(View v)
                {

                    isValid = validateAccount();

                    if(isValid)
                    {
                        String etNamestr = etName.getText().toString();
                        String etUsernamestr = etUsername.getText().toString();
                        String etPasswordstr = etPassword.getText().toString();
                        String etDatestr = etDate.getText().toString();
                        String etEmailstr = etEmail.getText().toString();
                        String etContactstr = etContact.getText().toString();
                        String GenderSpinnerstr = mGenderSpinner.getSelectedItem().toString();

                        //Insert the details in database
                        CustomerContact c = new CustomerContact();
                        c.setetusername(etUsernamestr);
                        c.setetname(etNamestr);
                        c.setetpassword(etPasswordstr);
                        c.setetDate(etDatestr);
                        c.setgender(GenderSpinnerstr);
                        c.setetEmail(etEmailstr);
                        c.setetContact(etContactstr);

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

        private void showDialogOnButtonClick()
        {
            btn = (Button)findViewById(R.id.dob);
            btn.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showDialog(DIALOG_ID);
                        }
                    }
            );
        }

        @Override
        protected Dialog onCreateDialog(int id){
            if(id== DIALOG_ID)
            {
                return new DatePickerDialog(CustomerRegisterActivity.this, dpickerListener, year_x, month_x, day_x);
            }
            return null;
        }

        private DatePickerDialog.OnDateSetListener dpickerListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                year_x = year;
                month_x = month + 1;
                day_x = dayOfMonth;

                Toast.makeText(CustomerRegisterActivity.this,day_x + "/" + month_x + "/" + year_x, Toast.LENGTH_LONG).show();

                if(day_x<10)
                {
                    if(month_x<10)
                    {
                        etDate.setText( "0"+day_x + "/" + "0"+month_x + "/" + year_x, TextView.BufferType.EDITABLE);
                    }
                    else
                    {
                        etDate.setText( "0"+day_x + "/" + month_x + "/" + year_x, TextView.BufferType.EDITABLE);
                    }
                }
                else
                {
                    if(month_x<10)
                    {
                        etDate.setText( day_x + "/" + "0"+month_x + "/" + year_x, TextView.BufferType.EDITABLE);
                    }
                    else
                    {
                        etDate.setText( day_x + "/" + month_x + "/" + year_x, TextView.BufferType.EDITABLE);
                    }
                }


            }
        };

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

        String etDatestr = etDate.getText().toString();
        year_x = Integer.parseInt(etDatestr.substring(6));
        month_x = Integer.parseInt(etDatestr.substring(3,5));
        day_x = Integer.parseInt(etDatestr.substring(0,2));

        int a= validDate(etDatestr);
        if (a!=1)
        {
            etDate.setError("Invalid Date of Birth\nDOB Format-[DD/MM/YYYY]");
            isValid = false;
            return isValid;
        }
        else if(year_x>2008 || year_x<1917)
        {

            etDate.setError("Your age should be between 10 to 100 years to register");
            isValid = false;
            return isValid;
        }
        else
        {
            etDate.setError(null);
        }

        String etEmailstr = etEmail.getText().toString();
        if (etEmail.length() < 2 || !Patterns.EMAIL_ADDRESS.matcher(etEmailstr).matches()) {
            etEmail.setError("Invalid Email Address");
            isValid = false;
            return isValid;
        } else {
            etEmail.setError(null);
        }

        if (etContact.length() < 10 || etContact.length() > 11 ) {
            etContact.setError("Invalid Telephone/Mobile No.");
            isValid = false;
            return isValid;
        } else {
            etContact.setError(null);
        }

        String username = etUsername.getText().toString();
        String password = helper.searchPassword(username);
        if(password!="Not Found!")
        {
            etUsername.setError("Customer Username Already Exists!!!\nEnter a new Username");
            isValid = false;
            return isValid;
        }

        return isValid;
    }

    private int validDate(String date)
    {
        String dob = "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$";
        Matcher matcherobj = Pattern.compile(dob).matcher(date);
        if(matcherobj.matches())
        {
            return  1;
        }
        else
        {
            return 0;
        }
    }

}
