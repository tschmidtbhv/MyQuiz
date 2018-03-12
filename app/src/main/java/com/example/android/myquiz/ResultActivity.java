package com.example.android.myquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.android.myquiz.helper.Config;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        Bundle extras = getIntent().getExtras();

        if(extras != null) {
            TextView textView = findViewById(R.id.textView);
            textView.setText(String.valueOf(extras.getInt(Config.POINTS)));
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
