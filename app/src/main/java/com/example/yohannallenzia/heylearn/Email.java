package com.example.yohannallenzia.heylearn;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Email extends AppCompatActivity {
    private EditText messageText;
    private EditText subjectText;
    private Button sendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        //Logo
        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo_white);

        //find the id for all the variables
        messageText = (EditText) findViewById(R.id.message);
        subjectText = (EditText) findViewById(R.id.subject);
        sendBtn = (Button) findViewById(R.id.sendButton);

        //Send button
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMail();
            }
        });
    }

    private void sendMail(){
        String message = messageText.getText().toString();
        String subject = subjectText.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        //Receiver
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"letslearncustservice@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);


        intent.setType("message/rfc822");
        try {
            startActivity(Intent.createChooser(intent, "Send mail..."));
            finish();
            Log.d("Finished sending email.", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(Email.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }

    }
}
