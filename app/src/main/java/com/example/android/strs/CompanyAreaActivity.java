package com.example.android.strs;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.strs.Tab_Layout.AllTransports;
import com.example.android.strs.Company_Tab_Layout.CompanyInfo;
import com.example.android.strs.Company_Tab_Layout.PostTransport;
import com.example.android.strs.Tab_Layout.ViewPagerAdapter;
import com.example.android.strs.data_transport.TransportDatabaseHelper;

public class CompanyAreaActivity extends AppCompatActivity
{

    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    public static String cusername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_area);

        final TransportDatabaseHelper db = new TransportDatabaseHelper(this);

        cusername = getIntent().getStringExtra("cusername");

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragments(new AllTransports(),"All Transports");
        viewPagerAdapter.addFragments(new PostTransport(),"Add New Transport");
        viewPagerAdapter.addFragments(new CompanyInfo(),"Company Information");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}
