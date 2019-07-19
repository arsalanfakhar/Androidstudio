package com.example.android.miwok;


import android.content.Context;
import android.media.Image;

public class Word  {

    private String mDefaulttranslation;
    private String mMiwokTranslation;
    private int mImageResourceId=-1;
    private int mAudioResourceId;
    /**
     *
     * @param defaulttranslation
     * @param miwokTranslation
     */
    public Word(String defaulttranslation,String miwokTranslation,int audioId){
        this.mDefaulttranslation=defaulttranslation;
        this.mMiwokTranslation=miwokTranslation;
        this.mAudioResourceId=audioId;
    }
    public Word(String defaulttranslation,String miwokTranslation,int imageId,int audioId){
        this.mDefaulttranslation=defaulttranslation;
        this.mMiwokTranslation=miwokTranslation;
        this.mImageResourceId=imageId;
        this.mAudioResourceId=audioId;
    }



    public String getmDefaulttranslation() {
        return mDefaulttranslation;
    }

    public String getmMiwokTranslation() {
        return mMiwokTranslation;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }
    //Method for checking image
    public boolean hasImage(){
        return mImageResourceId!=-1;
    }

    public int getAudioResourceId() {
        return mAudioResourceId;
    }

    @Override
    public String toString() {
        return "Word{" +
                "mDefaulttranslation='" + mDefaulttranslation + '\'' +
                ", mMiwokTranslation='" + mMiwokTranslation + '\'' +
                ", mImageResourceId=" + mImageResourceId +
                ", mAudioResourceId=" + mAudioResourceId +
                '}';
    }
    
}
