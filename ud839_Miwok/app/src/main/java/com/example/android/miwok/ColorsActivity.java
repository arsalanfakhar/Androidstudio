package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

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
        words.add(new Word("red","weṭeṭṭi",R.drawable.color_red,R.raw.color_red));
        words.add(new Word("green","chokokki",R.drawable.color_green,R.raw.color_green));

        words.add(new Word("brown","ṭakaakki",R.drawable.color_brown,R.raw.color_brown));

        words.add(new Word("gray","ṭopoppi",R.drawable.color_gray,R.raw.color_gray));

        //Create your own adapter
        WordAdapter wordAdapter=new WordAdapter(this,words,R.color.category_colors);
        ListView listView= (ListView) findViewById(R.id.list_view);

        listView.setAdapter(wordAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Word currentWord=words.get(position);

                releaseMediaPlayer();


                int result=mAudioManager.requestAudioFocus(aflistener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mMediaPlayer=MediaPlayer.create(ColorsActivity.this,
                            currentWord.getAudioResourceId());
                    mMediaPlayer.start();

                    mMediaPlayer.setOnCompletionListener(mOnCompletionListener);

                }

            }
        });
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
