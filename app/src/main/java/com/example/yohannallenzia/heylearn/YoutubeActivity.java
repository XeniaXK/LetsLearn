package com.example.yohannallenzia.heylearn;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class YoutubeActivity extends YouTubeBaseActivity {

    private static final String TAG="YoutubeActivity";
    static final String GOOGLE_API_KEY = "AIzaSyBjEW8XtXhD5DIaiOv_JZkDkwOcRuZ4IH4";

    YouTubePlayerView myPlayer;
    YouTubePlayer.OnInitializedListener myListener;
    List<String> videoList = new ArrayList<>(  );

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_youtube );

        myPlayer = (YouTubePlayerView) findViewById( R.id.youtubePlayer );
        Intent intent = getIntent();
        String methodology = intent.getStringExtra( "methodology" );
        int position = intent.getExtras().getInt( "position" );

        TextView videoTitle = (TextView) findViewById( R.id.videoTitle );
        TextView videoDesc = (TextView) findViewById( R.id.videoDesc );


        Log.d( TAG, "Youtube: " + methodology + " " + position);

        //match methodology based on user choice on landing page
        if(methodology.equals( "agile" )){
            if(position ==  0){
                videoList.add( "9TycLR0TqFA" );
                videoTitle.setText( "Introduction to Agile" );
                videoDesc.setText( R.string.agile1 );
            } else if(position == 1){
                videoList.add( "KLARQSoNAlc" );
                videoTitle.setText( "Agile Development" );
                videoDesc.setText( R.string.agile2 );
            } else if(position ==  2){
                videoList.add( "EDT0HMtDwYI" );
                videoTitle.setText( "Other" );
                videoDesc.setText( R.string.agile3 );
            }
        } else if(methodology.equals( "lean" )){
            if(position ==  0){
                videoList.add( "20xuu_8wWnY" );
                videoTitle.setText( "Introduction to Lean" );
                videoDesc.setText( R.string.lean1 );
            } else if(position == 1){
                videoList.add( "yZvsqm4Jok8" );
                videoTitle.setText( "Lean Steps" );
                videoDesc.setText( R.string.lean2 );
            } else if(position ==  2){
                videoList.add( "wfsRAZUnonI" );
                videoTitle.setText( "Principles" );
                videoDesc.setText( R.string.lean3 );
            }} else {
            if(position ==  0){
                videoList.add( "0V5BwTrQOCs" );
                videoTitle.setText( "Introduction to Design Thinking" );
                videoDesc.setText( R.string.design1 );
            } else if(position == 1){
                videoList.add( "_r0VX-aU_T8" );
                videoTitle.setText( "Process" );
                videoDesc.setText( R.string.design2 );
            } else if(position ==  2){
                videoList.add( "psLjEBUOnVs" );
                videoTitle.setText( "IBM Design Thinking" );
                videoDesc.setText( R.string.design3 );
                }}

                //Initialise YouTube Player
        myListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                youTubePlayer.loadVideos( videoList );
                if(!b) {
                    youTubePlayer.cueVideo(getIntent().getStringExtra("VIDEO_ID"));
                }
                Log.d( TAG, "onInitializationSuccess: yay" );
            }
            //Initialise failed

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d( TAG, "onInitializationFailure: nooo" );
            }
        };
        //To initialise activity listener
        myPlayer.initialize(GOOGLE_API_KEY, myListener);
    }
}
