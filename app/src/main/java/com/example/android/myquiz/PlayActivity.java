package com.example.android.myquiz;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.android.myquiz.Interfaces.QuestionInterface;
import com.example.android.myquiz.fragments.MultipleChoiceFragment;
import com.example.android.myquiz.fragments.OneAnswerFragment;
import com.example.android.myquiz.fragments.WriteAnswerFragment;
import com.example.android.myquiz.helper.Config;

import static com.example.android.myquiz.helper.Config.LASTPOINTS;
import static com.example.android.myquiz.helper.Config.LASTQUESTION;
import static com.example.android.myquiz.helper.Config.LASTSELECTION;
import static com.example.android.myquiz.helper.Config.LASTTEXT;

public class PlayActivity extends AppCompatActivity {

    int lastFragmentId = 0;
    int gamePoint = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            lastFragmentId = extras.getInt(Config.VARIANTID);
            setFragment(getGameVariantFragment());
        }
    }

    /**
     * Get the given gamevariant Fragment
     *
     * @return android.support.v4.app.Fragment
     */
    private android.support.v4.app.Fragment getGameVariantFragment() {


        android.support.v4.app.Fragment fragment = null;

        switch (lastFragmentId) {

            case R.id.random_question:
                fragment = new OneAnswerFragment();
                break;

            case R.id.write_answer:
                fragment = new WriteAnswerFragment();
                break;

            case R.id.multiple_choice:
                fragment = new MultipleChoiceFragment();
                break;
        }

        return fragment;
    }

    /**
     * Set the gamevariant fragment to the fragmentHolder
     *
     * @param fragment
     */
    private void setFragment(android.support.v4.app.Fragment fragment) {

        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragementHolder, fragment,String.valueOf(lastFragmentId));
            fragmentTransaction.commit();
        }
    }

    /**
     * Check the given answer
     *
     * @param view
     */
    public void checkAnswerClicked(View view) {

        QuestionInterface questionInterface = (QuestionInterface)getSupportFragmentManager().findFragmentByTag(String.valueOf(lastFragmentId));

        boolean isRight = questionInterface.checkGivenAnswer();

        if(isRight){
            Toast.makeText(this,"You choose the right answer", Toast.LENGTH_SHORT).show();
            gamePoint += 25;
            if(!questionInterface.canLoadNextQuestion()){
                //There are no more questions. Redirekt to resultpage
                Intent intent = new Intent(PlayActivity.this,ResultActivity.class);
                intent.putExtra(Config.POINTS, gamePoint);
                startActivity(intent);
            }

        }else {
            Toast.makeText(this,"Sorry try again", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if(savedInstanceState != null) {


            QuestionInterface questionInterface = (QuestionInterface)getSupportFragmentManager().findFragmentByTag(String.valueOf(lastFragmentId));
            questionInterface.loadLastQuestionIndex(savedInstanceState.getInt(LASTQUESTION)); //Load last question with last Index
            gamePoint = savedInstanceState.getInt(LASTPOINTS); //Restore Gamepoints

            if (lastFragmentId == R.id.write_answer) {
                //Restore Text to TextView
                WriteAnswerFragment writeAnswerFragment = (WriteAnswerFragment)questionInterface;
                writeAnswerFragment.setLastText(savedInstanceState.getString(LASTTEXT));
            }else {
                //Restore last selection
                questionInterface.setLastSelections(savedInstanceState.getIntegerArrayList(LASTSELECTION));
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        QuestionInterface questionInterface = (QuestionInterface)getSupportFragmentManager().findFragmentByTag(String.valueOf(lastFragmentId));
        outState.putInt(LASTQUESTION, questionInterface.getLastQuestionIndex()); //Save last questionIndex
        outState.putInt(LASTPOINTS, gamePoint); //Save Gamepoints

        if(lastFragmentId == R.id.write_answer){
            WriteAnswerFragment writeAnswerFragment = (WriteAnswerFragment)questionInterface;
            outState.putString(LASTTEXT,writeAnswerFragment.getInputText());
        }else {
            //Save last selection
            outState.putIntegerArrayList(LASTSELECTION, questionInterface.getLastSelections());
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
