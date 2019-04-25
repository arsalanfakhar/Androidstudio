package com.example.arsalan.reportcardapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewGrades extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_grades);

        ReportCardAdapter reportCardAdapter=new ReportCardAdapter(this,ReportCardList.getInstance().getListRefrence());

        ListView list=findViewById(R.id.mera_view);

        list.setAdapter(reportCardAdapter);
    }



}
