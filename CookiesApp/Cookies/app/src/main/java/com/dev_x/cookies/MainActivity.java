package com.dev_x.cookies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onclickbtn(View view){
        TextView statustxt=findViewById(R.id.status_txt);
        statustxt.setText("I'm so full");
        ImageView img=findViewById(R.id.cookie_img);
        img.setImageResource(R.drawable.after_cookie);
    }
}
