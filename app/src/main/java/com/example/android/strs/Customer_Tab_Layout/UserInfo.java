package com.example.android.strs.Customer_Tab_Layout;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.strs.CustomerLoginActivity;
import com.example.android.strs.CustomerRegisterActivity;
import com.example.android.strs.R;
import com.example.android.strs.data_customer.CustomerContact;
import com.example.android.strs.data_customer.CustomerDatabaseHelper;

import static com.example.android.strs.CustomerAreaActivity.username;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserInfo extends Fragment {

//    Context context;
//
//    @Override
//    public void onActivityCreated(Bundle savedInstanceState)
//    {
//        super.onActivityCreated(savedInstanceState);
//        context = getActivity();
//    }



    private TextView etName;
    private TextView etDate;
    private TextView etEmail;
    private TextView etContact;
    private TextView etgender;
    private TextView etUsername;
    private ImageView photo;


    public UserInfo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_info, container, false);

        CustomerDatabaseHelper helper = new CustomerDatabaseHelper(getActivity());

        etName = (TextView) view.findViewById(R.id.etName);
        etUsername = (TextView) view.findViewById(R.id.etUsername);
        etDate = (TextView) view.findViewById(R.id.etDate);
        etEmail = (TextView) view.findViewById(R.id.etEmail);
        etContact = (TextView) view.findViewById(R.id.etContact);
        etgender = (TextView) view.findViewById(R.id.textView);
        photo = (ImageView) view.findViewById(R.id.photo);

        //setDetails();
        etUsername.setText("Username: " + username,TextView.BufferType.EDITABLE);

        /*String name = "Utkarsh Bajpai";
        String dob = "27/08/1997";
        String email = "utkarsh.bajpai2015@vit.ac.in";
        String contact = "9873751601";
        String gender = "Male";*/

        String name = helper.searchName(username);
        etName.setText(name,TextView.BufferType.EDITABLE);

        String dob = helper.searchDob(username);
        etDate.setText("Date of Birth: " + dob,TextView.BufferType.EDITABLE);

        String email = helper.searchEmail(username);
        etEmail.setText("Email: " + email,TextView.BufferType.EDITABLE);

        String contact = helper.searchContact(username);
        etContact.setText("Phone No: " + contact,TextView.BufferType.EDITABLE);

        String gender = helper.searchGender(username);
        etgender.setText("Gender: " + gender,TextView.BufferType.EDITABLE);

        if(gender.equals(getString(R.string.gender_male)))
        {
            photo.setImageResource(R.drawable.malegender);
        }
        else if(gender.equals(getString(R.string.gender_female)))
        {
            photo.setImageResource(R.drawable.femalegender);
        }
        else
        {
            photo.setImageResource(R.drawable.unknowngender);
        }

        return view;
    }

}
