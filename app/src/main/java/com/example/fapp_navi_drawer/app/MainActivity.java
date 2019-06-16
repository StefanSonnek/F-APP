package com.example.fapp_navi_drawer.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.fapp_navi_drawer.R;


public class MainActivity extends AppCompatActivity {

    public static final String mypreference = "preferences";
    public static final String keySelectedTabIndex = "selectedTabIndex";

    private TabLayout tabLayout;
    private FloatingActionButton btnAddToDo;

    private SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startWorkout);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Alle"));
        tabLayout.addTab(tabLayout.newTab().setText("BRUST"));
        tabLayout.addTab(tabLayout.newTab().setText("BEINE"));
        tabLayout.addTab(tabLayout.newTab().setText("RÜCKEN"));
        tabLayout.addTab(tabLayout.newTab().setText("ARME"));
        tabLayout.addTab(tabLayout.newTab().setText("SCHULTERN"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
       // final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
       // viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putInt(keySelectedTabIndex, tab.getPosition());
                editor.commit();

                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });

        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        if (sharedpreferences.contains(keySelectedTabIndex)) {
            viewPager.setCurrentItem(sharedpreferences.getInt(keySelectedTabIndex, 0));
        }

        btnAddToDo = findViewById(R.id.btnAddToDo);
        btnAddToDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newUebung = new Intent(MainActivity.this, AddUebung.class);
                startActivity(newUebung);
            }
        });
    }
}
