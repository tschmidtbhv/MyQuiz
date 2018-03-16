package com.example.android.myquiz.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.android.myquiz.R;
import com.example.android.myquiz.data.Question;
import com.example.android.myquiz.helper.Config;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class OneAnswerFragment extends QuizSelectionFragment {

    private ArrayList<CompoundButton> compoundButtonArrayList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_one_answer, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setQuestionToView();
    }

    /**
     * Set the Question/Answer to it´s View
     */

    public void setQuestionToView() {

        Question question = loadQuestion(Config.ONEANSWER);

        //Get the view reference
        TextView questionTextView = (TextView) getView().findViewById(R.id.question);
        RadioButton answerOne = getView().findViewById(R.id.answerone);
        RadioButton answerTwo = getView().findViewById(R.id.answertwo);
        RadioButton answerThree = getView().findViewById(R.id.answerthree);

        //Set the Question / answer
        questionTextView.setText(question.getQuestion());
        answerOne.setText(question.getAnswers().get(0).getAnswer());
        answerTwo.setText(question.getAnswers().get(1).getAnswer());
        answerThree.setText(question.getAnswers().get(2).getAnswer());

        //Add the RadioButton to ArrayList
        compoundButtonArrayList = new ArrayList<>();
        compoundButtonArrayList.add(answerOne);
        compoundButtonArrayList.add(answerTwo);
        compoundButtonArrayList.add(answerThree);

        setCompoundButtonArrayList(compoundButtonArrayList);
    }

    /**
     * Reset the user selection
     */
    public void resetSelectionOrTextView() {
        RadioGroup radioGroup = getView().findViewById(R.id.radioGroup);
        radioGroup.clearCheck();
    }

    @Override
    public boolean checkGivenAnswer() {
        return super.checkGivenAnswer(QuizFragment.ISSELECTEDANSWER, null, compoundButtonArrayList);
    }
}
