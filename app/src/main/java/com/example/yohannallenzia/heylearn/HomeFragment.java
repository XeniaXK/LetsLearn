package com.example.yohannallenzia.heylearn;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_home, container, false);

        final Button agileButton = (Button) view.findViewById(R.id.agileButton);
        final Button leanButton = (Button) view.findViewById(R.id.leanButton);
        final Button designButton = (Button) view.findViewById(R.id.designButton);
        final Button emailButton = (Button) view.findViewById(R.id.emailButton);
        final Button flashcardButton = (Button) view.findViewById(R.id.flashcardButton);
        final Button slideButton = (Button) view.findViewById(R.id.slideButton);

        View.OnClickListener buttonListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button x = (Button) view;

                //agileButton
                if (x == agileButton){
                    Intent intent = new Intent(getContext(), BaseActivity.class);
                    intent.putExtra("methodology", "agile");
                    startActivity(intent);
                }
                //leanButton
                else if(x == leanButton){
                    Intent intent1 = new Intent(getContext(), BaseActivity.class);
                    intent1.putExtra("methodology", "lean");
                    startActivity(intent1);
                }
                //designButton
                else if (x == designButton) {
                    Intent intent2 = new Intent(getContext(), BaseActivity.class);
                    intent2.putExtra("methodology", "design");
                    startActivity(intent2);
                }
                //emailButton
                else if (x == emailButton){
                    Intent intent3 = new Intent(getContext(), Email.class);
                    startActivity(intent3);
                }
                //flashcardButton
                else if (x == flashcardButton){
                    Intent intent4 = new Intent(getContext(), CardActivity.class);
                    startActivity(intent4);
                }
                //SlidesButton
                else if (x == slideButton){
                    Intent intent5 = new Intent(getContext(), DriveActivity.class);
                    startActivity(intent5);
                }
            }
        };

        agileButton.setOnClickListener(buttonListener);
        leanButton.setOnClickListener(buttonListener);
        designButton.setOnClickListener(buttonListener);
        emailButton.setOnClickListener(buttonListener);
        flashcardButton.setOnClickListener(buttonListener);
        slideButton.setOnClickListener(buttonListener);

        return view;
    }
}

