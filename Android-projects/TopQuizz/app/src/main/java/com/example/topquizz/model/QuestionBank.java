package com.example.topquizz.model;

import android.util.Log;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class QuestionBank {

    private List<Question> mQuestionList;
    private int mQuestionSuivanteIndex;

    public QuestionBank(List<Question> questionList) {
        mQuestionList = questionList;
        // Shuffle the question list before storing it
        Collections.shuffle(questionList);
        mQuestionSuivanteIndex = 0;
    }

    public Question getQuestion() {
        if (mQuestionSuivanteIndex == mQuestionList.size()) {
            mQuestionSuivanteIndex = 0;
        }
        // Please note the post-incrementation
        return mQuestionList.get(mQuestionSuivanteIndex++);
    }
}