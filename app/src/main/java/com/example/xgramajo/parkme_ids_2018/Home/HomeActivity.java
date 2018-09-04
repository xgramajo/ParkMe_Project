package com.example.xgramajo.parkme_ids_2018.Home;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.xgramajo.parkme_ids_2018.Login.LoginActivity;
import com.example.xgramajo.parkme_ids_2018.R;
import com.google.firebase.auth.FirebaseAuth;


public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseAuth mAuth;

    android.support.v4.app.Fragment mContent;



    FirebaseAuth.AuthStateListener mAuthListener;
    private static String activatedFragment = "homeFragment";

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //if (savedInstanceState != null) {
            //Restore the fragment's instance
            //mContent = getSupportFragmentManager().getFragment(savedInstanceState, "timeLeftFragment");
        //}



        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() == null) {
                    sendToLogin();
                }
            }
        };

        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        HomeFragment homeFragment = new HomeFragment();
        PatentFragment patentFragment = new PatentFragment();
        CounterFragment counterFragment = new CounterFragment();
        TimeLeftFragment timeLeftFragment = new TimeLeftFragment();

        switch (activatedFragment) {
            case "homeFragment":
                fragmentTransaction.add(R.id.fragment_container, homeFragment);
                break;
            case "counterFragment":
                fragmentTransaction.add(R.id.fragment_container, counterFragment);
                break;
            case "patentFragment":
                fragmentTransaction.add(R.id.fragment_container, patentFragment);
                break;
            case "timeLeftFragment":
                fragmentTransaction.add(R.id.fragment_container, timeLeftFragment);
        }

        fragmentTransaction.commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_home:
                HomeActivity.setHomeFragment();
                startActivity(new Intent(this, HomeActivity.class));
                return true;
            case R.id.action_patent:
                HomeActivity.setPatentFragment();
                startActivity(new Intent(this, HomeActivity.class));
                return true;
            case R.id.action_timeleft:
                HomeActivity.setTimeLeftFragment();
                startActivity(new Intent(this, HomeActivity.class));
                return true;
            case R.id.log_out:
                logOut();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logOut() {
        LoginActivity.logOut();
        sendToLogin();
    }

    private void sendToLogin() {
        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
        finish();

    }

    public static void setHomeFragment() {
        activatedFragment = "homeFragment";
    }
    public static void setCounterFragment() {
        activatedFragment = "counterFragment";
    }
    public static void setPatentFragment() {
        activatedFragment = "patentFragment";
    }
    public static void setTimeLeftFragment() {
        activatedFragment = "timeLeftFragment";
    }

    //@Override
    //protected void onSaveInstanceState(Bundle outState) {
        //super.onSaveInstanceState(outState);
        //Save the fragment's instance
        //getSupportFragmentManager().putFragment(outState, "timeLeftFragment", mContent);
    //}






}