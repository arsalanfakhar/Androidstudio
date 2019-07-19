package com.example.android.miwok;

public class Word_phrases {

    private String mMiwokPhrase;
    private String mEngPhrase;

    public Word_phrases(String engPhrase,String miwokPhrase){
        this.mEngPhrase=engPhrase;
        this.mMiwokPhrase=miwokPhrase;
    }

    public String getmMiwokPhrase() {
        return mMiwokPhrase;
    }

    public String getmEngPhrase() {
        return mEngPhrase;
    }
}
