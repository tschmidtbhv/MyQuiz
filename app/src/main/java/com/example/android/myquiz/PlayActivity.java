package com.example.android.myquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.android.myquiz.Interfaces.QuestionInterface;
import com.example.android.myquiz.fragments.MultipleChoiceFragment;
import com.example.android.myquiz.fragments.OneAnswerFragment;
import com.example.android.myquiz.fragments.QuizFragment;
import com.example.android.myquiz.fragments.WriteAnswerFragment;
import com.example.android.myquiz.helper.Config;

import static com.example.android.myquiz.helper.Config.LASTPOINTS;
import static com.example.android.myquiz.helper.Config.LASTQUESTION;
import static com.example.android.myquiz.helper.Config.LASTSELECTION;

public class PlayActivity extends AppCompatActivity {

    public static String PACKAGE_NAME;
    int lastFragmentId = 0; //hold the last fragment id
    int gamePoint = 0; //Earned points

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            lastFragmentId = extras.getInt(Config.VARIANTID);
            setFragment(getGameVariantFragment());
        }

        PACKAGE_NAME = getApplicationContext().getPackageName();
    }

    /**
     * Get the given gameVariant Fragment
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
     * OptionsMenu for PlayActivity
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_play, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * On item Selected
     * There is just one item so no if/else needed
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        openPointOverView();
        return super.onOptionsItemSelected(item);
    }

    /**
     * Set the gamevariant fragment to the fragmentHolder
     *
     * @param fragment
     */
    private void setFragment(android.support.v4.app.Fragment fragment) {

        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragementHolder, fragment, String.valueOf(lastFragmentId));
            fragmentTransaction.commit();
        }
    }

    /**
     * OnClick check the answer
     * The user get a message if selected the right or wrong answer
     * points increes if right
     *
     * @param view
     */
    public void checkAnswerClicked(View view) {

        //Get the last fragment
        QuizFragment quizFragment = (QuizFragment) getSupportFragmentManager().findFragmentByTag(String.valueOf(lastFragmentId));
        boolean isRight = quizFragment.checkGivenAnswer();

        if (isRight) {
            Toast.makeText(this, "You choose the right answer", Toast.LENGTH_SHORT).show();
            gamePoint += 25;
            if (!quizFragment.canLoadNextQuestion()) {
                //There are no more questions. Redirekt to resultpage
                openPointOverView();
            } else {
                //Load next Question
                quizFragment.setQuestionToView();
            }
        } else {
            Toast.makeText(this, "Sorry try again", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Put the gamePoints to intent and start ResultActivity
     */
    private void openPointOverView() {

        Intent intent = new Intent(PlayActivity.this, ResultActivity.class);
        intent.putExtra(Config.POINTS, gamePoint);
        startActivity(intent);
    }

    /**
     * Restore the values for
     * index / points / lasttext / lastselection
     *
     * @param savedInstanceState
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState != null) {


            QuizFragment quizFragment = (QuizFragment) getSupportFragmentManager().findFragmentByTag(String.valueOf(lastFragmentId));
            quizFragment.setQuestionIndex(savedInstanceState.getInt(LASTQUESTION)); //Load last question with last Index

            gamePoint = savedInstanceState.getInt(LASTPOINTS); //Restore Gamepoints

            if (lastFragmentId == R.id.write_answer) {
                //Restore Text to TextView
                WriteAnswerFragment writeAnswerFragment = (WriteAnswerFragment) quizFragment;
                writeAnswerFragment.setLastText(savedInstanceState.getString(Config.LASTTEXT));
            } else {
                //Restore last selection
                QuestionInterface questionInterface = (QuestionInterface) quizFragment;
                questionInterface.setLastSelections(savedInstanceState.getIntegerArrayList(LASTSELECTION));
            }

            quizFragment.setQuestionToView();
        }
    }


    /**
     * Save instanceState for index / points / lasttext / lastselection
     *
     * @param outState
     */

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        QuizFragment quizFragment = (QuizFragment) getSupportFragmentManager().findFragmentByTag(String.valueOf(lastFragmentId));
        outState.putInt(LASTQUESTION, quizFragment.getLastQuestionIndex()); //Save last questionIndex
        outState.putInt(LASTPOINTS, gamePoint); //Save Gamepoints

        if (lastFragmentId == R.id.write_answer) {
            WriteAnswerFragment writeAnswerFragment = (WriteAnswerFragment) quizFragment;
            outState.putString(Config.LASTTEXT, writeAnswerFragment.getInputText());
        } else {
            //Save last selection
            QuestionInterface questionInterface = (QuestionInterface) quizFragment;
            outState.putIntegerArrayList(LASTSELECTION, questionInterface.getLastSelections());
        }
    }

}
