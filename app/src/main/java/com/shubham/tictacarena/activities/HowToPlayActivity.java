package com.shubham.tictacarena.activities;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.shubham.tictacarena.R;

public class HowToPlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play);

        TextView instructions = findViewById(R.id.instructions);
        instructions.setText(
                "How to Play TicTac Arena:\n\n" +
                        "1. Select a game pattern from the list\n" +
                        "2. Each player gets only 3 moves at a time\n" +
                        "3. On the 4th move, the oldest move disappears\n" +
                        "4. Win by completing any 3-in-a-row as per the pattern's rules\n\n" +
                        "Contact: tictacarena@example.com"
        );
    }
}