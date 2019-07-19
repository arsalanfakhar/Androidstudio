package com.example.android.miwok;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v4.content.ContextCompat;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class WordAdapter extends ArrayAdapter<Word> {
    int backgroundColor;
    public WordAdapter(Context context, ArrayList<Word> word,int backgroundColor) {

        super(context,0,word);
        this.backgroundColor=backgroundColor;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Check if existing view is being reused,otherwise inflate the view
        View listItemView=convertView;
        if(listItemView==null){
            listItemView=LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item,parent,false
            );
        }

         Word currentWord=getItem(position);

        ImageView imageView=listItemView.findViewById(R.id.image);
        if(currentWord.hasImage()){

            imageView.setImageResource(currentWord.getImageResourceId());
            imageView.setVisibility(View.VISIBLE);
        }
        else {
            imageView.setVisibility(View.GONE);
        }

        View textContainer=listItemView.findViewById(R.id.text_container);

        //Converting color to passable value
        int color=ContextCompat.getColor(getContext(),backgroundColor);

        textContainer.setBackgroundColor(color);

/*
        //Listener on linear layout to play the word audio
        textContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("avtivity","Current word"+currentWord);
                MediaPlayer mediaPlayer=MediaPlayer.create(getContext(),currentWord.getAudioResourceId());
                mediaPlayer.start();
            }
        });
*/

        //chaledse
        TextView defaultTextView= listItemView.findViewById(R.id.default_text_view);

        defaultTextView.setText(currentWord.getmDefaulttranslation());

        TextView miwokTextView= listItemView.findViewById(R.id.miwok_text_view);
        miwokTextView.setText(currentWord.getmMiwokTranslation());

        return listItemView;

    }
}
