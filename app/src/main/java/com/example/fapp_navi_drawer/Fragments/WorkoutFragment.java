package com.example.fapp_navi_drawer.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.fapp_navi_drawer.Activities.workout.AddUebung;
import com.example.fapp_navi_drawer.Activities.workout.UebungAdapter;
import com.example.fapp_navi_drawer.Activities.workout.UpdateUebung;
import com.example.fapp_navi_drawer.R;
import com.example.fapp_navi_drawer.bll.MUSKELGRUPPE;
import com.example.fapp_navi_drawer.bll.Uebung;

import java.util.ArrayList;
import java.util.List;


public class WorkoutFragment extends Fragment {
    public static final String mypreference = "preferences";
    public static final String keySelectedTabIndex = "selectedTabIndex";

    private TabLayout tabLayout;
    private FloatingActionButton btnAddToDo;
    private ArrayList<Uebung> workouts = new ArrayList<>();

    private SharedPreferences sharedpreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.workout_start, container, false);

        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Alle"));
        tabLayout.addTab(tabLayout.newTab().setText("BRUST"));
        tabLayout.addTab(tabLayout.newTab().setText("BEINE"));
        tabLayout.addTab(tabLayout.newTab().setText("RÜCKEN"));
        tabLayout.addTab(tabLayout.newTab().setText("ARME"));
        tabLayout.addTab(tabLayout.newTab().setText("SCHULTERN"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        ListView lv = view.findViewById(R.id.all_workouts);
        lv.setAdapter(new UebungAdapter(getActivity(), workouts));
        if(workouts.size() == 0)
            workouts.add(new Uebung(1, "Dummy", "testing", 5,5,5, MUSKELGRUPPE.ARME));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int position, long l) {
                Intent intent = new Intent(getActivity(), UpdateUebung.class);
                Uebung workout = (Uebung) av.getItemAtPosition(position);
                intent.putExtra("Workout", workout);
                startActivityForResult(intent, 1);
            }
        });

        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.pager);
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

        sharedpreferences = this.getActivity().getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        if (sharedpreferences.contains(keySelectedTabIndex)) {
            viewPager.setCurrentItem(sharedpreferences.getInt(keySelectedTabIndex, 0));
        }

        btnAddToDo = view.findViewById(R.id.btnAddToDo);
        btnAddToDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newUebung = new Intent(getActivity(), AddUebung.class);
                startActivityForResult(newUebung, 1);
            }
        });
        return view;
    }
}
