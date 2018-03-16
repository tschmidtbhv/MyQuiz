package com.example.android.myquiz.data;


import java.util.ArrayList;

/**
 * Created by ithom on 02.03.2018.
 */

public class Question {

    private String mQuestion;
    private ArrayList<Answer> mAnswers;

    public Question(String question) {
        mQuestion = question;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public void setQuestion(String mQuestion) {
        this.mQuestion = mQuestion;
    }

    public ArrayList<Answer> getAnswers() {
        return mAnswers;
    }

    public void setAnswers(ArrayList<Answer> mAnswers) {
        this.mAnswers = mAnswers;
    }
}
