package com.example.arsalan.reportcardapp;

import java.util.ArrayList;

public class ReportCardList {

    private ArrayList<ReportCard> myList=new ArrayList<>();
    private static ReportCardList reportCardList=new ReportCardList();
    private ReportCardList(){

    }

    public void addStudent( ReportCard card){
        myList.add(card);
    }

    public ArrayList<ReportCard> getListRefrence(){
        return myList;
    }

    public static ReportCardList getInstance(){
        return reportCardList;
    }
}
