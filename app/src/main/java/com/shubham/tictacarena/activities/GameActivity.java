package com.shubham.tictacarena.activities;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
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
    private MaterialButton btnRestart;
    private boolean isAI;
    private boolean isPlayerXTurn = true;
    private Queue<PlayerMove> playerXMoves = new LinkedList<>();
    private Queue<PlayerMove> playerOMoves = new LinkedList<>();
    private Pattern currentPattern;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        isAI = getIntent().getBooleanExtra("isAI", false);
        String patternName = getIntent().getStringExtra("pattern");
        currentPattern = PatternGenerator.getPatternByName(patternName);

        initializeViews();
        setupGameBoard();
        setupButtons();
    }

    private void initializeViews() {
        gameBoard = findViewById(R.id.gameBoard);
        playerTurnText = findViewById(R.id.playerTurnText);
        patternNameText = findViewById(R.id.patternName);
        patternRuleText = findViewById(R.id.patternRule);
        btnRestart = findViewById(R.id.btnRestart);

        patternNameText.setText(currentPattern.getName());
        patternRuleText.setText(currentPattern.getRule());
    }

    private void setupGameBoard() {
        updatePlayerTurnText();
        gameBoard.resetBoard();
        gameBoard.setOnCellClickListener((row, col) -> {
            if (isPlayerXTurn) {
                handlePlayerMove(row, col, "X", playerXMoves);
                if (isAI && !isBoardFull()) {
                    makeAIMove();
                }
            } else {
                handlePlayerMove(row, col, "O", playerOMoves);
            }
        });
    }

    private void setupButtons() {
        btnRestart.setOnClickListener(v -> resetGame());
    }

    private void handlePlayerMove(int row, int col, String player, Queue<PlayerMove> moves) {
        if (!gameBoard.isCellEmpty(row, col)) return;

        if (moves.size() >= 3) {
            PlayerMove oldestMove = moves.poll();
            gameBoard.clearCell(oldestMove.getRow(), oldestMove.getCol());
        }
        moves.add(new PlayerMove(row, col));
        gameBoard.setCell(row, col, player);
        checkWinner();
        isPlayerXTurn = !isPlayerXTurn;
        updatePlayerTurnText();
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
                gameBoard,
                playerXMoves,
                playerOMoves,
                currentPattern.getWinningLines()
        );

        if (winner != null) {
            showWinnerDialog(winner);
        } else if (isBoardFull()) {
            showWinnerDialog("DRAW");
        }
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameBoard.isCellEmpty(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    private void showWinnerDialog(String result) {
        WinnerDialog dialog = new WinnerDialog(this, result, this::resetGame, this::finish);
        dialog.show();
    }

    private void resetGame() {
        playerXMoves.clear();
        playerOMoves.clear();
        isPlayerXTurn = true;
        gameBoard.resetBoard();
        updatePlayerTurnText();
    }

    private void updatePlayerTurnText() {
        String player = isPlayerXTurn ? "X" : "O";
        playerTurnText.setText(String.format("Player %s's Turn", player));
    }
}