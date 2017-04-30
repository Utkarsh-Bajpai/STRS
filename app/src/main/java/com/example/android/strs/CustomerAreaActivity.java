package com.example.android.strs;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.strs.Tab_Layout.AllTransports;
import com.example.android.strs.Tab_Layout.ViewPagerAdapter;
import com.example.android.strs.Customer_Tab_Layout.MyBookings;
import com.example.android.strs.Customer_Tab_Layout.UserInfo;
import com.example.android.strs.data_customer.CustomerDatabaseHelper;

public class CustomerAreaActivity extends AppCompatActivity
{
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    public static String username;

    CustomerDatabaseHelper helper = new CustomerDatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_area);



        username = getIntent().getStringExtra("username");

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragments(new AllTransports(),"All Transports");
        viewPagerAdapter.addFragments(new MyBookings(),"My Bookings");
        viewPagerAdapter.addFragments(new UserInfo(),"My Account");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);


    }
}
