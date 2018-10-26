package com.example.yohannallenzia.heylearn;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CardActivity extends AppCompatActivity {
    EditText front;
    EditText back;
    Button addButton;
    Button viewButton;

    //Firebase
    DatabaseReference databaseReference, businessRef;

    public static DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("Card");
    ListView listViewCards;
    public static List<Card> cardList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        //Logo
        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo_white);

        //Find the id for all the variables
        front = (EditText) findViewById(R.id.frontText);
        back = (EditText) findViewById(R.id.backText);
        addButton = (Button) findViewById(R.id.addBtn);
        viewButton = (Button) findViewById(R.id.viewBtn);

        listViewCards = (ListView) findViewById(R.id.list);

        //Create a new ArrayList
        cardList = new ArrayList<>();

        //View button clicked
        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(CardActivity.this, CardView.class);
                CardActivity.this.startActivity(myIntent);
            }
        });

        //Add button clicked
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCard();

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cardList.clear();
                //Loop through the whole list and store it into cardList
                for(DataSnapshot cardSnapshot : dataSnapshot.getChildren()){
                    Card card = cardSnapshot.getValue(Card.class);
                    cardList.add(card);
                }

                CardList adapter = new CardList(CardActivity.this, cardList);
                listViewCards.setAdapter(adapter);

            }

            //Cancelled
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    //Add a new Card
    private void addCard(){
        //Get the value entered for fronttext and backtext
        String frontText = front.getText().toString();
        String backText = back.getText().toString();

        if(!TextUtils.isEmpty(frontText)){
            //Retireve the Card table
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Card");
            //Get a key for the variable id
            String id = databaseReference.push().getKey();
            //Store values into card
            Card card = new Card(id, frontText, backText);
            databaseReference.child(id).setValue(card);

            //Card added successfully
            Toast.makeText(this, "Card added", Toast.LENGTH_LONG).show();
        }else{
            //Field is empty
            Toast.makeText(this,"You should enter front text", Toast.LENGTH_LONG).show();
        }
    }
}

