package com.example.yohannallenzia.heylearn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class AddQuiz extends AppCompatActivity {

    EditText QuestionA;
    EditText OptionOne;
    EditText OptionTwo;
    EditText OptionThree;
    EditText Answer;

    Button SubmitButton;

    //Store all the methodology
    Spinner spinnerMetho;

    //Firebase
    DatabaseReference databaseQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quiz);

        //Find the Questions table from Firebase Database
        databaseQuestions = FirebaseDatabase.getInstance().getReference("Questions");

        //Find the id of all variables
        QuestionA = (EditText) findViewById(R.id.Question);
        OptionOne = (EditText) findViewById(R.id.Option1);
        OptionTwo = (EditText) findViewById(R.id.Option2);
        OptionThree = (EditText) findViewById(R.id.Option3);
        Answer = (EditText) findViewById(R.id.answer);

        SubmitButton = (Button) findViewById(R.id.SubmitButton);
        spinnerMetho = (Spinner) findViewById(R.id.spinner);

        //Submit button pressed
        SubmitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addQuestion();
            }
        });

    }

    //addQuestions method
    private void addQuestion(){
        //get the value of question, options, genre and answer
        String question = QuestionA.getText().toString().trim();
        String option1 = OptionOne.getText().toString().trim();
        String option2 = OptionTwo.getText().toString().trim();
        String option3 = OptionThree.getText().toString().trim();

        String genre = spinnerMetho.getSelectedItem().toString();
        String answer = Answer.getText().toString().trim();

        if(!TextUtils.isEmpty(question)){

            //Generate a random key
            String id = databaseQuestions.push().getKey();

            //Questions Class
            Questions questionb = new Questions(id, question, option1, option2, option3, answer, genre);
            databaseQuestions.child(id).setValue(questionb);

            //Added successfully meesage
            Toast.makeText(this, "Added succesfully", Toast.LENGTH_LONG).show();

        } else {
            //When field is being left empty
            Toast.makeText(this, "You should enter a question.", Toast.LENGTH_LONG).show();
        }
    }
}
