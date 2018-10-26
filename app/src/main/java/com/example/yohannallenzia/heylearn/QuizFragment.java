package com.example.yohannallenzia.heylearn;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizFragment extends Fragment {

    private static final String TAG = "QuizFragment";
    TextView questionText, countDownText, Correct, Incorrect;
    Button button1, button2, button3;
    int correct = 0;
    int wrong = 0;
    int total = 0;

    int ComputerCount = 0;
    int qid = 4;
    int agile = -1;
    int design = 9;

    //Firebase
    DatabaseReference databaseQuestions;

    public static List<Questions> questionsList;

    public static List<Questions> questionsList1;

    public static List<Questions> questionsList2;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View myView = inflater.inflate(R.layout.activity_quiz, container, false);
        //databaseQuestions = FirebaseDatabase.getInstance().getReference("Questions");

        //Find view by id for all the variables
        questionText = (TextView) myView.findViewById(R.id.questionNo);
        countDownText = (TextView) myView.findViewById(R.id.CountDown);
        Correct = (TextView) myView.findViewById(R.id.Correct);
        Incorrect = (TextView) myView.findViewById(R.id.Incorrect);

        button1 = (Button) myView.findViewById(R.id.button);
        button2 = (Button) myView.findViewById(R.id.button2);
        button3 = (Button) myView.findViewById(R.id.button3);

        //Agile
        questionsList = new ArrayList<>();
        //Lean
        questionsList1 = new ArrayList<>();
        //Design Thinking
        questionsList2 = new ArrayList<>();

        updateQuestions();
        reverseTimer(60, countDownText);

        return myView;
    }

    //UpdateQuestions
    public void updateQuestions() {
        ComputerCount++;

        if (ComputerCount > 6) {
            Toast.makeText(getContext(), "Game Over", Toast.LENGTH_SHORT).show();
            Correct.setText(String.valueOf(correct));
            Incorrect.setText(String.valueOf(wrong));
        } else {
            databaseQuestions = FirebaseDatabase.getInstance().getReference().child("Questions");
            total++;

            databaseQuestions.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    questionsList.clear();
                    for (DataSnapshot cardSnapshot : dataSnapshot.getChildren()) {
                        Questions card = cardSnapshot.getValue(Questions.class);
                        questionsList.add(card);
                    }
                    /*

                    questionsList adapter = new CardList(MainActivity.this, cardList);
                    listViewCards.setAdapter(adapter);
*/
                    final Questions question = dataSnapshot.getValue(Questions.class);

                    BaseActivity activity = (BaseActivity) getActivity();
                    String myDataFromActivity1 = activity.getMyData();
                    Log.d("Guess my Methodology", myDataFromActivity1);

//                    while (myDataFromActivity1.equalsIgnoreCase("lean")){
//                        qid=6;

//                   for (int i = 0; i < questionsList.size(); i++){
//                    if(qid < questionsList.size()){
//                        qid++;

                    if(qid< 9){
                        qid++;
                        if(myDataFromActivity1.equalsIgnoreCase("lean")){
                            Log.d(TAG, "onDataChange: "+questionsList.get(qid).methodology);
                            //     if (ComputerCount < questionsList.size()) {
                            questionText.setText(questionsList.get(qid).question);
                            button1.setText(questionsList.get(qid).opta);
                            button2.setText(questionsList.get(qid).optb);
                            button3.setText(questionsList.get(qid).optc);

                            // button1.setBackgroundColor(Color.parseColor("#03A9FA"));
                            //   button2.setBackgroundColor(Color.parseColor("#03A9FA"));
                            //  button3.setBackgroundColor(Color.parseColor("#03A9FA"));

                            button1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //question.answer
                                    if (button1.getText().toString().equals(questionsList.get(qid).answernr)) {
                                        Toast.makeText(getContext(), "Correct answer", Toast.LENGTH_SHORT).show();
                                        // button1.setBackgroundColor(Color.GREEN);
                                        correct = correct + 1;
                                        Handler handler = new Handler();

                                        handler.postDelayed(new Runnable() {
                                            public void run() {
                                                //  button1.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                //  button2.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                // button3.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                updateQuestions();
                                            }
                                        }, 1500);

                                    } else {
                                        Log.d("something", questionsList.get(qid).answernr);
                                        Toast.makeText(getContext(), "Incorrect", Toast.LENGTH_SHORT).show();

                                        wrong = wrong + 1;

                                        // button1.setBackgroundColor(Color.RED);

                                        if (button2.getText().toString().equals(question.getAnswernr())) {
                                            //   button2.setBackgroundColor(Color.GREEN);
                                        } else if (button3.getText().toString().equals(question.getAnswernr())) {
                                            //   button3.setBackgroundColor(Color.GREEN);
                                        }

                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                //     button1.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                //     button2.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                //     button3.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                updateQuestions();
                                            }
                                        }, 1500);
                                    }
                                }
                            });

                            //button2
                            button2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //question.answer
                                    if (button2.getText().toString().equals(questionsList.get(qid).answernr)) {
                                        Toast.makeText(getContext(), "Correct answer", Toast.LENGTH_SHORT).show();
                                        // button2.setBackgroundColor(Color.GREEN);
                                        correct = correct + 1;
                                        Handler handler = new Handler();

                                        handler.postDelayed(new Runnable() {
                                            public void run() {
                                                //       button1.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                //      button2.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                //     button3.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                updateQuestions();
                                            }
                                        }, 1500);

                                    } else {
                                        Toast.makeText(getContext(), "Incorrect", Toast.LENGTH_SHORT).show();
                                        wrong = wrong + 1;

                                        //    button2.setBackgroundColor(Color.RED);

                                        if (button3.getText().toString().equals(questionsList.get(qid).answernr)) {
                                            //     button3.setBackgroundColor(Color.GREEN);
                                        } else if (button3.getText().toString().equals(questionsList.get(qid).answernr)) {
                                            //       button3.setBackgroundColor(Color.GREEN);
                                        }

                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                //       button1.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                //      button2.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                //      button3.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                updateQuestions();
                                            }
                                        }, 1500);
                                    }
                                }
                            });

                            //button2
                            button3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //question.answer
                                    if (button3.getText().toString().equals(questionsList.get(qid).answernr)) {
                                        Toast.makeText(getContext(), "Correct answer", Toast.LENGTH_SHORT).show();
                                        //  button3.setBackgroundColor(Color.GREEN);
                                        correct = correct + 1;
                                        Handler handler = new Handler();

                                        handler.postDelayed(new Runnable() {
                                            public void run() {
                                                //  button1.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                //  button2.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                //  button3.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                updateQuestions();
                                            }
                                        }, 1500);

                                    } else {
                                        Toast.makeText(getContext(), "Incorrect", Toast.LENGTH_SHORT).show();
                                        wrong = wrong + 1;

                                        //   button3.setBackgroundColor(Color.RED);

                                        if (button1.getText().toString().equals(questionsList.get(qid).answernr)) {
                                            //    button1.setBackgroundColor(Color.GREEN);
                                        } else if (button2.getText().toString().equals(questionsList.get(qid).answernr)) {
                                            //   button2.setBackgroundColor(Color.GREEN);
                                        }

                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                //     button1.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                //    button2.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                //   button3.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                updateQuestions();
                                            }
                                        }, 1500);
                                    }
                                }
                            });

//                            } else {
//                                Toast.makeText(getContext(), "No more questions", Toast.LENGTH_SHORT).show();
//                                countDownText.setText("Completed");
//                            }

                        }
                    } if(agile< 4){
                        agile++;
                        if(myDataFromActivity1.equalsIgnoreCase("agile")){
                            Log.d(TAG, "onDataChange: "+questionsList.get(agile).methodology);
                            //     if (ComputerCount < questionsList.size()) {
                            questionText.setText(questionsList.get(agile).question);
                            button1.setText(questionsList.get(agile).opta);
                            button2.setText(questionsList.get(agile).optb);
                            button3.setText(questionsList.get(agile).optc);

                            // button1.setBackgroundColor(Color.parseColor("#03A9FA"));
                            // button2.setBackgroundColor(Color.parseColor("#03A9FA"));
                            // button3.setBackgroundColor(Color.parseColor("#03A9FA"));


                            button1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //question.answer
                                    if (button1.getText().toString().equals(questionsList.get(agile).answernr)) {
                                        Toast.makeText(getContext(), "Correct answer", Toast.LENGTH_SHORT).show();
                                        //   button1.setBackgroundColor(Color.GREEN);
                                        correct = correct + 1;
                                        Handler handler = new Handler();

                                        handler.postDelayed(new Runnable() {
                                            public void run() {
                                                //      button1.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                //     button2.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                //     button3.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                updateQuestions();
                                            }
                                        }, 1500);

                                    } else {
                                        Log.d("something", questionsList.get(agile).answernr);
                                        Toast.makeText(getContext(), "Incorrect", Toast.LENGTH_SHORT).show();

                                        wrong = wrong + 1;

                                        // button1.setBackgroundColor(Color.RED);

                                        if (button2.getText().toString().equals(question.getAnswernr())) {
                                            //   button2.setBackgroundColor(Color.GREEN);
                                        } else if (button3.getText().toString().equals(question.getAnswernr())) {
                                            //  button3.setBackgroundColor(Color.GREEN);
                                        }

                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                //     button1.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                //     button2.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                //     button3.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                updateQuestions();
                                            }
                                        }, 1500);
                                    }
                                }
                            });

                            //button2
                            button2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //question.answer
                                    if (button2.getText().toString().equals(questionsList.get(agile).answernr)) {
                                        Toast.makeText(getContext(), "Correct answer", Toast.LENGTH_SHORT).show();
                                        //   button2.setBackgroundColor(Color.GREEN);
                                        correct = correct + 1;
                                        Handler handler = new Handler();

                                        handler.postDelayed(new Runnable() {
                                            public void run() {
                                                //      button1.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                //      button2.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                //      button3.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                updateQuestions();
                                            }
                                        }, 1500);

                                    } else {
                                        Toast.makeText(getContext(), "Incorrect", Toast.LENGTH_SHORT).show();
                                        wrong = wrong + 1;

                                        // button2.setBackgroundColor(Color.RED);

                                        if (button3.getText().toString().equals(questionsList.get(agile).answernr)) {
                                            //  button3.setBackgroundColor(Color.GREEN);
                                        } else if (button3.getText().toString().equals(questionsList.get(agile).answernr)) {
                                            //   button3.setBackgroundColor(Color.GREEN);
                                        }

                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                //  button1.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                //  button2.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                //  button3.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                updateQuestions();
                                            }
                                        }, 1500);
                                    }
                                }
                            });

                            //button2
                            button3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //question.answer
                                    if (button3.getText().toString().equals(questionsList.get(agile).answernr)) {
                                        Toast.makeText(getContext(), "Correct answer", Toast.LENGTH_SHORT).show();
                                        //  button3.setBackgroundColor(Color.GREEN);
                                        correct = correct + 1;
                                        Handler handler = new Handler();

                                        handler.postDelayed(new Runnable() {
                                            public void run() {
                                                //      button1.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                //     button2.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                //    button3.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                updateQuestions();
                                            }
                                        }, 1500);

                                    } else {
                                        Toast.makeText(getContext(), "Incorrect", Toast.LENGTH_SHORT).show();
                                        wrong = wrong + 1;

                                        // button3.setBackgroundColor(Color.RED);

                                        if (button1.getText().toString().equals(questionsList.get(agile).answernr)) {
                                            //   button1.setBackgroundColor(Color.GREEN);
                                        } else if (button2.getText().toString().equals(questionsList.get(agile).answernr)) {
                                            //   button2.setBackgroundColor(Color.GREEN);
                                        }

                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                //     button1.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                //   button2.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                // button3.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                updateQuestions();
                                            }
                                        }, 1500);
                                    }
                                }
                            });

//                            } else {
//                                Toast.makeText(getContext(), "No more questions", Toast.LENGTH_SHORT).show();
//                                countDownText.setText("Completed");
//                            }

                        }
                    }  if(qid< 9){
                        qid++;
                        if(myDataFromActivity1.equalsIgnoreCase("lean")){
                            Log.d(TAG, "onDataChange: "+questionsList.get(qid).methodology);
                            //     if (ComputerCount < questionsList.size()) {
                            questionText.setText(questionsList.get(qid).question);
                            button1.setText(questionsList.get(qid).opta);
                            button2.setText(questionsList.get(qid).optb);
                            button3.setText(questionsList.get(qid).optc);

                            // button1.setBackgroundColor(Color.parseColor("#03A9FA"));
                            //   button2.setBackgroundColor(Color.parseColor("#03A9FA"));
                            //  button3.setBackgroundColor(Color.parseColor("#03A9FA"));


                            button1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //question.answer
                                    if (button1.getText().toString().equals(questionsList.get(qid).answernr)) {
                                        Toast.makeText(getContext(), "Correct answer", Toast.LENGTH_SHORT).show();
                                        // button1.setBackgroundColor(Color.GREEN);
                                        correct = correct + 1;
                                        Handler handler = new Handler();

                                        handler.postDelayed(new Runnable() {
                                            public void run() {
                                                //  button1.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                //  button2.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                // button3.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                updateQuestions();
                                            }
                                        }, 1500);

                                    } else {
                                        Log.d("something", questionsList.get(qid).answernr);
                                        Toast.makeText(getContext(), "Incorrect", Toast.LENGTH_SHORT).show();

                                        wrong = wrong + 1;

                                        // button1.setBackgroundColor(Color.RED);

                                        if (button2.getText().toString().equals(question.getAnswernr())) {
                                            //   button2.setBackgroundColor(Color.GREEN);
                                        } else if (button3.getText().toString().equals(question.getAnswernr())) {
                                            //   button3.setBackgroundColor(Color.GREEN);
                                        }

                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                //     button1.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                //     button2.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                //     button3.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                updateQuestions();
                                            }
                                        }, 1500);
                                    }
                                }
                            });

                            //button2
                            button2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //question.answer
                                    if (button2.getText().toString().equals(questionsList.get(qid).answernr)) {
                                        Toast.makeText(getContext(), "Correct answer", Toast.LENGTH_SHORT).show();
                                        // button2.setBackgroundColor(Color.GREEN);
                                        correct = correct + 1;
                                        Handler handler = new Handler();

                                        handler.postDelayed(new Runnable() {
                                            public void run() {
                                                //       button1.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                //      button2.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                //     button3.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                updateQuestions();
                                            }
                                        }, 1500);

                                    } else {
                                        Toast.makeText(getContext(), "Incorrect", Toast.LENGTH_SHORT).show();
                                        wrong = wrong + 1;

                                        //    button2.setBackgroundColor(Color.RED);

                                        if (button3.getText().toString().equals(questionsList.get(qid).answernr)) {
                                            //     button3.setBackgroundColor(Color.GREEN);
                                        } else if (button3.getText().toString().equals(questionsList.get(qid).answernr)) {
                                            //       button3.setBackgroundColor(Color.GREEN);
                                        }

                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                //       button1.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                //      button2.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                //      button3.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                updateQuestions();
                                            }
                                        }, 1500);
                                    }
                                }
                            });

                            //button2
                            button3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //question.answer
                                    if (button3.getText().toString().equals(questionsList.get(qid).answernr)) {
                                        Toast.makeText(getContext(), "Correct answer", Toast.LENGTH_SHORT).show();
                                        //  button3.setBackgroundColor(Color.GREEN);
                                        correct = correct + 1;
                                        Handler handler = new Handler();

                                        handler.postDelayed(new Runnable() {
                                            public void run() {
                                                //  button1.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                //  button2.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                //  button3.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                updateQuestions();
                                            }
                                        }, 1500);

                                    } else {
                                        Toast.makeText(getContext(), "Incorrect", Toast.LENGTH_SHORT).show();
                                        wrong = wrong + 1;

                                        //   button3.setBackgroundColor(Color.RED);

                                        if (button1.getText().toString().equals(questionsList.get(qid).answernr)) {
                                            //    button1.setBackgroundColor(Color.GREEN);
                                        } else if (button2.getText().toString().equals(questionsList.get(qid).answernr)) {
                                            //   button2.setBackgroundColor(Color.GREEN);
                                        }

                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                //     button1.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                //    button2.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                //   button3.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                updateQuestions();
                                            }
                                        }, 1500);
                                    }
                                }
                            });

//                            } else {
//                                Toast.makeText(getContext(), "No more questions", Toast.LENGTH_SHORT).show();
//                                countDownText.setText("Completed");
//                            }

                        }
                    } if(design< 13){
                        design++;
                        if(myDataFromActivity1.equalsIgnoreCase("design")){
                            Log.d(TAG, "onDataChange: "+questionsList.get(design).methodology);
                            //     if (ComputerCount < questionsList.size()) {
                            questionText.setText(questionsList.get(design).question);
                            button1.setText(questionsList.get(design).opta);
                            button2.setText(questionsList.get(design).optb);
                            button3.setText(questionsList.get(design).optc);

                            // button1.setBackgroundColor(Color.parseColor("#03A9FA"));
                            // button2.setBackgroundColor(Color.parseColor("#03A9FA"));
                            // button3.setBackgroundColor(Color.parseColor("#03A9FA"));


                            button1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //question.answer
                                    if (button1.getText().toString().equals(questionsList.get(design).answernr)) {
                                        Toast.makeText(getContext(), "Correct answer", Toast.LENGTH_SHORT).show();
                                        //   button1.setBackgroundColor(Color.GREEN);
                                        correct = correct + 1;
                                        Handler handler = new Handler();

                                        handler.postDelayed(new Runnable() {
                                            public void run() {
                                                //      button1.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                //     button2.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                //     button3.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                updateQuestions();
                                            }
                                        }, 1500);

                                    } else {
                                        Log.d("something", questionsList.get(design).answernr);
                                        Toast.makeText(getContext(), "Incorrect", Toast.LENGTH_SHORT).show();

                                        wrong = wrong + 1;

                                        // button1.setBackgroundColor(Color.RED);

                                        if (button2.getText().toString().equals(question.getAnswernr())) {
                                            //   button2.setBackgroundColor(Color.GREEN);
                                        } else if (button3.getText().toString().equals(question.getAnswernr())) {
                                            //  button3.setBackgroundColor(Color.GREEN);
                                        }

                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                //     button1.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                //     button2.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                //     button3.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                updateQuestions();
                                            }
                                        }, 1500);
                                    }
                                }
                            });

                            //button2
                            button2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //question.answer
                                    if (button2.getText().toString().equals(questionsList.get(design).answernr)) {
                                        Toast.makeText(getContext(), "Correct answer", Toast.LENGTH_SHORT).show();
                                        //   button2.setBackgroundColor(Color.GREEN);
                                        correct = correct + 1;
                                        Handler handler = new Handler();

                                        handler.postDelayed(new Runnable() {
                                            public void run() {
                                                //      button1.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                //      button2.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                //      button3.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                updateQuestions();
                                            }
                                        }, 1500);

                                    } else {
                                        Toast.makeText(getContext(), "Incorrect", Toast.LENGTH_SHORT).show();
                                        wrong = wrong + 1;

                                        // button2.setBackgroundColor(Color.RED);

                                        if (button3.getText().toString().equals(questionsList.get(design).answernr)) {
                                            //  button3.setBackgroundColor(Color.GREEN);
                                        } else if (button3.getText().toString().equals(questionsList.get(design).answernr)) {
                                            //   button3.setBackgroundColor(Color.GREEN);
                                        }

                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                //  button1.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                //  button2.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                //  button3.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                updateQuestions();
                                            }
                                        }, 1500);
                                    }
                                }
                            });

                            //button2
                            button3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //question.answer
                                    if (button3.getText().toString().equals(questionsList.get(design).answernr)) {
                                        Toast.makeText(getContext(), "Correct answer", Toast.LENGTH_SHORT).show();
                                        //  button3.setBackgroundColor(Color.GREEN);
                                        correct = correct + 1;
                                        Handler handler = new Handler();

                                        handler.postDelayed(new Runnable() {
                                            public void run() {
                                                //      button1.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                //     button2.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                //    button3.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                updateQuestions();
                                            }
                                        }, 1500);

                                    } else {
                                        Toast.makeText(getContext(), "Incorrect", Toast.LENGTH_SHORT).show();
                                        wrong = wrong + 1;

                                        // button3.setBackgroundColor(Color.RED);

                                        if (button1.getText().toString().equals(questionsList.get(design).answernr)) {
                                            //   button1.setBackgroundColor(Color.GREEN);
                                        } else if (button2.getText().toString().equals(questionsList.get(design).answernr)) {
                                            //   button2.setBackgroundColor(Color.GREEN);
                                        }

                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                //     button1.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                //   button2.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                // button3.setBackgroundColor(Color.parseColor("#03A9FA"));
                                                updateQuestions();
                                            }
                                        }, 1500);
                                    }
                                }
                            });

//                            } else {
//                                Toast.makeText(getContext(), "No more questions", Toast.LENGTH_SHORT).show();
//                                countDownText.setText("Completed");
//                            }

                        }
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    public void reverseTimer(int Seconds, final TextView tv) {
        new CountDownTimer(Seconds * 1000 + 1000, 1000) {

            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                tv.setText(String.format("%02d", minutes)
                        + " : " + String.format("%02d", seconds));
            }

            public void onFinish() {
                tv.setText("Completed");
                Correct.setText(String.valueOf(correct));
                Incorrect.setText(String.valueOf(wrong));
            }
        }.start();
    }
}

