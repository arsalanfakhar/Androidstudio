package com.example.arsalan.reportcardapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ReportCardAdapter extends ArrayAdapter<ReportCard> {

    public ReportCardAdapter(Context context, ArrayList<ReportCard> reportCards){
        super(context,0,reportCards);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView=convertView;
        if(convertView==null){
            //Inflate View
            listItemView=LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        //it returns object at position
        ReportCard currentReportCard=getItem(position);

        TextView studentNum=listItemView.findViewById(R.id.std_no);
        TextView nameText=listItemView.findViewById(R.id.view_name);
        TextView classText=listItemView.findViewById(R.id.view_class);
        TextView mathText=listItemView.findViewById(R.id.view_math_marks);
        TextView engText=listItemView.findViewById(R.id.view_eng_marks);
        TextView phyText=listItemView.findViewById(R.id.view_phy_marks);
        TextView urduText=listItemView.findViewById(R.id.view_urdu_marks);
        TextView csText=listItemView.findViewById(R.id.view_CS_marks);

        studentNum.setText(studentNum.getText().toString()+(position+1));

        nameText.setText(nameText.getText().toString()+currentReportCard.getmName());

        classText.setText(classText.getText().toString()+currentReportCard.getmClass());

        mathText.setText(mathText.getText().toString()+currentReportCard.getmMathsGrade());

        engText.setText(engText.getText().toString()+currentReportCard.getmEngGrade());

        phyText.setText(phyText.getText().toString()+currentReportCard.getmPhyGrade());

        urduText.setText(urduText.getText().toString()+currentReportCard.getmUrduGrade());

        csText.setText(csText.getText().toString()+currentReportCard.getmCSGrade());

        return listItemView;
    }
}
