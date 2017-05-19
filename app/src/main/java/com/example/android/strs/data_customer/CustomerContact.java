package com.example.android.strs.data_customer;

import java.util.Date;

/**
 * Created by Utkarsh._.Bajpai on 21-Apr-17.
 */

public class CustomerContact {

    String etname, etusername, etpassword, etDate, etEmail,  etContact, gender;

    public void setetname(String etname)
    {
        this.etname = etname;
    }
    public String getetname()
    {
        return this.etname;
    }

    public void setetusername(String etusername)
    {
        this.etusername = etusername;
    }
    public String getetusername()
    {
        return this.etusername;
    }

    public void setetpassword(String etpassword)
    {
        this.etpassword = etpassword;
    }
    public String getetpassword()
    {
        return this.etpassword;
    }

    public void setetDate(String etDate)
    {
        this.etDate = etDate;
    }
    public String getetDate()
    {
        return this.etDate;
    }

    public void setetEmail(String etEmail)
    {
        this.etEmail = etEmail;
    }
    public String getetEmail()
    {
        return this.etEmail;
    }

    public void setetContact(String etContact)
    {
        this.etContact = etContact;
    }
    public String getetContact()
    {
        return this.etContact;
    }

    public void setgender(String gender)
    {
        this.gender = gender;
    }
    public String getgender()
    {
        return this.gender;
    }

}
