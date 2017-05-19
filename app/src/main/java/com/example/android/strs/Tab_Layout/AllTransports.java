package com.example.android.strs.Tab_Layout;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.strs.Payment;
import com.example.android.strs.R;
import com.example.android.strs.data_booked_transport.BookedTransportContact;
import com.example.android.strs.data_booked_transport.BookedTransportdatabaseHelper;
import com.example.android.strs.data_transport.TransportContact;
import com.example.android.strs.data_transport.TransportDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

import static com.example.android.strs.CompanyAreaActivity.cusername;
import static com.example.android.strs.CustomerAreaActivity.username;

/**
 * Created by Utkarsh._.Bajpai on 29-Apr-17.
 */

public class AllTransports extends Fragment {

    TransportDatabaseHelper helper;
    BookedTransportdatabaseHelper helper2;
    BookedTransportContact t;
    List<TransportContact> dbList;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "CardViewActivity";

    ArrayList<String> name = new ArrayList<String>();
    ArrayList<String> source = new ArrayList<String>();
    ArrayList<String> destination = new ArrayList<String>();
    ArrayList<String> duration = new ArrayList<String>();
    ArrayList<String> Price = new ArrayList<String>();
    ArrayList<String> date = new ArrayList<String>();
    ArrayList<String> time = new ArrayList<String>();
    ArrayList<String> seats = new ArrayList<String>();
    ArrayList<String> company = new ArrayList<String>();

    String namea;
    String sourcea;
    String destinationa;
    String durationa;
    String Pricea;
    String datea;
    String timea;
    String seatsa;
    String companya;
    String customera;

    int i=0;

    public void maketext()
    {
        Cursor Cursor = helper.getAllRows();
        Cursor.moveToFirst();
        do
        {
            //if(Cursor != null || Cursor.moveToFirst())
            if(Cursor != null && Cursor.getCount()>0)
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

        }while(Cursor.moveToNext());
        Cursor.close();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.transports_available, container, false);

        name = new ArrayList<String>();
        source = new ArrayList<String>();
        destination = new ArrayList<String>();
        duration = new ArrayList<String>();
        Price = new ArrayList<String>();
        date = new ArrayList<String>();
        time = new ArrayList<String>();
        seats = new ArrayList<String>();
        company = new ArrayList<String>();

        customera = username;

        i=0;


        helper = new TransportDatabaseHelper(getActivity());
        helper2 = new BookedTransportdatabaseHelper(getActivity());
        dbList= new ArrayList<TransportContact>();
        dbList = helper.getDataFromDB();
        
        mRecyclerView = (RecyclerView) view.findViewById(R.id.transport_recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        maketext();
        mAdapter = new MyRecyclerAdapter(getDataSet());//MyRecyclerAdapter(getActivity(),helper.getDataFromDB());
        mRecyclerView.setAdapter(mAdapter);

        if (mAdapter.getItemCount() == 0) {

            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.transport_recyclerview);
            TextView text = (TextView) view.findViewById(R.id.empty_title_text);
            TextView text2 = (TextView) view.findViewById(R.id.empty_subtitle_text);
            ImageView imgView=(ImageView) view.findViewById(R.id.empty_shelter_image);
            //Drawable drawable  = getResources().(R.drawable.notransport);

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
        ((MyRecyclerAdapter) mAdapter).setOnItemClickListener(new MyRecyclerAdapter
                .MyClickListener() {

            @Override
            public void onItemClick(int position, View v) {
                Log.i(LOG_TAG, " Clicked on Item " + position);

                namea = name.get(position);
                sourcea = source.get(position);
                destinationa = destination.get(position);
                durationa = duration.get(position);
                datea = date.get(position);
                timea = time.get(position);
                Pricea = Price.get(position);
                seatsa = seats.get(position);
                companya = company.get(position);

                int value=Integer.parseInt(seatsa);

                if(value>0)
                {
                    t = new BookedTransportContact(namea,sourcea,destinationa,durationa,datea,timea,Pricea,seatsa,companya,customera);

                    t.setcusername(companya);
                    t.settname(namea);
                    t.setsource(sourcea);
                    t.setdestination(destinationa);
                    t.setjtime(durationa);
                    t.setcost(Pricea);
                    t.setdate(datea);
                    t.setdtime(timea);
                    t.setseats(seatsa);
                    t.setusername(customera);

                    helper2.insertContact(t);

                    helper.modify(companya,namea,seatsa);

                    Intent intent = new Intent();
                    intent.setClass(getActivity(), Payment.class);
                    getActivity().startActivity(intent);

                    //Toast.makeText(getActivity(),"\t\t\t\t\t\tCongratulations!!!\nYou have booked a seat on " + namea,Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(getActivity(),"All the seats on "+ namea + " are booked!!!\n\t\t\t\t\t\tTry another Transport",Toast.LENGTH_SHORT).show();
                }



            }
        });
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second, menu);
        return true;
    }*/

    private ArrayList<TransportContact> getDataSet() {
        ArrayList results = new ArrayList<TransportContact>();
        for (int index = 0; index < i; index++) {
            TransportContact obj = new TransportContact(name.get(index),
                    source.get(index),
                    destination.get(index),
                    duration.get(index),
                    date.get(index),
                    time.get(index),
                    Price.get(index),
                    seats.get(index),
                    company.get(index));


                    /*"1Name",
                    "2Source",
                    "3Destination",
                    "4Duration",
                    "6Date",
                    "7Time",
                    "5Price",
                    "8Seats",
                    "9Company");
                    /*,

                    /*helper.searchTName(username),helper.searchTSource(username),
                    helper.searchTDestination(username),helper.searchJTime(username),helper.searchCost(username),helper.searchDate(username)
                    ,helper.searchDTime(username),helper.searchSeats(username),cusername
                    /*"1Name",
                    "2Source",
                    "3Destination",
                    "4Duration",
                    "5Price",
                    "6Date",
                    "7Time",
                    "8Seats",
                    "9Company"*/
            results.add(index, obj);
        }
        return results;
    }
}
