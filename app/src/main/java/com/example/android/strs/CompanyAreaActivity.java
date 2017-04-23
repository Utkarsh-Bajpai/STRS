package com.example.android.strs;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.strs.Tab_Layout.AllTransportsAvailable;
import com.example.android.strs.Company_Tab_Layout.CompanyInfo;
import com.example.android.strs.Company_Tab_Layout.PostTransport;
import com.example.android.strs.Tab_Layout.ViewPagerAdapter;

public class CompanyAreaActivity extends AppCompatActivity
{

    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_area);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragments(new AllTransportsAvailable(),"All Transports");
        viewPagerAdapter.addFragments(new PostTransport(),"Add New Transport");
        viewPagerAdapter.addFragments(new CompanyInfo(),"Company Info");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}
