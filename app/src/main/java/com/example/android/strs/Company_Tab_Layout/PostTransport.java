package com.example.android.strs.Company_Tab_Layout;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.icu.util.TimeZone;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.android.strs.CompanyAreaActivity;
import com.example.android.strs.CustomerAreaActivity;
import com.example.android.strs.CustomerLoginActivity;
import com.example.android.strs.CustomerRegisterActivity;
import com.example.android.strs.R;
import com.example.android.strs.data_customer.CustomerContact;
import com.example.android.strs.data_customer.CustomerDatabaseHelper;
import com.example.android.strs.data_transport.TransportContact;
import com.example.android.strs.data_transport.TransportDatabaseHelper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.android.strs.CompanyAreaActivity.cusername;

/**
 * A simple {@link Fragment} subclass.
 */

public class PostTransport extends Fragment  implements TimePicker.OnTimeChangedListener{


    public PostTransport() {
        // Required empty public constructor
    }

    private EditText etName;
    private EditText etSource;
    private EditText etDestination;
    private EditText etJTime;
    private EditText etCost;
    private TextView etDate;
    private TextView etDTime;
    private EditText etSeats;
    private Button bRegister;
    private DatePicker datePicker;

    //private Button btn;
    int year_x, month_x, day_x,year,month,day;
    static final int DIALOG_ID = 0;

    boolean isValid = true;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final TransportDatabaseHelper helper = new TransportDatabaseHelper(getActivity());

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_post_transport, container, false);



            etName = (EditText) view.findViewById(R.id.etTName);
            etSource= (EditText) view.findViewById(R.id.etTSource);
            etDestination = (EditText) view.findViewById(R.id.etTDestination);
            etJTime = (EditText) view.findViewById(R.id.Time);
            etCost = (EditText) view.findViewById(R.id.Cost);
            etDTime = (TextView) view.findViewById(R.id.DTime);
            etDate = (TextView) view.findViewById(R.id.DDate);
            etSeats = (EditText) view.findViewById(R.id.Seats);
            bRegister = (Button) view.findViewById(R.id.bTRegister);
            datePicker = (DatePicker) view.findViewById( R.id.datepicker );

            final Calendar cal = Calendar.getInstance();
            year_x = cal.get(Calendar.YEAR);
            month_x = cal.get(Calendar.MONTH);
            day_x = cal.get(Calendar.DAY_OF_MONTH);
            year = year_x;
            month = month_x;
            day = day_x;

            //showDialogOnButtonClick();

            bRegister.setOnClickListener(new View.OnClickListener()
            {

                public void onClick(View v) {

                    setdate();

                    isValid = validateAccount();

                    if (isValid) {
                        String etNamestr = etName.getText().toString();
                        String etSourcestr = etSource.getText().toString();
                        String etDestinationstr = etDestination.getText().toString();
                        String etJTimestr = etJTime.getText().toString();
                        String etCoststr = etCost.getText().toString();

                        String etDDatestr = etDate.getText().toString();
                        String etDTimestr = etDTime.getText().toString();
                        String etSeatsstr = etSeats.getText().toString();

                        //Insert the details in database
                        TransportContact t = new TransportContact();

                        t.setcusername(cusername);
                        t.settname(etNamestr);
                        t.setsource(etSourcestr);
                        t.setdestination(etDestinationstr);
                        t.setjtime(etJTimestr);
                        t.setcost(etCoststr);
                        t.setdate(etDDatestr);
                        t.setdtime(etDTimestr);
                        t.setseats(etSeatsstr);

                        helper.insertContact(t);

                        Toast.makeText(getActivity(),"Transport Posted Successfully",Toast.LENGTH_SHORT).show();
                    }

                }

            });

        TimePicker timePicker = (TimePicker) view.findViewById(R.id.activity_main_timepicker);
        {
            timePicker.setIs24HourView(true);
            timePicker.setOnTimeChangedListener(this);
        }
            return view;
        }

        @Override
        public void onTimeChanged(TimePicker timePickerView, int hours, int minutes) {
            final String stringNewTime = hours + " : " + minutes;
            etDTime.setText(stringNewTime);
        }

        private void setdate()
        {
            if(datePicker.getDayOfMonth()<10)
            {
                if(datePicker.getMonth()<10)
                {
                    etDate.setText("0" + datePicker.getDayOfMonth() + "/" + "0" + datePicker.getMonth() + "/" + datePicker.getYear());
                }
                else
                {
                    etDate.setText("0" + datePicker.getDayOfMonth() + "/" + datePicker.getMonth() + "/" + datePicker.getYear());
                }
            }
            else
            {
                if(datePicker.getMonth()<10)
                {
                    etDate.setText(datePicker.getDayOfMonth() + "/" + "0" + datePicker.getMonth() + "/" + datePicker.getYear());
                }
                else
                {
                    etDate.setText(datePicker.getDayOfMonth() + "/" + datePicker.getMonth() + "/" + datePicker.getYear());
                }
            }
        }

    private boolean validateAccount()
    {
        isValid = true;

        if (etName.length() < 3) {
            etName.setError("Company Name should have atleast 3 characters");
            isValid = false;
            return isValid;
        } else {
            etName.setError(null);
        }

        if (etSource.length() < 3) {
            etSource.setError("Company Username should have atleast 3 characters");
            isValid = false;
            return isValid;
        } else {
            etSource.setError(null);
        }

        if (etDestination.length() < 3) {
            etDestination.setError("Company Username should have atleast 3 characters");
            isValid = false;
            return isValid;
        } else {
            etDestination.setError(null);
        }

        if (etJTime.length()<2)
        {
            etJTime.setError("Invalid Cost");
            isValid = false;
            return isValid;
        } else {
            etJTime.setError(null);
        }

        if (etCost.length()<2)
        {
            etCost.setError("Invalid Cost");
            isValid = false;
            return isValid;
        } else {
            etCost.setError(null);
        }

        String etDatestr = etDate.getText().toString();
        year_x = Integer.parseInt(etDatestr.substring(6));
        month_x = Integer.parseInt(etDatestr.substring(3,5));
        day_x = Integer.parseInt(etDatestr.substring(0,2));

        if(year_x < year)
        {

            etDate.setError("Invalid Year!!!\nYou can not book a date from the past!!!");
            Toast.makeText(getActivity(),"Invalid Year!!!\nYou can not book a date from the past!!!",Toast.LENGTH_LONG).show();
            isValid = false;
            return isValid;
        }
        else if(year_x == year_x && month_x < month)
        {

            etDate.setError("Invalid Month!!!\nYou can not book a date from the past!!!");
            Toast.makeText(getActivity(),"Invalid Month!!!\nYou can not book a date from the past!!!",Toast.LENGTH_LONG).show();
            isValid = false;
            return isValid;
        }
        else if(year_x == year_x && month_x == month && day_x<day)
        {

            etDate.setError("Invalid Day!!!\nYou can not book a date from the past!!!");
            Toast.makeText(getActivity(),"Invalid Day!!!\nYou can not book a date from the past!!!",Toast.LENGTH_LONG).show();
            isValid = false;
            return isValid;
        }
        else
        {
            etDate.setError(null);
        }

        return isValid;
    }

}
