package com.shubham.tictacarena.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.shubham.tictacarena.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialButton playWithFriend = findViewById(R.id.playWithFriend);
        MaterialButton playWithAI = findViewById(R.id.playWithAI);
        MaterialButton howToPlay = findViewById(R.id.howToPlay);

        playWithFriend.setOnClickListener(v -> startGame(false));
        playWithAI.setOnClickListener(v -> startGame(true));
        howToPlay.setOnClickListener(v ->
                startActivity(new Intent(this, HowToPlayActivity.class)));
    }

    private void startGame(boolean isAI) {
        Intent intent = new Intent(this, PatternsActivity.class);
        intent.putExtra("isAI", isAI);
        startActivity(intent);
    }
}