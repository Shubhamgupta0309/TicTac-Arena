package com.shubham.tictacarena.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.shubham.tictacarena.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupGameOptions();
    }

    private void setupGameOptions() {
        LinearLayout gameOptionsLayout = findViewById(R.id.gameOptionsLayout);

        // Classic Mode
        addGameOptionButton(gameOptionsLayout, "Classic Tic-Tac-Toe", v -> {
            startGame("Classic", false);
        });

        // AI Challenge
        addGameOptionButton(gameOptionsLayout, "AI Challenge", v -> {
            startGame("Classic", true);
        });

        // Time Attack
        addGameOptionButton(gameOptionsLayout, "Time Attack", v -> {
            startTimedGame("Classic", false);
        });

        // Tournament Mode
        addGameOptionButton(gameOptionsLayout, "Tournament", v -> {
            startTournament();
        });

        // How to Play
        addGameOptionButton(gameOptionsLayout, "How to Play", v -> {
            startActivity(new Intent(this, HowToPlayActivity.class));
        });
    }

    private void addGameOptionButton(LinearLayout layout, String text, View.OnClickListener listener) {
        MaterialButton button = new MaterialButton(this);
        button.setText(text);
        button.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        button.setOnClickListener(listener);
        button.setBackgroundColor(getResources().getColor(R.color.button_primary));
        button.setTextColor(getResources().getColor(R.color.white));
        button.setCornerRadius(8);
        button.setElevation(4);
        button.setPadding(0, 24, 0, 24);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) button.getLayoutParams();
        params.setMargins(0, 0, 0, 16);
        button.setLayoutParams(params);
        layout.addView(button);
    }

    private void startGame(String pattern, boolean isAI) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("pattern", pattern);
        intent.putExtra("isAI", isAI);
        startActivity(intent);
    }

    private void startTimedGame(String pattern, boolean isAI) {
        Intent intent = new Intent(this, TimedGameActivity.class);
        intent.putExtra("pattern", pattern);
        intent.putExtra("isAI", isAI);
        startActivity(intent);
    }

    private void startTournament() {
        startActivity(new Intent(this, TournamentActivity.class));
    }
}