package com.shubham.tictacarena.activities;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.shubham.tictacarena.R;
import com.shubham.tictacarena.dialogs.WinnerDialog;
import com.shubham.tictacarena.models.Pattern;
import com.shubham.tictacarena.models.PlayerMove;
import com.shubham.tictacarena.utils.GameLogic;
import com.shubham.tictacarena.utils.PatternGenerator;
import com.shubham.tictacarena.views.GameBoardView;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private GameBoardView gameBoard;
    private TextView playerTurnText;
    private TextView patternNameText;
    private TextView patternRuleText;
    private String currentPattern;
    private boolean isAI;
    private boolean isPlayerXTurn = true;
    private Queue<PlayerMove> playerXMoves = new LinkedList<>();
    private Queue<PlayerMove> playerOMoves = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        currentPattern = getIntent().getStringExtra("pattern");
        isAI = getIntent().getBooleanExtra("isAI", false);

        gameBoard = findViewById(R.id.gameBoard);
        playerTurnText = findViewById(R.id.playerTurnText);
        patternNameText = findViewById(R.id.patternName);
        patternRuleText = findViewById(R.id.patternRule);

        Pattern pattern = PatternGenerator.getPatternByName(currentPattern);
        patternNameText.setText(pattern.getName());
        patternRuleText.setText(pattern.getRule());
        playerTurnText.setText("Player X's Turn");

        gameBoard.setPattern(pattern);
        gameBoard.setOnCellClickListener((row, col) -> {
            if (isPlayerXTurn) {
                handlePlayerMove(row, col, "X", playerXMoves);
                if (isAI && !gameBoard.isGameOver()) {
                    makeAIMove();
                }
            } else {
                handlePlayerMove(row, col, "O", playerOMoves);
            }
        });
    }

    private void handlePlayerMove(int row, int col, String player, Queue<PlayerMove> moves) {
        if (moves.size() >= 3) {
            PlayerMove oldestMove = moves.poll();
            gameBoard.clearCell(oldestMove.getRow(), oldestMove.getCol());
        }
        moves.add(new PlayerMove(row, col));
        gameBoard.setCell(row, col, player);
        checkWinner();
        isPlayerXTurn = !isPlayerXTurn;
        playerTurnText.setText(isPlayerXTurn ? "Player X's Turn" : "Player O's Turn");
    }

    private void makeAIMove() {
        Random random = new Random();
        int row, col;
        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (!gameBoard.isCellEmpty(row, col));

        handlePlayerMove(row, col, "O", playerOMoves);
    }

    private void checkWinner() {
        String winner = GameLogic.checkWinner(
                gameBoard.getBoardState(),
                currentPattern,
                playerXMoves,
                playerOMoves
        );

        if (winner != null) {
            showWinnerDialog(winner);
        } else if (gameBoard.isBoardFull()) {
            showWinnerDialog("DRAW");
        }
    }

    private void showWinnerDialog(String result) {
        WinnerDialog dialog = new WinnerDialog(this, result, this::recreate, this::finish);
        dialog.show();
    }
}