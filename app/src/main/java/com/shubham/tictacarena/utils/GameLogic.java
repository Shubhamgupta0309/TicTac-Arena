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
        // Convert to arrays for easier checking
        PlayerMove[] xMoves = playerXMoves.toArray(new PlayerMove[0]);
        PlayerMove[] oMoves = playerOMoves.toArray(new PlayerMove[0]);

        // Must have exactly 3 pieces to win
        if (xMoves.length == 3) {
            for (int[] combo : winningCombinations) {
                if (matchesCombo(xMoves, combo)) {
                    return "X";
                }
            }
        }

        if (oMoves.length == 3) {
            for (int[] combo : winningCombinations) {
                if (matchesCombo(oMoves, combo)) {
                    return "O";
                }
            }
        }

        return null;
    }

    private static boolean matchesCombo(PlayerMove[] moves, int[] combo) {
        // combo format: [row1,col1, row2,col2, row3,col3]
        boolean pos1 = false, pos2 = false, pos3 = false;

        for (PlayerMove move : moves) {
            if (move.getRow() == combo[0] && move.getCol() == combo[1]) pos1 = true;
            if (move.getRow() == combo[2] && move.getCol() == combo[3]) pos2 = true;
            if (move.getRow() == combo[4] && move.getCol() == combo[5]) pos3 = true;
        }

        return pos1 && pos2 && pos3;
    }
}