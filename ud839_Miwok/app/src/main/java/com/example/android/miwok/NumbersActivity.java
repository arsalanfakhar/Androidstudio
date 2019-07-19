package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

    private MediaPlayer.OnCompletionListener mOnCompletionListener=new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    private AudioManager mAudioManager;
    //Audio focus listener
    private AudioManager.OnAudioFocusChangeListener aflistener= new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if(focusChange==AudioManager.AUDIOFOCUS_GAIN){
                //resume playing the file
                mMediaPlayer.start();
            }
            else if(focusChange==AudioManager.AUDIOFOCUS_LOSS ){

                releaseMediaPlayer();
            }
            else if(focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            }

        }
    } ;

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        mAudioManager= (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words=new ArrayList<Word>();
        words.add(new Word("one","lutti",R.drawable.number_one,R.raw.number_one));
        words.add(new Word("two","otikko",R.drawable.number_two,R.raw.number_two));
        words.add(new Word("three","tolookosu",R.drawable.number_three,R.raw.number_three));
        words.add(new Word("four","oyissa",R.drawable.number_four,R.raw.number_four ));
        words.add(new Word("five","massokka",R.drawable.number_five,R.raw.number_five) );
        words.add(new Word("six","temmokka",R.drawable.number_six,R.raw.number_six ));
        words.add(new Word("seven","kenekaku",R.drawable.number_seven,R.raw.number_seven) );
        words.add(new Word("eigth","kawinta",R.drawable.number_eight,R.raw.number_eight) );
        words.add(new Word("nine","wo'e",R.drawable.number_nine,R.raw.number_nine) );
        words.add(new Word("ten","na'aacha",R.drawable.number_ten,R.raw.number_ten) );

        WordAdapter wordAdapter=new WordAdapter(this,words,R.color.category_numbers);

        ListView listView= (ListView) findViewById(R.id.list_view);

        listView.setAdapter(wordAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Word currentWord=words.get(position);
                releaseMediaPlayer();

                int result=mAudioManager.requestAudioFocus(aflistener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mMediaPlayer=MediaPlayer.create(NumbersActivity.this,
                            currentWord.getAudioResourceId());
                    mMediaPlayer.start();

                    mMediaPlayer.setOnCompletionListener(mOnCompletionListener);

                }


            }
        });

        /*ArrayAdapter<Word> itemsAdapter=new ArrayAdapter<Word>(this,R.layout.list_item,R.id.default_text_view,words);

        GridView gridView= (GridView) findViewById(R.id.grid);
        gridView.setAdapter(itemsAdapter);*/
/*
        LinearLayout rootView= (LinearLayout) findViewById(R.id.rootView);


        for(int i=0;i<words.size();i++){
            TextView t=new TextView(this);
            t.setText(words.get(i));
            rootView.addView(t);
        }*/

        /* for (String word : words) {
            t.append(word+"\n");
        };*/


    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
            mAudioManager.abandonAudioFocus(aflistener);
        }
    }


}
