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
    private MaterialButton btnMenu;
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
        btnMenu = findViewById(R.id.btnMenu);
    }

    private void setupGameBoard() {
        Pattern pattern = PatternGenerator.getPatternByName(currentPattern);
        patternNameText.setText(pattern.getName());
        patternRuleText.setText(pattern.getRule());
        updatePlayerTurnText();

        gameBoard.resetBoard();
        gameBoard.setOnCellClickListener((row, col) -> {
            if (isPlayerXTurn) {
                handlePlayerMove(row, col, "X", playerXMoves);
                if (isAI && !gameBoard.isBoardFull()) {
                    makeAIMove();
                }
            } else {
                handlePlayerMove(row, col, "O", playerOMoves);
            }
        });
    }

    private void setupButtons() {
        btnRestart.setOnClickListener(v -> {
            resetGame();
        });

        btnMenu.setOnClickListener(v -> {
            finish();
        });
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
        // Simple AI logic - can be enhanced with minimax later
        Random random = new Random();
        int row, col;
        int attempts = 0;
        final int maxAttempts = 50;

        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
            attempts++;
        } while (!gameBoard.isCellEmpty(row, col) && attempts < maxAttempts);

        if (attempts < maxAttempts) {
            handlePlayerMove(row, col, "O", playerOMoves);
        }
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
        String color = isPlayerXTurn ? "#E53935" : "#1E88E5";
        playerTurnText.setText(String.format("Player <font color='%s'>%s</font>'s Turn", color, player));
    }
}