package com.example.android.myquiz;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.android.myquiz.helper.Config;

public class ResultActivity extends AppCompatActivity {

    int gamePoints = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //Get Intent extras (Used for points)
        Bundle extras = getIntent().getExtras();

        if (extras != null) {

            //Get the resource string and but the points in it
            gamePoints = extras.getInt(Config.POINTS);
            String message = getResources().getString(R.string.you_got_point, gamePoints);

            //Set the text with points
            TextView textView = findViewById(R.id.pointsTextView);
            textView.setText(message);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_result, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        sendMail();
        return true;
    }

    /**
     * Open Mail (if available) and parse the Points in it.
     */
    private void sendMail() {

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
        intent.putExtra(Intent.EXTRA_TEXT, createMailText());

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * Create the text for mail
     * Contains app name and gamepoints
     *
     * @return
     */
    private String createMailText() {
        String message = getString(R.string.mail_text, getString(R.string.app_name), gamePoints);
        return message;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        //Clear the Stack go to MainActivity
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}
