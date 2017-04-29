package com.example.android.strs.Tab_Layout;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.ListView;

import com.example.android.strs.R;
import com.example.android.strs.data_transport.TransportContact;
import com.example.android.strs.data_transport.TransportDatabaseHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllTransportsAvailable extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    private static final int Transport_LOADER = 0;

    TransportCursorAdapter mCursorAdapter;

    String[] name = new String[1000];
    String[] source = new String[1000];
    String[] destination = new String[1000];
    String[] duration = new String[1000];
    String[] Price = new String[1000];
    String[] date = new String[1000];
    String[] time = new String[1000];
    String[] seats = new String[1000];
    String[] company = new String[1000];


    public AllTransportsAvailable() {
        // Required empty public constructor
    }

    public void make_string()
    {


        TransportDatabaseHelper helper = new TransportDatabaseHelper(this.getContext());

        Cursor Cursor = helper.getAllRows();
        Cursor.moveToFirst();
        int i=0;
        do
        {
            name[i] = Cursor.getString(0);
            source[i] = Cursor.getString(1);
            destination[i] = Cursor.getString(2);
            duration[i] = Cursor.getString(3);
            Price[i] = Cursor.getString(4);
            date[i] = Cursor.getString(5);
            time[i] = Cursor.getString(6);
            seats[i] = Cursor.getString(7);
            company[i] = Cursor.getString(8);
            i++;

        }while(Cursor.moveToNext());

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_all_transports_available, container, false);

        //make_string();



        /*ListView transportListView = (ListView) view.findViewById(R.id.all_transports);
        View emptyView = view.findViewById(R.id.empty_view);
        transportListView.setEmptyView(emptyView);

        //set up adapter
        mCursorAdapter = new TransportCursorAdapter(getActivity(), null);
        transportListView.setAdapter(mCursorAdapter);

        //kick off loader
        getActivity().getSupportLoaderManager().initLoader(Transport_LOADER, null, this);*/


        //TransportDatabaseHelper helper = new TransportDatabaseHelper(this.getContext());
        //TransportDatabaseHelper helper = new TransportDatabaseHelper(getActivity());
        //SQLiteDatabase database = helper.getReadableDatabase();
        //Cursor cursor = database.rawQuery("SELECT * FROM " + TransportDatabaseHelper.TABLE_NAME, null);
        //cursor.moveToFirst();

        /*ListView transportListView = (ListView) view.findViewById(R.id.all_transports);
        View emptyView = view.findViewById(R.id.empty_view);
        transportListView.setEmptyView(emptyView);*/



        //TransportCursorAdapter adapter = new TransportCursorAdapter(getActivity(), null);
        //transportListView.setAdapter(adapter);

        /*String[] name = new String[1000];
        String[] source = new String[1000];
        String[] destination = new String[1000];
        String[] duration = new String[1000];
        String[] Price = new String[1000];
        String[] date = new String[1000];
        String[] time = new String[1000];
        String[] seats = new String[1000];
        String[] company = new String[1000];

        TransportDatabaseHelper helper = new TransportDatabaseHelper(this.getContext());
        Cursor Cursor = helper.getAllRows();
        Cursor.moveToFirst();
        int i=0;
        do
        {
            name[i] = Cursor.getString(0);
            source[i] = Cursor.getString(1);
            destination[i] = Cursor.getString(2);
            duration[i] = Cursor.getString(3);
            Price[i] = Cursor.getString(4);
            date[i] = Cursor.getString(5);
            time[i] = Cursor.getString(6);
            seats[i] = Cursor.getString(7);
            company[i] = Cursor.getString(8);
            i++;

        }while(Cursor.moveToNext());*/
        /*String name = new String();
        String source = new String();
        String destination = new String();
        String duration = new String();
        String Price = new String();
        String date = new String();
        String time = new String();
        String seats = new String();
        String company = new String();

        CustomAdapter utkarshsdapter = new CustomAdapter(this,android.R.display_transport ,name,source,destination,duration,Price,date,time,seats,company));
        ListView utkarshslistview = (ListView) view.findViewById(R.id.all_transports);
        utkarshslistview.setAdapter(utkarshsdapter);*/







        return view;


    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        String[] projection = {
                TransportDatabaseHelper.COLUMN_CUSERNAME,
                TransportDatabaseHelper.COLUMN_TNAME,
                TransportDatabaseHelper.COLUMN_TSOURCE,
                TransportDatabaseHelper.COLUMN_TDESTINATION,
                TransportDatabaseHelper.COLUMN_JTIME,
                TransportDatabaseHelper.COLUMN_COST,
                TransportDatabaseHelper.COLUMN_Date,
                TransportDatabaseHelper.COLUMN_DTIME,
                TransportDatabaseHelper.COLUMN_SEATS
        };

        return new CursorLoader(getActivity(), TransportContact.CONTENT_URI ,projection,null,null,null);

        //return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);
    }
}
