package com.example.yohannallenzia.heylearn;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import static com.example.yohannallenzia.heylearn.CardActivity.cardList;


public class CardView extends AppCompatActivity {
    private Activity context;

    //Firebase
    DatabaseReference databaseReference;
    TextView frontCard;
    TextView backCard;
    Button next;
    Button flip;
    int qid = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_view );

        //find the id for all the variables
        frontCard = (TextView) findViewById(R.id.showFront);
        backCard = (TextView) findViewById(R.id.showBack);
        next = (Button) findViewById(R.id.nextButton);
        flip = (Button) findViewById(R.id.flipButton);

        //set the vlue for the frontCard
        frontCard.setText(cardList.get(qid).frontText);

        //Next button
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ++qid;
                if (qid<cardList.size()){
                    //Set the value for front and back of the card
                    frontCard.setText( cardList.get(qid).frontText);
                    backCard.setText("");

                }

            }
        });

        //Flip button
        flip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backCard.setText(cardList.get(qid). backText);
            }
        });



    }
}
