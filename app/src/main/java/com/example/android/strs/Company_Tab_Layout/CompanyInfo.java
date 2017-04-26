package com.example.android.strs.Company_Tab_Layout;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.strs.R;
import com.example.android.strs.data_company.CompanyDatabaseHelper;
import com.example.android.strs.data_customer.CustomerDatabaseHelper;

import static com.example.android.strs.CompanyAreaActivity.cusername;
import static com.example.android.strs.CustomerAreaActivity.username;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompanyInfo extends Fragment {



    private TextView etCName;
    private TextView etCLocation;
    private TextView etCEmail;
    private TextView etCContact;
    private TextView etCMode;
    private TextView etCUsername;
    private ImageView photo;


    public CompanyInfo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_company_info, container, false);

        CompanyDatabaseHelper helper = new CompanyDatabaseHelper(getActivity());

        etCName = (TextView) view.findViewById(R.id.etCName);
        etCUsername = (TextView) view.findViewById(R.id.etCUsername);
        etCLocation = (TextView) view.findViewById(R.id.etCLocation);
        etCEmail = (TextView) view.findViewById(R.id.etCEmail);
        etCContact = (TextView) view.findViewById(R.id.etCContact);
        etCMode = (TextView) view.findViewById(R.id.textView);
        photo = (ImageView) view.findViewById(R.id.photo);

        //setDetails();
        etCUsername.setText("Username: " + cusername ,TextView.BufferType.EDITABLE);

        /*String name = "Utkarsh Travels";
        String location = "New Delhi";
        String email = "utkarsh.bajpai2015@vit.ac.in";
        String contact = "9873751601";
        String mode = "Cab/Taxi";*/

        String name = helper.searchName(cusername);
        etCName.setText(name,TextView.BufferType.EDITABLE);

        String location = helper.searchLocation(cusername);
        etCLocation.setText("Location: " + location,TextView.BufferType.EDITABLE);

        String email = helper.searchEmail(cusername);
        etCEmail.setText("Email: " + email,TextView.BufferType.EDITABLE);

        String contact = helper.searchContact(cusername);
        etCContact.setText("Phone No: " + contact,TextView.BufferType.EDITABLE);

        String mode = helper.searchMode(cusername);
        etCMode.setText("Mode: " + mode,TextView.BufferType.EDITABLE);

        if(mode.equals(getString(R.string.mode_cab)))
        {
            photo.setImageResource(R.drawable.taxi);
        }
        else if(mode.equals(getString(R.string.mode_bus)))
        {
            photo.setImageResource(R.drawable.bus);
        }
        else if(mode.equals(getString(R.string.mode_train)))
        {
            photo.setImageResource(R.drawable.train);
        }
        else if(mode.equals(getString(R.string.mode_airplane)))
        {
            photo.setImageResource(R.drawable.plane);
        }
        else
        {
            photo.setImageResource(R.drawable.strslogo);
        }

        return view;
    }

}
