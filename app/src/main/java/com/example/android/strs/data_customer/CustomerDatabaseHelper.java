package com.example.android.strs.data_customer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.android.strs.CustomerRegisterActivity;

import java.util.Date;

/**
 * Created by Utkarsh._.Bajpai on 21-Apr-17.
 */

public class CustomerDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "CustomerRegistration.db";
    private static final String TABLE_NAME = "CustomerRegistration";
    private static final String COLUMN_ETNAME = "etName";
    private static final String COLUMN_ETUSERNAME = "etUsername";
    private static final String COLUMN_ETPASSWORD = "etPassword";
    private static final String COLUMN_ETDATE = "etDate";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_CONTACT = "contact";
    private static final String COLUMN_GENDER = "gender";

    SQLiteDatabase db;

    private static final String TABLE_CREATE = "create table CustomerRegistration (etName text not null,etUsername text not null," +
            "etPassword text not null,etDate text not null,email text not null,contact text not null,gender text not null)";

    public CustomerDatabaseHelper(Context context)
    {
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db=db;
    }

    public void insertContact(CustomerContact c)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values= new ContentValues();

        values.put(COLUMN_ETNAME,c.getetname());
        values.put(COLUMN_ETUSERNAME,c.getetusername());
        values.put(COLUMN_ETPASSWORD, c.getetpassword());
        values.put(COLUMN_ETDATE,c.getetDate());
        values.put(COLUMN_EMAIL, c.getetEmail());
        values.put(COLUMN_CONTACT,c.getetContact());
        values.put(COLUMN_GENDER, c.getgender());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public String searchPassword(String uname)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select etUsername, etPassword from "+ TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        String username, password;
        password = "Not Found!";

        if(cursor.moveToFirst())
        {
            do{
                username = cursor.getString(0);

                if(username.equals(uname))
                {
                    password = cursor.getString(1);
                    break;
                }
            }while(cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return(password);
    }

    public String searchName(String uname)
    {

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select etUsername, etName from "+ TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);

        String username, name;
        name = "Not Found!";

        if(cursor.moveToFirst())
        {
            do{
                username = cursor.getString(0);
                if(username.equals(uname))
                {
                    name = cursor.getString(1);
                    break;
                }
            }while(cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return(name);
    }

    public String searchDob(String uname)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select etUsername, etDate from "+ TABLE_NAME;
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

    public String searchEmail(String uname)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select etUsername, email from "+ TABLE_NAME;
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
        String query = "select etUsername, contact from "+ TABLE_NAME;
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

    public String searchGender(String uname)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select etUsername, gender from "+ TABLE_NAME;
        Cursor Cursor = db.rawQuery(query, null);

        String username, Gender;
        Gender = "Not Found!";

        if(Cursor.moveToFirst())
        {
            do{
                username = Cursor.getString(0);

                if(username.equals(uname))
                {
                    Gender = Cursor.getString(1);
                    break;
                }
            }while(Cursor.moveToNext());
        }
        db.close();
        Cursor.close();
        return(Gender);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS" + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}

