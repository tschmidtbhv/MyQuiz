package com.example.android.myquiz.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.android.myquiz.Interfaces.QuestionInterface;
import com.example.android.myquiz.R;
import com.example.android.myquiz.data.Question;
import com.example.android.myquiz.data.QuestionsData;
import com.example.android.myquiz.helper.Config;
import com.example.android.myquiz.helper.QuestionHelper;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class OneAnswerFragment extends Fragment implements QuestionInterface{

    private ArrayList<CompoundButton> radioButtonArrayList;

    //the current question "index"
    private int currentQuestionIndex;

    private QuestionsData questionsData;

    public OneAnswerFragment() {
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_one_answer, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setQuestionToView();
    }

    /**
     * Set the Question/Answer to it´s View
     */
    private void setQuestionToView(){

        currentQuestionIndex = 0;

        //Create a QuestionsData ArrayList / Get the Question
        questionsData = new QuestionsData(Config.ONEANSWER);
        Question question = questionsData.getQuestions().get(currentQuestionIndex);

        //Get the view reference
        TextView questionTextView = (TextView)getView().findViewById(R.id.question);
        RadioButton answerOne = getView().findViewById(R.id.answerone);
        RadioButton answerTwo = getView().findViewById(R.id.answertwo);
        RadioButton answerThree = getView().findViewById(R.id.answerthree);

        //Set the Question / answer
        questionTextView.setText(question.getQuestion());
        answerOne.setText(question.getAnswers().get(0).getAnswer());
        answerTwo.setText(question.getAnswers().get(1).getAnswer());
        answerThree.setText(question.getAnswers().get(2).getAnswer());

        //Add the RadioButton to ArrayList
        radioButtonArrayList = new ArrayList<>();
        radioButtonArrayList.add(answerOne);
        radioButtonArrayList.add(answerTwo);
        radioButtonArrayList.add(answerThree);

    }


    @Override
    public boolean checkGivenAnswer() {
        return QuestionHelper.checkGivenAnswer(currentQuestionIndex, questionsData,radioButtonArrayList);
    }

    @Override
    public void nextQuestion() {
        //TODO load next question
        Log.v(OneAnswerFragment.class.getSimpleName(), "nextQuestion()");
    }

}
