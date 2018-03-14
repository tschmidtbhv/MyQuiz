package com.example.android.myquiz.Interfaces;

import java.util.ArrayList;

/**
 * Created by ithom on 10.03.2018.
 */

public interface QuestionInterface {

    boolean checkGivenAnswer();
    boolean canLoadNextQuestion();
    int getLastQuestionIndex();
    void loadLastQuestionIndex(int questionIndex);
    ArrayList<Integer> getLastSelections();
    void setLastSelections(ArrayList<Integer> integerArrayList);
}
