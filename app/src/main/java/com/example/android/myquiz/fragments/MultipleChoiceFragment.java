package com.example.android.myquiz.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.android.myquiz.R;
import com.example.android.myquiz.data.Question;
import com.example.android.myquiz.helper.Config;

import java.util.ArrayList;


public class MultipleChoiceFragment extends QuizSelectionFragment {

    private ArrayList<CompoundButton> compoundButtonArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_multiple_choice, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setQuestionToView();
    }

    public void setQuestionToView() {

        Question question = loadQuestion(Config.MULTIPLEANSWER);

        //Get the view reference
        TextView questionTextView = (TextView) getView().findViewById(R.id.question);
        CheckBox checkBox1 = getView().findViewById(R.id.checkBox1);
        CheckBox checkBox2 = getView().findViewById(R.id.checkBox2);
        CheckBox checkBox3 = getView().findViewById(R.id.checkBox3);

        //Set the Question / answer
        questionTextView.setText(question.getQuestion());
        checkBox1.setText(question.getAnswers().get(0).getAnswer());
        checkBox2.setText(question.getAnswers().get(1).getAnswer());
        checkBox3.setText(question.getAnswers().get(2).getAnswer());

        //Add the Checkboxes to ArrayList
        compoundButtonArrayList = new ArrayList<>();
        compoundButtonArrayList.add(checkBox1);
        compoundButtonArrayList.add(checkBox2);
        compoundButtonArrayList.add(checkBox3);

        setCompoundButtonArrayList(compoundButtonArrayList);
    }

    /**
     * Reset the checkbox selection
     */
    public void resetSelectionOrTextView() {
        for (CompoundButton compoundButton : compoundButtonArrayList) {
            compoundButton.setChecked(false);
        }
    }


    public boolean checkGivenAnswer() {
        return super.checkGivenAnswer(QuizFragment.ISSELECTEDANSWER, null, compoundButtonArrayList);
    }

}
