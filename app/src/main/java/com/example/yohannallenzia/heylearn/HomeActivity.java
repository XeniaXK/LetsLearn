package com.example.yohannallenzia.heylearn;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Logo
        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo_white);

        //Find id for all variables
        final Button agileButton = (Button) findViewById(R.id.agileButton);
        final Button leanButton = (Button) findViewById(R.id.leanButton);
        final Button designButton = (Button) findViewById(R.id.designButton);
        final Button emailButton = (Button) findViewById(R.id.emailButton);
        final Button flashcardButton = (Button) findViewById(R.id.flashcardButton);
        final Button slideButton = (Button) findViewById(R.id.slideButton);

        View.OnClickListener buttonListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button x = (Button) view;

                //agileButton
                if (x == agileButton){
                    Intent intent = new Intent(HomeActivity.this, BaseActivity.class);
                    intent.putExtra("methodology", "agile");
                    startActivity(intent);
                }
                //leanButton
                else if(x == leanButton){
                    Intent intent1 = new Intent(HomeActivity.this, BaseActivity.class);
                    intent1.putExtra("methodology", "lean");
                    startActivity(intent1);
                }
                //designButton
                else if (x == designButton) {
                    Intent intent2 = new Intent(HomeActivity.this, BaseActivity.class);
                    intent2.putExtra("methodology", "design");
                    startActivity(intent2);
                }
                //emailButton
                else if (x == emailButton){
                    Intent intent3 = new Intent(HomeActivity.this, Email.class);
                    startActivity(intent3);
                }
                //flashcardButton
                else if (x == flashcardButton){
                    Intent intent4 = new Intent(HomeActivity.this, CardActivity.class);
                    startActivity(intent4);
                }
                //SlidesButton
                else if (x == slideButton){
                    Intent intent5 = new Intent(HomeActivity.this, DriveActivity.class);
                    startActivity(intent5);
                }
            }
        };

        agileButton.setOnClickListener(buttonListener);
        leanButton.setOnClickListener(buttonListener);
        designButton.setOnClickListener(buttonListener);
        emailButton.setOnClickListener(buttonListener);
        flashcardButton.setOnClickListener( buttonListener );
        slideButton.setOnClickListener( buttonListener );
    }

}
