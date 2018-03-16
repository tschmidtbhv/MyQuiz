package com.example.android.myquiz.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.myquiz.R;
import com.example.android.myquiz.data.Question;
import com.example.android.myquiz.helper.Config;


/**
 * A simple {@link Fragment} subclass.
 */
public class WriteAnswerFragment extends QuizFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_write_answer, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setQuestionToView();
    }

    public void setQuestionToView() {
        Question question = loadQuestion(Config.WRITEANSWER);
        TextView questionTextView = getView().findViewById(R.id.question);
        questionTextView.setText(question.getQuestion());
    }

    public boolean checkGivenAnswer() {
        return super.checkGivenAnswer(QuizFragment.ISWRITENANWSER, getAnswerTextView().getText().toString(), null);
    }

    /**
     * Returns the String from TextView
     *
     * @return inputString
     */
    public String getInputText() {
        String inputText = getAnswerTextView().getText().toString();
        return inputText;
    }

    public void setLastText(String lastText) {
        getAnswerTextView().setText(lastText);
    }

    @Override
    public void resetSelectionOrTextView() {
        getAnswerTextView().setText("");
    }

    /**
     * Get the Answer TextView
     *
     * @return
     */
    private TextView getAnswerTextView() {
        return getView().findViewById(R.id.answerText);
    }
}
