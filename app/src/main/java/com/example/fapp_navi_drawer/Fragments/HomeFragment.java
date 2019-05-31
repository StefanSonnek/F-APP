package com.example.fapp_navi_drawer.Fragments;

import android.content.Intent;
import android.widget.ScrollView;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.example.fapp_navi_drawer.AccountActivity;
import com.example.fapp_navi_drawer.MainActivity;
import com.example.fapp_navi_drawer.R;
import com.example.fapp_navi_drawer.SettingsActivity;

public class HomeFragment extends Fragment implements Animation.AnimationListener{
    Button btnWorkout;
    Button btnMeal;
    Button btnFriends;
    Button btnAccount;
    Button btnSettings;
    NavigationView navigationView;
    FragmentManager fragmentManager;
    ViewGroup transitionsContainer;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ScrollView view = (ScrollView) inflater.inflate(R.layout.fragment_home, container, false);

        Animation animZoom = AnimationUtils.loadAnimation(getContext(),
                R.anim.fade_in);
        navigationView = getActivity().findViewById(R.id.nav_view);

        btnWorkout = view.findViewById(R.id.workout_button);
        btnMeal = view.findViewById(R.id.food_button);
        btnFriends = view.findViewById(R.id.social_button);
        btnAccount = view.findViewById(R.id.account_button);
        btnSettings = view.findViewById(R.id.settings_button);

        btnWorkout.startAnimation(animZoom);
        btnMeal.startAnimation(animZoom);
        btnFriends.startAnimation(animZoom);
        btnAccount.startAnimation(animZoom);
        btnSettings.startAnimation(animZoom);
        fragmentManager = getFragmentManager();

        btnWorkout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction().replace(R.id.frame, new WorkoutFragment()).commit();
                navigationView.getMenu().getItem(1).setChecked(true);
            }
        });

        btnMeal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction().replace(R.id.frame, new FoodFragment()).commit();
                navigationView.getMenu().getItem(2).setChecked(true);
            }
        });

        btnFriends.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction().replace(R.id.frame, new SocialFragment()).commit();
                navigationView.getMenu().getItem(3).setChecked(true);
            }
        });

        btnAccount.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AccountActivity.class));
            }
        });

        btnSettings.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SettingsActivity.class));
            }
        });


        return view;
    }


    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

}
