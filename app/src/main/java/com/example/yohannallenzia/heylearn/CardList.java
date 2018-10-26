package com.example.yohannallenzia.heylearn;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CardList extends ArrayAdapter<Card> {
    private Activity context;

    //List
    private List<Card> cardList;

    public CardList(Activity context, List<Card> cardList){
        super (context, R.layout.card_list, cardList);
        this.context = context;
        this.cardList = cardList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.card_list, null, true);

        //Find the id for front and back of flashcard
        TextView front = (TextView) listViewItem.findViewById(R.id.textView);
        TextView back = (TextView) listViewItem.findViewById(R.id.textView2);
        Card card = cardList.get(position);

        //Set the value for front and back
        front.setText(card.getFrontText());
        back.setText(card.getBackText());

        return listViewItem;

    }
}
