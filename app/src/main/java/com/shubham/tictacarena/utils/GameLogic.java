package com.shubham.tictacarena.utils;

import com.shubham.tictacarena.models.PlayerMove;
import com.shubham.tictacarena.views.GameBoardView;
import java.util.Queue;

public class GameLogic {

    public static String checkWinner(
            GameBoardView gameBoard,
            Queue<PlayerMove> playerXMoves,
            Queue<PlayerMove> playerOMoves,
            int[][] winningCombinations
    ) {
        // Check if X has won
        if (checkLines(playerXMoves, winningCombinations)) {
            return "X";
        }

        // Check if O has won
        if (checkLines(playerOMoves, winningCombinations)) {
            return "O";
        }

        return null;
    }

    private static boolean checkLines(Queue<PlayerMove> moves, int[][] winningCombinations) {
        if (moves.size() < 3) return false;

        for (int[] combination : winningCombinations) {
            boolean wins = true;
            for (int i = 0; i < 3; i++) {
                int row = combination[i * 2];
                int col = combination[i * 2 + 1];
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
            if (move.getRow() == row && move.getCol() == col) {
                return true;
            }
        }
        return false;
    }
}