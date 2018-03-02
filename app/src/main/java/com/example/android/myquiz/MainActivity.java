package com.example.android.myquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.android.myquiz.helper.Config;
import com.example.android.myquiz.helper.TransitionHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void callPlayActivity(View view){
        Intent intent = new Intent(MainActivity.this, PlayActivity.class);
        intent.putExtra(Config.VARIANTID, view.getId());

        startActivity(intent);
        TransitionHelper.animateSlideIn(this);

    }
}
