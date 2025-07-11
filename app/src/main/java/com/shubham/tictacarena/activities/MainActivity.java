package com.shubham.tictacarena.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.shubham.tictacarena.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button playWithFriend = findViewById(R.id.playWithFriend);
        Button playWithAI = findViewById(R.id.playWithAI);
        Button howToPlay = findViewById(R.id.howToPlay);

        playWithFriend.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PatternsActivity.class);
            intent.putExtra("isAI", false);
            startActivity(intent);
        });

        playWithAI.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PatternsActivity.class);
            intent.putExtra("isAI", true);
            startActivity(intent);
        });

        howToPlay.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, HowToPlayActivity.class));
        });
    }
}