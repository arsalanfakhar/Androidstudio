package com.example.arsalan.reportcardapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button store_Grades=findViewById(R.id.store_grades);

        /**
         * Start Store Grade Activity listener
         */
        store_Grades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent storeGradeIntent=new Intent(MainActivity.this,StoreGrades.class);
                startActivity(storeGradeIntent);
            }
        });

        Button view_Grades=findViewById(R.id.view_Grades);

        /**
         * Start View Grade Activity listener
         */
        view_Grades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewGradeIntent=new Intent(MainActivity.this,ViewGrades.class);
                startActivity(viewGradeIntent);
            }
        });

    }




}
