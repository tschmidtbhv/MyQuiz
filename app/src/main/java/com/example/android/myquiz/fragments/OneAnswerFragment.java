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
import android.widget.RadioGroup;
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
    private int currentQuestionIndex = 0;

    private QuestionsData questionsData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_one_answer, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setQuestionToView(currentQuestionIndex);
    }

    /**
     * Set the Question/Answer to it´s View
     */
    private void setQuestionToView(int questionIndex){


        //Create a QuestionsData ArrayList / Get the Question
        questionsData = new QuestionsData(Config.ONEANSWER);
        Question question = questionsData.getQuestions().get(questionIndex);

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

    private void resetSelection(){
        RadioGroup radioGroup = getView().findViewById(R.id.radioGroup);
        radioGroup.clearCheck();
    }

    @Override
    public boolean checkGivenAnswer() {
        return QuestionHelper.checkGivenAnswer(currentQuestionIndex, questionsData,radioButtonArrayList);
    }

    @Override
    public boolean canLoadNextQuestion() {

        if((currentQuestionIndex + 1) < questionsData.getQuestions().size()){
            currentQuestionIndex++;
            resetSelection(); //Reset the selection
            setQuestionToView(currentQuestionIndex);
        }else {
            return false;
        }

        return true;
    }

    @Override
    public int getLastQuestionIndex() {
        return currentQuestionIndex;
    }

    @Override
    public void loadLastQuestionIndex(int questionIndex) {
        currentQuestionIndex = questionIndex;
        setQuestionToView(questionIndex);
    }

    /**
     * Add the index for the selected CompoundButton
     * @return ArrayList<Integer> all seletions of CompoundButton
     */
    @Override
    public ArrayList<Integer> getLastSelections() {
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        for(int i = 0; i < radioButtonArrayList.size(); i++) {
            CompoundButton compoundButton = radioButtonArrayList.get(i);
            if(compoundButton.isChecked())integerArrayList.add(i);
        }
        return integerArrayList;
    }

    /**
     * Set the selection for CompoundButton
     * @param integerArrayList
     */
    @Override
    public void setLastSelections(ArrayList<Integer> integerArrayList) {
        for(Integer selectedIndex: integerArrayList){
            CompoundButton compoundButton =  radioButtonArrayList.get(selectedIndex);
            compoundButton.setChecked(true);
        }
    }
}
