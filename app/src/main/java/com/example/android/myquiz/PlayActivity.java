package com.example.android.myquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.android.myquiz.fragments.MultipleChoiceFragment;
import com.example.android.myquiz.fragments.OneAnswerFragment;
import com.example.android.myquiz.fragments.WriteAnswerFragment;
import com.example.android.myquiz.helper.ExtraTags;
import com.example.android.myquiz.helper.TransitionHelper;

public class PlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            int fragmentId = extras.getInt(ExtraTags.VARIANTID);
            setFragment(getGameVariantFragment(fragmentId));
        }

    }

    /**
     * Get the given gamevariant Fragment
     *
     * @param variantId
     * @return android.support.v4.app.Fragment
     */
    private android.support.v4.app.Fragment getGameVariantFragment(int variantId) {


        android.support.v4.app.Fragment fragment = null;

        switch (variantId) {

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

        if (fragment != null)
            getSupportFragmentManager().beginTransaction().add(R.id.fragementHolder, fragment).commit();
    }

    /**
     * Check the given answer
     *
     * @param view
     */
    public void checkAnswerClicked(View view) {

        Intent intent = new Intent(PlayActivity.this, ResultActivity.class);
        intent.putExtra(ExtraTags.POINTS, 240);
        startActivity(intent);
        TransitionHelper.animateSlideIn(this);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        TransitionHelper.animateSlideOut(this);
    }
}
