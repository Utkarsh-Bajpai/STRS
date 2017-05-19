package com.example.android.strs.data_company;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.strs.data_company.CompanyContact;

/**
 * Created by Utkarsh._.Bajpai on 16-Apr-17.
 */

public class CompanyDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "CompanyRegistration.db";
    private static final String TABLE_NAME = "CompanyRegistration";
    private static final String COLUMN_CNAME = "cname";
    private static final String COLUMN_CUSERNAME = "cusername";
    private static final String COLUMN_CPASSWORD = "cpassword";
    private static final String COLUMN_CLOCATION = "clocation";
    private static final String COLUMN_CEMAIL = "cemail";
    private static final String COLUMN_CCONTACT = "ccontact";
    private static final String COLUMN_CMODE = "cmode";

    SQLiteDatabase db;

    private static final String TABLE_CREATE = "create table CompanyRegistration (cname text not null,cusername text not null, " +
            "cpassword text not null,clocation text not null,cemail text not null,ccontact text not null,cmode text not null)";

    public CompanyDatabaseHelper(Context context)
    {
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db=db;
    }

    public void insertContact(CompanyContact c)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values= new ContentValues();

        values.put(COLUMN_CNAME,c.getcname());
        values.put(COLUMN_CUSERNAME,c.getcusername());
        values.put(COLUMN_CPASSWORD, c.getcpassword());
        values.put(COLUMN_CLOCATION,c.getclocation());
        values.put(COLUMN_CEMAIL,c.getcemail());
        values.put(COLUMN_CCONTACT,c.getccontact());
        values.put(COLUMN_CMODE, c.getcmode());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public String searchPassword(String uname)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select cusername, cpassword from "+ TABLE_NAME;
        Cursor Cursor = db.rawQuery(query, null);

        String username, password;
        password = "Not Found!";

        if(Cursor.moveToFirst())
        {
            do{
                username = Cursor.getString(0);

                if(username.equals(uname))
                {
                    password = Cursor.getString(1);
                    break;
                }
            }while(Cursor.moveToNext());
        }
        db.close();
        Cursor.close();
        return(password);
    }

    public String searchName(String uname)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select cusername, cname from "+ TABLE_NAME;
        Cursor Cursor = db.rawQuery(query, null);

        String username,name;
        name = "Not Found!";

        if(Cursor.moveToFirst())
        {
            do{
                username = Cursor.getString(0);

                if(username.equals(uname))
                {
                    name = Cursor.getString(1);
                    break;
                }
            }while(Cursor.moveToNext());
        }
        db.close();
        Cursor.close();
        return(name);
    }

    public String searchLocation(String uname)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select cusername, clocation from "+ TABLE_NAME;
        Cursor Cursor = db.rawQuery(query, null);

        String username, Location;
        Location = "Not Found!";

        if(Cursor.moveToFirst())
        {
            do{
                username = Cursor.getString(0);

                if(username.equals(uname))
                {
                    Location = Cursor.getString(1);
                    break;
                }
            }while(Cursor.moveToNext());
        }
        db.close();
        Cursor.close();
        return(Location);
    }

    public String searchEmail(String uname)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select cusername, cemail from "+ TABLE_NAME;
        Cursor Cursor = db.rawQuery(query, null);

        String username, Email;
        Email = "Not Found!";

        if(Cursor.moveToFirst())
        {
            do{
                username = Cursor.getString(0);

                if(username.equals(uname))
                {
                    Email = Cursor.getString(1);
                    break;
                }
            }while(Cursor.moveToNext());
        }
        db.close();
        Cursor.close();
        return(Email);
    }

    public String searchContact(String uname)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select cusername, ccontact from "+ TABLE_NAME;
        Cursor Cursor = db.rawQuery(query, null);

        String username, Contact;
        Contact = "Not Found!";

        if(Cursor.moveToFirst())
        {
            do{
                username = Cursor.getString(0);

                if(username.equals(uname))
                {
                    Contact = Cursor.getString(1);
                    break;
                }
            }while(Cursor.moveToNext());
        }
        db.close();
        Cursor.close();
        return(Contact);
    }

    public String searchMode(String uname)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select cusername, cmode from "+ TABLE_NAME;
        Cursor Cursor = db.rawQuery(query, null);

        String username, Mode;
        Mode = "Not Found!";

        if(Cursor.moveToFirst())
        {
            do{
                username = Cursor.getString(0);

                if(username.equals(uname))
                {
                    Mode = Cursor.getString(1);
                    break;
                }
            }while(Cursor.moveToNext());
        }
        db.close();
        Cursor.close();
        return(Mode);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS" + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}
