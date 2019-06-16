package com.example.fapp_navi_drawer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.fapp_navi_drawer.DAL.DatabaseHelperFood;
import com.example.fapp_navi_drawer.Fragments.FoodFragment;
import com.example.fapp_navi_drawer.Fragments.HomeFragment;
import com.example.fapp_navi_drawer.Fragments.SocialFragment;
import com.example.fapp_navi_drawer.Fragments.WorkoutFragment;

public class MainActivity extends AppCompatActivity{

    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Toolbar toolbar;

    public static int navItemIndex;

    private static final String TAG_HOME = "home";
    private static final String TAG_WORKOUT = "workout";
    private static final String TAG_SOCIAL = "social";
    private static final String TAG_FOOD = "food";
    private static final String TAG_SETTINGS = "settings";
    private static final String TAG_ACCOUNT = "account";
    private static final String TAG_LOGOUT = "log out";

    public static String CURRENT_TAG = TAG_HOME;

    private String[] activityTitles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        String username = getIntent().getStringExtra("username");
        if(username != null) {
            NavigationView navigationView = (NavigationView)this.findViewById(R.id.nav_view);
            TextView user = navigationView.getHeaderView(0).findViewById(R.id.nav_header_username);
            user.setText(username);
        }
        navItemIndex = 0;
        new DatabaseHelperFood(this);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);

        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

        setUpNavigationView();

        loadHomeFragment();

    }

    private void loadHomeFragment() {
        // selecting appropriate nav menu item
        selectNavMenu();

        // set toolbar title
        setToolbarTitle();

        // if user select the current navigation menu again
        // just close the navigation drawer
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();
            return;
        }

        //change the Fragment
        Fragment fragment = getFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame, fragment).commit();

        //Closing drawer on item click
        drawer.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }

    private Fragment getFragment() {
        switch (navItemIndex) {
            case 0:
                HomeFragment homeFragment = new HomeFragment();
                return homeFragment;
            case 1:
                WorkoutFragment workoutFragment = new WorkoutFragment();
                return workoutFragment;
            case 2:
                FoodFragment foodFragment = new FoodFragment();
                return foodFragment;
            case 3:
                SocialFragment socialFragment = new SocialFragment();
                return socialFragment;
            case 4:
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                return new HomeFragment();
            case 5:
                startActivity(new Intent(MainActivity.this, AccountActivity.class));
                return new HomeFragment();
            case 6:
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            default:
                return new HomeFragment();
        }
    }

    public void setToolbarTitle() {
        this.getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }

    private void selectNavMenu() {
        if(navItemIndex > 3){
            }
        else {
            navigationView.getMenu().getItem(navItemIndex).setChecked(true);
        }
    }

    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.nav_home:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_HOME;
                        break;
                    case R.id.nav_workout:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_WORKOUT;
                        break;
                    case R.id.nav_food:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_FOOD;
                        break;
                    case R.id.nav_social:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_SOCIAL;
                        break;
                    case R.id.nav_settings:
                        navItemIndex = 4;
                        CURRENT_TAG = TAG_SETTINGS;
                        break;
                    case R.id.nav_account:
                        navItemIndex = 5;
                        CURRENT_TAG = TAG_ACCOUNT;
                        break;
                    case R.id.nav_logout:
                        navItemIndex = 6;
                        CURRENT_TAG = TAG_LOGOUT;
                        break;
                    default:
                        navItemIndex = 0;
                }

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }

                loadHomeFragment();

                return true;
            }
        });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawer.setDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }

        boolean shouldLoadHomeFragOnBackPress = true;
        if (shouldLoadHomeFragOnBackPress) {
            if (navItemIndex != 0) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_HOME;
                loadHomeFragment();
                return;
            }
        }

        super.onBackPressed();
    }

        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }


}
