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

        //Get Intent extras (Used for points)
        Bundle extras = getIntent().getExtras();

        if(extras != null) {

            //Get the resource string and but the points in it
            String message = getResources().getString(R.string.you_got_point,extras.getInt(Config.POINTS));

            //Set the text with points
            TextView textView = findViewById(R.id.pointsTextView);
            textView.setText(message);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        //Clear the Stack go to MainActivity
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
