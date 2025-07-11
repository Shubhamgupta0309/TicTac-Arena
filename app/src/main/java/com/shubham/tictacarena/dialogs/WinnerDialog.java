package com.shubham.tictacarena.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.widget.Button;
import android.widget.TextView;
import com.shubham.tictacarena.R;

public class WinnerDialog extends Dialog {

    public WinnerDialog(Context context, String result, Runnable onPlayAgain, Runnable onExit) {
        super(context);
        setContentView(R.layout.activity_winner_dialog);

        TextView resultText = findViewById(R.id.resultText);
        Button playAgain = findViewById(R.id.playAgain);
        Button backToMenu = findViewById(R.id.backToMenu);

        resultText.setText(result.equals("DRAW") ? "It's a Draw!" : "Player " + result + " Wins!");

        playAgain.setOnClickListener(v -> {
            dismiss();
            onPlayAgain.run();
        });

        backToMenu.setOnClickListener(v -> {
            dismiss();
            onExit.run();
        });
    }
}