package com.example.topquizz.model;

import java.util.ArrayList;
import java.util.List;

public class Question {

    private String mQuestion;
    private List<String> mChoixListe;
    private int mReponseIndex;

    public Question(String mQuestion, List<String> mChoixListe, int mReponseIndex) {
        this.mQuestion = mQuestion;
        this.mChoixListe = mChoixListe;
        this.mReponseIndex = mReponseIndex;
    }

    public Question() {
    }

    public String getmQuestion() {
        return mQuestion;
    }

    public void setmQuestion(String mQuestion) {
        this.mQuestion = mQuestion;
    }

    public List<String> getmChoixListe() {
        return mChoixListe;
    }

    public void setmChoixListe(List<String> mChoixListe) {
        this.mChoixListe = mChoixListe;
    }

    public int getmReponseIndex() {
        return mReponseIndex;
    }

    public void setmReponseIndex(int mReponseIndex) {
        this.mReponseIndex = mReponseIndex;
    }
}
