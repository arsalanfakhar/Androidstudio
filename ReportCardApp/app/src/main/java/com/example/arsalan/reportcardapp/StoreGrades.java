package com.example.arsalan.reportcardapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class StoreGrades extends AppCompatActivity {
    final ReportCardList reportCardList=ReportCardList.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_grades);

        final TextView nameText=findViewById(R.id.std_name);
        final TextView classText=findViewById(R.id.std_class);
        final TextView mathText=findViewById(R.id.std_subject1);
        final TextView engText=findViewById(R.id.std_subject2);
        final TextView phyText=findViewById(R.id.std_subject3);
        final TextView urduText=findViewById(R.id.std_subject4);
        final TextView csText=findViewById(R.id.std_subject5);



        nameText.getText();
        Button addStudent=findViewById(R.id.btn_addStudent);

        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reportCardList.addStudent(new ReportCard(nameText.getText().toString(),Integer.parseInt(classText.getText().toString()) ,Integer.parseInt(mathText.getText().toString()) ,
                        Integer.parseInt(engText.getText().toString()) ,Integer.parseInt(phyText.getText().toString()) ,
                        Integer.parseInt(urduText.getText().toString()) ,Integer.parseInt(csText.getText().toString()) ));

            }

        });






    }



    public void checkInput(View view){
        //Checking
        ArrayList<ReportCard> myarr=reportCardList.getListRefrence();

        Toast.makeText(this,myarr.get(0).getmName(),Toast.LENGTH_SHORT).show();

    }



}
