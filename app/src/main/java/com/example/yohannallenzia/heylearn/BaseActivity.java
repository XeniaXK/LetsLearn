package com.example.yohannallenzia.heylearn;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class BaseActivity extends AppCompatActivity {

    TextView methodology;
    private String myString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        //Logo
        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo_white);

        //Fragment
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        methodology = (TextView) findViewById(R.id.methodology);
        String st = getIntent().getExtras().getString("methodology");
        methodology.setText(st);
        myString = st;

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new VideoFragment()).commit();
        }

    }

    //Listener for fragment navigation
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                //Direct to home page
                case R.id.navigation_home:
                    selectedFragment = new HomeFragment();
                    BottomNavigationView navigationView = (BottomNavigationView) findViewById( R.id.navigation );
                    navigationView.setVisibility( View.GONE );
                    break;

                    //Direct to video page
                case R.id.navigation_video:
                    selectedFragment = new VideoFragment();
                    break;

                    //Direct to quiz page
                case R.id.navigation_quiz:
                    selectedFragment = new QuizFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

            return true;
        }
    };

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState( savedInstanceState );
        BottomNavigationView navigationView = (BottomNavigationView) findViewById( R.id.navigation );

        if(navigationView.getSelectedItemId() == R.id.navigation_home){
            navigationView.setVisibility( View.GONE );
        }

    }

    public String getMyData() {
        return myString;
    }
}


