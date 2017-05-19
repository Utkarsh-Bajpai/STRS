package com.example.android.strs.data_booked_transport;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Utkarsh._.Bajpai on 01-May-17.
 */

public class BookedTransportdatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "BookingTransportRegistration.db";
    public static final String TABLE_NAME = "BookingTransportRegistration";
    public static final String COLUMN_USERNAME = "Username";
    public static final String COLUMN_CUSERNAME = "CUsername";
    public static final String COLUMN_TNAME = "TName";
    public static final String COLUMN_TSOURCE = "TSource";
    public static final String COLUMN_TDESTINATION = "TDestination";
    public static final String COLUMN_JTIME = "JTime";
    public static final String COLUMN_COST = "Cost";
    public static final String COLUMN_Date = "DDate";
    public static final String COLUMN_DTIME = "DTime";
    public static final String COLUMN_SEATS = "Seats";

    SQLiteDatabase db;

    private static final String TABLE_CREATE = "create table BookingTransportRegistration (TName text not null,TSource text not null," +
            "TDestination text not null,JTime text not null,Cost text not null,DDate text not null,DTime text not null,Seats text not null," +
            "CUsername text not null,Username text not null)";


    public BookedTransportdatabaseHelper(Context context)
    {
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db=db;
    }

    public void insertContact(BookedTransportContact t)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values= new ContentValues();

        values.put(COLUMN_CUSERNAME,t.getcusername());
        values.put(COLUMN_TNAME,t.gettname());
        values.put(COLUMN_TSOURCE, t.getsource());
        values.put(COLUMN_TDESTINATION ,t.getdestination());
        values.put(COLUMN_JTIME,t.getjtime());
        values.put(COLUMN_COST ,t.getcost());
        values.put(COLUMN_Date, t.getdate());
        values.put(COLUMN_DTIME, t.getdtime());
        values.put(COLUMN_SEATS, t.getseats());
        values.put(COLUMN_USERNAME,t.getusername());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Cursor getAllRows()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS" + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }

    public List<BookedTransportContact> getDataFromDB(){

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from "+ TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);

        List<BookedTransportContact> modelList = new ArrayList<BookedTransportContact>();

        if (cursor.moveToFirst()){
            do {
                BookedTransportContact model = new BookedTransportContact(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8),cursor.getString(9));

                model.settname(cursor.getString(0));
                model.setsource(cursor.getString(1));
                model.setdestination(cursor.getString(2));
                model.setjtime(cursor.getString(3));
                model.setcost(cursor.getString(4));
                model.setdate(cursor.getString(5));
                model.setdtime(cursor.getString(6));
                model.setseats(cursor.getString(7));
                model.setcusername(cursor.getString(8));
                model.setusername(cursor.getString(9));

                modelList.add(model);
            }while (cursor.moveToNext());
        }
        Log.d("Transport Data", modelList.toString());


        return modelList;
    }


    public String searchTName(String uname)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select CUsername, TName from "+ TABLE_NAME;
        Cursor Cursor = db.rawQuery(query, null);

        String username, tname;
        tname = "Not Found!";

        if(Cursor.moveToFirst())
        {
            do{
                username = Cursor.getString(0);

                if(username.equals(uname))
                {
                    tname = Cursor.getString(1);
                    break;
                }
            }while(Cursor.moveToNext());
        }
        db.close();
        Cursor.close();
        return(tname);
    }

    public String searchTSource(String uname)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select CUsername, TSource from "+ TABLE_NAME;
        Cursor Cursor = db.rawQuery(query, null);

        String username, TSource;
        TSource = "Not Found!";

        if(Cursor.moveToFirst())
        {
            do{
                username = Cursor.getString(0);

                if(username.equals(uname))
                {
                    TSource = Cursor.getString(1);
                    break;
                }
            }while(Cursor.moveToNext());
        }
        db.close();
        Cursor.close();
        return(TSource);
    }

    public String searchTDestination(String uname)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select CUsername, TDestination from "+ TABLE_NAME;
        Cursor Cursor = db.rawQuery(query, null);

        String username, TDestination;
        TDestination = "Not Found!";

        if(Cursor.moveToFirst())
        {
            do{
                username = Cursor.getString(0);

                if(username.equals(uname))
                {
                    TDestination = Cursor.getString(1);
                    break;
                }
            }while(Cursor.moveToNext());
        }
        db.close();
        Cursor.close();
        return(TDestination);
    }

    public String searchJTime(String uname)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select CUsername, JTime from "+ TABLE_NAME;
        Cursor Cursor = db.rawQuery(query, null);

        String username, JTime;
        JTime = "Not Found!";

        if(Cursor.moveToFirst())
        {
            do{
                username = Cursor.getString(0);

                if(username.equals(uname))
                {
                    JTime = Cursor.getString(1);
                    break;
                }
            }while(Cursor.moveToNext());
        }
        db.close();
        Cursor.close();
        return(JTime);
    }

    public String searchCost(String uname)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select CUsername, Cost from "+ TABLE_NAME;
        Cursor Cursor = db.rawQuery(query, null);

        String username, Cost;
        Cost = "\"Not Found!\"";

        if(Cursor.moveToFirst())
        {
            do{
                username = Cursor.getString(0);

                if(username.equals(uname))
                {
                    Cost = Cursor.getString(1);
                    break;
                }
            }while(Cursor.moveToNext());
        }
        db.close();
        Cursor.close();
        return(Cost);
    }

    public String searchDate(String uname)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select CUsername, DDate from "+ TABLE_NAME;
        Cursor Cursor = db.rawQuery(query, null);

        String username, Date;
        Date = "Not Found!";

        if(Cursor.moveToFirst())
        {
            do{
                username = Cursor.getString(0);

                if(username.equals(uname))
                {
                    Date = Cursor.getString(1);
                    break;
                }
            }while(Cursor.moveToNext());
        }
        db.close();
        Cursor.close();
        return(Date);
    }

    public String searchDTime(String uname)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select CUsername, DTime from "+ TABLE_NAME;
        Cursor Cursor = db.rawQuery(query, null);

        String username, DTime;
        DTime = "Not Found!";

        if(Cursor.moveToFirst())
        {
            do{
                username = Cursor.getString(0);

                if(username.equals(uname))
                {
                    DTime = Cursor.getString(1);
                    break;
                }
            }while(Cursor.moveToNext());
        }
        db.close();
        Cursor.close();
        return(DTime);
    }

    public String searchSeats(String uname)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select CUsername, Seats from "+ TABLE_NAME;
        Cursor Cursor = db.rawQuery(query, null);

        String username,Seats;
        Seats = "Not Found!";

        if(Cursor.moveToFirst())
        {
            do{
                username = Cursor.getString(0);

                if(username.equals(uname))
                {
                    Seats = Cursor.getString(1);
                    break;
                }
            }while(Cursor.moveToNext());
        }
        db.close();
        Cursor.close();
        return(Seats);
    }


}

