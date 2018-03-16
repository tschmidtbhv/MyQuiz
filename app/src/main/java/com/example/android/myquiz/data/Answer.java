package com.example.android.myquiz.data;

/**
 * Created by ithom on 02.03.2018.
 */

public class Answer {

    private String mAnswer;
    private boolean mIsRight;

    public Answer(String answer, boolean isRight) {
        mAnswer = answer;
        mIsRight = isRight;
    }

    public String getAnswer() {
        return mAnswer;
    }

    public boolean isRight() {
        return mIsRight;
    }
}
