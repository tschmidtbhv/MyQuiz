package com.example.android.myquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.android.myquiz.helper.ExtraTags;
import com.example.android.myquiz.helper.TransitionHelper;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        Bundle extras = getIntent().getExtras();

        if(extras != null) {
            TextView textView = findViewById(R.id.textView);
            textView.setText(String.valueOf(extras.getInt(ExtraTags.POINTS)));
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        TransitionHelper.animateSlideOut(this);
    }
}
