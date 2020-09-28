package com.example.topquizz.model;

import java.io.Serializable;

public class User implements Serializable {

    private String mPrenom;
    private int mScore;

    public int getmScore() {
        return mScore;
    }

    public void setmScore(int mScore) {
        this.mScore = mScore;
    }

    public String getmPrenom() {
        return mPrenom;
    }

    public void setmPrenom(String mPrenom) {
        this.mPrenom = mPrenom;
    }

    public User(String mPrenom, int mScore) {
        this.mPrenom = mPrenom;
        this.mScore = mScore;
    }

    public User() {
    }
}
