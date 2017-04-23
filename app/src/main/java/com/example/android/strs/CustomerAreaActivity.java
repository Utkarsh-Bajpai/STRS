package com.example.android.strs;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.strs.Tab_Layout.AllTransportsAvailable;
import com.example.android.strs.Tab_Layout.ViewPagerAdapter;
import com.example.android.strs.Customer_Tab_Layout.MyBookings;
import com.example.android.strs.Customer_Tab_Layout.UserInfo;

public class CustomerAreaActivity extends AppCompatActivity
{
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_area);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragments(new AllTransportsAvailable(),"All Transports");
        viewPagerAdapter.addFragments(new MyBookings(),"My Bookings");
        viewPagerAdapter.addFragments(new UserInfo(),"User Info");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
