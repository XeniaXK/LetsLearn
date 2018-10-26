package com.example.yohannallenzia.heylearn;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Vector;

public class VideoFragment extends Fragment {

    private TextView Methologytype;
    private static final String TAG="VideoFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.video_list, container, false);

        BaseActivity activity = (BaseActivity) getActivity();
        final String myDataFromActivity = activity.getMyData();

        ArrayList<String> myVideoList = new ArrayList<String>(  );

        //match the methodology based on users choice on landing page
        if(myDataFromActivity.equals( "agile" )) {
            myVideoList.add( "Introduction to Agile" );
            myVideoList.add( "Agile Development" );
            myVideoList.add( "Other" );
        } else if(myDataFromActivity.equals( "lean" )){
            myVideoList.add( "Introduction to Lean" );
            myVideoList.add( "Lean Steps" );
            myVideoList.add( "Principles" );
        } else {
            myVideoList.add( "Introduction to Design Thinking" );
            myVideoList.add( "Process" );
            myVideoList.add( "IBM Design Thinking" );
        }

        String[] myVideoArray = myVideoList.toArray(new String[myVideoList.size()]);

        ListView listView = (ListView) view.findViewById(R.id.listViewVideo);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>( getContext(), android.R.layout.simple_list_item_1, myVideoArray );
        listView.setAdapter(arrayAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //Pass vairables methodology and position to YoutubeActivity class
                Intent intent = new Intent (getActivity(), YoutubeActivity.class);
                intent.putExtra( "methodology", myDataFromActivity );
                intent.putExtra("position", position);
                Log.d( TAG, "Passing methodology and position : " +myDataFromActivity+ " " + position);
                startActivity(intent);
            }
        });

        return view;

    }
}
