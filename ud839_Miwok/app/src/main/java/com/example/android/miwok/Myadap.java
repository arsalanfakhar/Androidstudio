package com.example.android.miwok;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

//public class Myadap extends ArrayAdapter<Word_phrases> {
//
//
//    public Myadap(Context context, ArrayList<Word_phrases> phrases) {
//
//        super(context,0, phrases);
//    }
//
//
//    @Override
//    public View getView(int position, View convertView,ViewGroup parent) {
//
//        View listItemView=convertView;
//        if(listItemView==null){
//            listItemView=LayoutInflater.from(getContext()).inflate(
//                    R.layout.list_item_phrase,parent,false);
//        }
//
//        Word_phrases current_phrase=getItem(position);
//
//        TextView engText= listItemView.findViewById(R.id.phrases_eng);
//        engText.setText(current_phrase.getmEngPhrase());
//
//        TextView miwokText=listItemView.findViewById(R.id.phrases_miwok);
//        miwokText.setText(current_phrase.getmMiwokPhrase());
//
//        return  listItemView;
//
//    }
//}
