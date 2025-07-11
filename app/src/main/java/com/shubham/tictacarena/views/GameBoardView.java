package com.shubham.tictacarena.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.shubham.tictacarena.models.Pattern;

public class GameBoardView extends View {

    private String[][] board = new String[3][3];
    private Paint paint;
    private OnCellClickListener listener;
    private Pattern currentPattern;

    public GameBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
        paint.setTextSize(100);
        paint.setTextAlign(Paint.Align.CENTER);
    }

    public void setPattern(Pattern pattern) {
        this.currentPattern = pattern;
        resetBoard();
    }

    public void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = "";
            }
        }
        invalidate();
    }

    public void setCell(int row, int col, String value) {
        board[row][col] = value;
        invalidate();
    }

    public void clearCell(int row, int col) {
        board[row][col] = "";
        invalidate();
    }

    public boolean isCellEmpty(int row, int col) {
        return board[row][col].isEmpty();
    }

    public String[][] getBoardState() {
        return board;
    }

    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].isEmpty()) return false;
            }
        }
        return true;
    }

    public boolean isGameOver() {
        return false; // Implement based on game state
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Draw grid
        float cellSize = getWidth() / 3f;

        // Vertical lines
        canvas.drawLine(cellSize, 0, cellSize, getHeight(), paint);
        canvas.drawLine(cellSize * 2, 0, cellSize * 2, getHeight(), paint);

        // Horizontal lines
        canvas.drawLine(0, cellSize, getWidth(), cellSize, paint);
        canvas.drawLine(0, cellSize * 2, getWidth(), cellSize * 2, paint);

        // Draw X's and O's
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!board[i][j].isEmpty()) {
                    float x = j * cellSize + cellSize / 2;
                    float y = i * cellSize + cellSize / 2 + paint.getTextSize() / 3;
                    paint.setColor(board[i][j].equals("X") ? Color.RED : Color.BLUE);
                    canvas.drawText(board[i][j], x, y, paint);
                }
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN && listener != null) {
            float cellSize = getWidth() / 3f;
            int row = (int) (event.getY() / cellSize);
            int col = (int) (event.getX() / cellSize);
            if (row >= 0 && row < 3 && col >= 0 && col < 3) {
                listener.onCellClick(row, col);
            }
            return true;
        }
        return super.onTouchEvent(event);
    }

    public void setOnCellClickListener(OnCellClickListener listener) {
        this.listener = listener;
    }

    public interface OnCellClickListener {
        void onCellClick(int row, int col);
    }
}