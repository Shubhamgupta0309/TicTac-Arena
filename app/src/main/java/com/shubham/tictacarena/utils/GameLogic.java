package com.shubham.tictacarena.utils;

import com.shubham.tictacarena.models.Pattern;
import com.shubham.tictacarena.models.PlayerMove;
import java.util.Queue;

public class GameLogic {

    public static String checkWinner(
            String[][] board,
            String patternName,
            Queue<PlayerMove> playerXMoves,
            Queue<PlayerMove> playerOMoves
    ) {
        Pattern pattern = PatternGenerator.getPatternByName(patternName);

        if (checkLines(playerXMoves, pattern)) return "X";
        if (checkLines(playerOMoves, pattern)) return "O";

        return null;
    }

    private static boolean checkLines(Queue<PlayerMove> moves, Pattern pattern) {
        if (moves.size() < 3) return false;

        for (int[] line : pattern.getWinningLines()) {
            boolean wins = true;
            for (int i = 0; i < 3; i++) {
                int row = line[i * 2];
                int col = line[i * 2 + 1];
                if (!containsMove(moves, row, col)) {
                    wins = false;
                    break;
                }
            }
            if (wins) return true;
        }
        return false;
    }

    private static boolean containsMove(Queue<PlayerMove> moves, int row, int col) {
        for (PlayerMove move : moves) {
            if (move.getRow() == row && move.getCol() == col) return true;
        }
        return false;
    }
}