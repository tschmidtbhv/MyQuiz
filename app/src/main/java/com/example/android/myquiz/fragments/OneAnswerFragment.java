package com.example.android.myquiz.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.android.myquiz.R;
import com.example.android.myquiz.data.Answer;
import com.example.android.myquiz.data.Question;
import com.example.android.myquiz.data.QuestionsData;

/**
 * A simple {@link Fragment} subclass.
 */
public class OneAnswerFragment extends Fragment {

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

        QuestionsData questionsData = new QuestionsData(R.id.random_question);
        Question question = questionsData.getQuestions().get(0);

        TextView questionTextView = (TextView)getView().findViewById(R.id.question);
        questionTextView.setText(question.getQuestion());

        RadioButton answerOne = getView().findViewById(R.id.answerone);
        RadioButton answerTwo = getView().findViewById(R.id.answertwo);
        RadioButton answerThree = getView().findViewById(R.id.answerthree);


        answerOne.setText(question.getAnswers().get(0).getAnswer());
        answerTwo.setText(question.getAnswers().get(1).getAnswer());
        answerThree.setText(question.getAnswers().get(2).getAnswer());

        for(Answer answer : question.getAnswers()){
            Log.v("Question","Question " + answer.getAnswer() + " isRight: " +answer.isRight());
        }

    }

    public boolean questionAvailable(){

        return true;
    }

    public void test(){
        Log.v("OneAnswerFragment", "OneAnswerFragment test()");
    }
}
