package com.example.posapplication;

import android.os.Bundle;

import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;

public class AdminActivity extends AppCompatActivity {

    private UserListAdapter userListAdapter;
    public static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
//        PagerAdapter sectionsPagerAdapter = new PagerAdapter(this, getSupportFragmentManager());
//        ViewPager viewPager = findViewById(R.id.view_pager);
//        viewPager.setAdapter(sectionsPagerAdapter);
//        TabLayout tabs = findViewById(R.id.tabs);
//        tabs.setupWithViewPager(viewPager);
        
//        if (findViewById(R.id))
//        viewPager.
//        TabLayoutMediator(tabLayout, viewPager)
    }
}