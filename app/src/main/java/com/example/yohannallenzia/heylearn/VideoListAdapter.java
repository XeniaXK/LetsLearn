package com.example.yohannallenzia.heylearn;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

public class VideoListAdapter extends ArrayAdapter {

    public VideoListAdapter(@NonNull Context context, int resource) {
        super( context, resource );
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;

        //Inflate Youtube player layout
        if(listItem == null){
            listItem = LayoutInflater.from(getContext()).inflate(R.layout.activity_youtube, parent, false);
        }
        YouTubePlayerView myPlayer = (YouTubePlayerView) listItem.findViewById(R.id.youtubePlayer);

        return listItem;
    }
}
