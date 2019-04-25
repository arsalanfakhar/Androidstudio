package com.dev_x.mycricket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    int score=0;

    public void updateScore6(View view){
          score+=6;
          changeScore("Score:"+score);
    }
    public void updateScore4(View view){
        score+=4;
        changeScore("Score:"+score);
    }
    public void updateScore3(View view){
        score+=3;
        changeScore("Score:"+score);
    }
    public void updateScore2(View view){
        score+=2;
        changeScore("Score:"+score);
    }
    public void updateScore1(View view){
        score+=1;
        changeScore("Score:"+score);
    }
    public void changeScore(String score) {
        TextView scoreView = (TextView) findViewById(R.id.score_text_view);
        scoreView.setText(score);
    }
    public void resetscore(View view){
        score=0;
        changeScore("Score:"+score);
    }
}
