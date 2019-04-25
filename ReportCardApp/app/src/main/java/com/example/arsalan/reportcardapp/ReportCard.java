package com.example.arsalan.reportcardapp;

public class ReportCard {
    private String mName;
    private int mClass;
    private int mMathsGrade;
    private int mEngGrade;
    private int mPhyGrade;
    private int mUrduGrade;
    private int mCSGrade;

    public ReportCard(String std_name,int std_class,int mathsGrade,int engGrade,int phyGrade,
                      int urduGrade,int csGrade){
        this.mName=std_name;
        this.mClass=std_class;
        this.mMathsGrade=mathsGrade;
        this.mEngGrade=engGrade;
        this.mPhyGrade=phyGrade;
        this.mUrduGrade=urduGrade;
        this.mCSGrade=csGrade;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public int getmClass() {
        return mClass;
    }

    public void setmClass(int mClass) {
        this.mClass = mClass;
    }

    public int getmMathsGrade() {
        return mMathsGrade;
    }

    public void setmMathsGrade(int mMathsGrade) {
        this.mMathsGrade = mMathsGrade;
    }

    public int getmEngGrade() {
        return mEngGrade;
    }

    public void setmEngGrade(int mEngGrade) {
        this.mEngGrade = mEngGrade;
    }

    public int getmPhyGrade() {
        return mPhyGrade;
    }

    public void setmPhyGrade(int mPhyGrade) {
        this.mPhyGrade = mPhyGrade;
    }

    public int getmUrduGrade() {
        return mUrduGrade;
    }

    public void setmUrduGrade(int mUrduGrade) {
        this.mUrduGrade = mUrduGrade;
    }

    public int getmCSGrade() {
        return mCSGrade;
    }

    public void setmCSGrade(int mCSGrade) {
        this.mCSGrade = mCSGrade;
    }
}
