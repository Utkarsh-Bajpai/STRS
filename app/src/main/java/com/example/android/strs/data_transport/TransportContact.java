package com.example.android.strs.data_transport;

import android.net.Uri;

/**
 * Created by Utkarsh._.Bajpai on 23-Apr-17.
 */

public class TransportContact {

    private String  cusername, tname, source, destination, jtime, date, dtime, cost, seats;

    public TransportContact(String tname, String source, String destination, String jtime, String date, String dtime, String cost, String seats,String cusername)
    {
        this.cusername = cusername;
        this.tname = tname;
        this.source = source;
        this.destination = destination;
        this.jtime = jtime;
        this.date = date;
        this.dtime = dtime;
        this.cost = cost;
        this.seats = seats;
    }

    public void setcusername(String cusername)
    {
        this.cusername = cusername;
    }
    public String getcusername()
    {
        return this.cusername;
    }

    public void settname(String tname)
    {
        this.tname = tname;
    }
    public String gettname()
    {
        return this.tname;
    }

    public void setsource(String source)
    {
        this.source = source;
    }
    public String getsource()
    {
        return this.source;
    }

    public void setdestination(String destination)
    {
        this.destination = destination;
    }
    public String getdestination()
    {
        return this.destination;
    }

    public void setjtime(String jtime)
    {
        this.jtime = jtime;
    }
    public String getjtime()
    {
        return this.jtime;
    }

    public void setdate(String date)
    {
        this.date = date;
    }
    public String getdate()
    {
        return this.date;
    }

    public void setdtime(String dtime)
    {
        this.dtime = dtime;
    }
    public String getdtime()
    {
        return this.dtime;
    }

    public void setcost(String cost)
    {
        this.cost = cost;
    }
    public String getcost()
    {
        return this.cost;
    }

    public void setseats(String seats)
    {
        this.seats = seats;
    }
    public String getseats()
    {
        return this.seats;
    }
}
