package com.example.android.strs.Customer_Tab_Layout;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.strs.Payment;
import com.example.android.strs.R;

import com.example.android.strs.data_booked_transport.BookedTransportContact;
import com.example.android.strs.data_booked_transport.BookedTransportdatabaseHelper;
import com.example.android.strs.data_company.CompanyDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

import static com.example.android.strs.CustomerAreaActivity.username;

/**
 * A simple {@link Fragment} subclass.
 */

public class MyBookings extends Fragment {

    BookedTransportdatabaseHelper helper;
    CompanyDatabaseHelper helper2;
    List<BookedTransportContact> dbList;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "CardViewActivity2";

    ArrayList<String> name = new ArrayList<String>();
    ArrayList<String> source = new ArrayList<String>();
    ArrayList<String> destination = new ArrayList<String>();
    ArrayList<String> duration = new ArrayList<String>();
    ArrayList<String> Price = new ArrayList<String>();
    ArrayList<String> date = new ArrayList<String>();
    ArrayList<String> time = new ArrayList<String>();
    ArrayList<String> seats = new ArrayList<String>();
    ArrayList<String> company = new ArrayList<String>();

    int i=0;

    public void maketext()
    {
        Cursor Cursor = helper.getAllRows();
        Cursor.moveToFirst();
        do
        {
            if(Cursor != null && Cursor.getCount()>0)
            {

                Log.d("Curor ", "Value: " + Cursor.getString(9));
                Log.d("Username ", "Value2: " + username);
                String a=Cursor.getString(9);
                String b = username;
                if(a.equals(b))
                {
                    name.add(Cursor.getString(0));
                    source.add(Cursor.getString(1));
                    destination.add(Cursor.getString(2));
                    duration.add(Cursor.getString(3));
                    Price.add(Cursor.getString(4));
                    date.add(Cursor.getString(5));
                    time.add(Cursor.getString(6));
                    seats.add(Cursor.getString(7));
                    company.add(Cursor.getString(8));
                    i++;
                }
            }

        }while(Cursor.moveToNext());
        Cursor.close();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_my_bookings, container, false);

        name = new ArrayList<String>();
        source = new ArrayList<String>();
        destination = new ArrayList<String>();
        duration = new ArrayList<String>();
        Price = new ArrayList<String>();
        date = new ArrayList<String>();
        time = new ArrayList<String>();
        seats = new ArrayList<String>();
        company = new ArrayList<String>();

        i=0;


        helper = new BookedTransportdatabaseHelper(getActivity());
        helper2 = new CompanyDatabaseHelper(getActivity());
        dbList= new ArrayList<BookedTransportContact>();
        dbList = helper.getDataFromDB();

        mRecyclerView = (RecyclerView) view.findViewById(R.id.transport_recyclerview2);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        maketext();
        mAdapter = new MyBookingRecyclerAdapter(getContext(),getDataSet());
        mRecyclerView.setAdapter(mAdapter);

        if (mAdapter.getItemCount() == 0) {

            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.transport_recyclerview2);
            TextView text = (TextView) view.findViewById(R.id.empty_title_text);
            TextView text2 = (TextView) view.findViewById(R.id.empty_subtitle_text);
            ImageView imgView=(ImageView) view.findViewById(R.id.empty_shelter_image);

            text.setText("The Transport database is empty!!!");
            text2.setText("Come Back Again Later!!!");

            recyclerView.setVisibility(View.GONE);
            text.setVisibility(View.VISIBLE);
            text2.setVisibility(View.VISIBLE);
            imgView.setVisibility(View.VISIBLE);


        }
        return view;
    }

    public void onResume() {
        super.onResume();
        ((MyBookingRecyclerAdapter) mAdapter).setOnItemClickListener(new MyBookingRecyclerAdapter
                .MyClickListener() {

            @Override
            public void onItemClick(int position, View v) {
                Log.i(LOG_TAG, " Clicked on Item " + position);
                String email = helper2.searchEmail(company.get(position));
                String contact = helper2.searchContact(company.get(position));
                Toast.makeText(getActivity(),"\t\t\t\t\t\t\t\t\t\t\t\tBooked Ticket " + position + 1 +"\nFor any Enquiry or changes about your booking of " +
                        name.get(position)+"\nContact company "+company.get(position)+
                        "\nContact No. - "+contact+"\nEmail Id - "+email,Toast.LENGTH_LONG).show();

            }
        });
    }

    private ArrayList<BookedTransportContact> getDataSet() {
        ArrayList results = new ArrayList<BookedTransportdatabaseHelper>();
        for (int index = 0; index < i; index++) {
            BookedTransportContact obj = new BookedTransportContact(name.get(index),
                    source.get(index),
                    destination.get(index),
                    duration.get(index),
                    date.get(index),
                    time.get(index),
                    Price.get(index),
                    seats.get(index),
                    company.get(index),username);

            results.add(index, obj);
        }
        return results;
    }
}
