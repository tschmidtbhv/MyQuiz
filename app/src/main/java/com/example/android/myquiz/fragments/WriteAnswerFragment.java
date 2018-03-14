package com.example.android.myquiz.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.myquiz.Interfaces.QuestionInterface;
import com.example.android.myquiz.R;
import com.example.android.myquiz.data.Question;
import com.example.android.myquiz.data.QuestionsData;
import com.example.android.myquiz.helper.Config;
import com.example.android.myquiz.helper.QuestionHelper;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class WriteAnswerFragment extends Fragment implements QuestionInterface{

    //the current question "index"
    private int currentQuestionIndex = 0;

    QuestionsData questionsData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_write_answer, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setQuestionToView(currentQuestionIndex);
    }

    private void setQuestionToView(int questionIndex) {

        questionsData = new QuestionsData(Config.WRITEANSWER);

        if(questionsData.getQuestions().size() > 0) {

            Question question = questionsData.getQuestions().get(questionIndex);
            TextView questionTextView = getView().findViewById(R.id.question);
            questionTextView.setText(question.getQuestion());
        }
    }

    @Override
    public boolean checkGivenAnswer() {
        TextView answerTextView = getView().findViewById(R.id.answerText);
        return QuestionHelper.checkWritenAnswer(answerTextView.getText().toString(), questionsData.getQuestions().get(currentQuestionIndex));
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
     * Returns the String from TextView
     *
     * @return inputString
     */
    public String getInputText(){

        TextView answerTextView = getView().findViewById(R.id.answerText);
        String inputText = answerTextView.getText().toString();
        return inputText;
    }

    public void setLastText(String lastText) {
        TextView textView = getView().findViewById(R.id.answerText);
        textView.setText(lastText);
    }

    /**
     * WritenAnswerFragment does not have a selection. It returns null
     * @return
     */
    @Override
    public ArrayList<Integer> getLastSelections() {
        return null;
    }

    @Override
    public void setLastSelections(ArrayList<Integer> integerArrayList) {

    }
}
