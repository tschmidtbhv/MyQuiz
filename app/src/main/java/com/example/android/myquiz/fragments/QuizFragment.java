package com.example.android.myquiz.fragments;

import android.support.v4.app.Fragment;
import android.widget.CompoundButton;

import com.example.android.myquiz.data.Question;
import com.example.android.myquiz.data.QuestionsData;
import com.example.android.myquiz.helper.QuestionHelper;

import java.util.ArrayList;

/**
 * Created by ithom on 14.03.2018.
 */

public abstract class QuizFragment extends Fragment {

    public final static int ISSELECTEDANSWER = 1;
    public final static int ISWRITENANWSER = 2;


    //the current question "index"
    private int mCurrentQuestionIndex = 0;

    //Questiondata
    private QuestionsData questionsData;


    //Abstract Methods
    public abstract void setQuestionToView();

    public abstract void resetSelectionOrTextView();

    public abstract boolean checkGivenAnswer();

    /**
     * load the QuestionData
     *
     * @param questionType use class Constants
     */
    private void loadQuestionData(int questionType) {
        questionsData = new QuestionsData(getContext(), questionType);
    }

    /**
     * load Question for given type
     *
     * @param questionType use class Constants
     * @return Question
     */
    public Question loadQuestion(int questionType) {

        if (questionsData == null) loadQuestionData(questionType);
        return questionsData.getQuestions().get(mCurrentQuestionIndex);
    }

    /**
     * Set the current question index
     *
     * @param questionIndex the index of the question
     */
    public void setQuestionIndex(int questionIndex) {
        mCurrentQuestionIndex = questionIndex;
    }

    /**
     * Check is there a next question
     * If a question the currentQuestionIndex will increes
     * and reset the selection / TextView
     *
     * @return boolean
     */
    public boolean canLoadNextQuestion() {

        if ((mCurrentQuestionIndex + 1) < questionsData.getQuestions().size()) {
            mCurrentQuestionIndex++;
            resetSelectionOrTextView();
        } else {
            return false;
        }

        return true;
    }

    public int getLastQuestionIndex() {
        return mCurrentQuestionIndex;
    }

    /**
     * Check modus, Helper check the answer and return isRight or not
     *
     * @param questionModus
     * @param givenAnswer
     * @param compoundButtonArrayList
     * @return
     */

    public boolean checkGivenAnswer(int questionModus, String givenAnswer, ArrayList<CompoundButton> compoundButtonArrayList) {

        boolean tempBoolean = false;

        switch (questionModus) {

            case ISSELECTEDANSWER:
                tempBoolean = QuestionHelper.checkGivenAnswer(mCurrentQuestionIndex, questionsData, compoundButtonArrayList);
                break;

            case ISWRITENANWSER:
                tempBoolean = QuestionHelper.checkWritenAnswer(givenAnswer, questionsData.getQuestions().get(mCurrentQuestionIndex));
                break;
        }

        return tempBoolean;
    }


}
