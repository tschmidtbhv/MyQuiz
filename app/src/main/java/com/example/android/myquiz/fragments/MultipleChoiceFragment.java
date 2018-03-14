package com.example.android.myquiz.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
public class MultipleChoiceFragment extends Fragment implements QuestionInterface {

    private ArrayList<CompoundButton> checkBoxArrayList;

    //the current question "index"
    private int currentQuestionIndex = 0;

    private QuestionsData questionsData;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_multiple_choice, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setQuestionToView(currentQuestionIndex);
    }

    private void setQuestionToView(int questionIndex) {

        if(checkBoxArrayList != null) resetSelection(); //Reset the selection

        //Create a QuestionsData ArrayList / Get the Question
        questionsData = new QuestionsData(Config.MULTIPLEANSWER);
        Question question = questionsData.getQuestions().get(questionIndex);

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
        checkBoxArrayList = new ArrayList<>();
        checkBoxArrayList.add(checkBox1);
        checkBoxArrayList.add(checkBox2);
        checkBoxArrayList.add(checkBox3);
    }

    private void resetSelection(){
        for(CompoundButton compoundButton: checkBoxArrayList){
            compoundButton.setChecked(false);
        }
    }

    @Override
    public boolean checkGivenAnswer() {
        return QuestionHelper.checkGivenAnswer(currentQuestionIndex, questionsData, checkBoxArrayList);
    }

    @Override
    public boolean canLoadNextQuestion() {

        if((currentQuestionIndex + 1) < questionsData.getQuestions().size()){
            currentQuestionIndex++;
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
        for(int i = 0; i < checkBoxArrayList.size(); i++) {
            CompoundButton compoundButton = checkBoxArrayList.get(i);
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
            CompoundButton compoundButton =  checkBoxArrayList.get(selectedIndex);
            compoundButton.setChecked(true);
        }
    }
}
