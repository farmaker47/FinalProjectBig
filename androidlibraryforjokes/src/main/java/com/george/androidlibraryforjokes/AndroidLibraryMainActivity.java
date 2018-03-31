package com.george.androidlibraryforjokes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

public class AndroidLibraryMainActivity extends AppCompatActivity {

    public static final String JOKE_FROM_JAVA = "joke_from_java";
    private String incomingJoke;
    private TextView androidText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_library_main);

        androidText = findViewById(R.id.androidTextView);

        Intent intent = getIntent();
        if (intent.hasExtra(JOKE_FROM_JAVA)) {
            incomingJoke = intent.getStringExtra(JOKE_FROM_JAVA);
        }else{
            Toast.makeText(this, R.string.noJoke,Toast.LENGTH_LONG).show();
        }

        androidText.setText(incomingJoke);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setTitle(R.string.jokesTitle);
    }

}
