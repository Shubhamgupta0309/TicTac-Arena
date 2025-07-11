package com.shubham.tictacarena.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;
import com.shubham.tictacarena.R;

public class GameBoardView extends View {

    private String[][] board = new String[3][3];
    private Paint gridPaint;
    private Paint xPaint;
    private Paint oPaint;
    private OnCellClickListener listener;
    private int cellSize;
    private int boardPadding = 16;

    public GameBoardView(Context context) {
        super(context);
        init();
    }

    public GameBoardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        // Grid paint
        gridPaint = new Paint();
        gridPaint.setColor(getResources().getColor(R.color.grid_line));
        gridPaint.setStrokeWidth(6f);
        gridPaint.setStyle(Paint.Style.STROKE);
        gridPaint.setAntiAlias(true);

        // X player paint
        xPaint = new Paint();
        xPaint.setColor(getResources().getColor(R.color.player_x));
        xPaint.setStrokeWidth(12f);
        xPaint.setStyle(Paint.Style.STROKE);
        xPaint.setAntiAlias(true);

        // O player paint
        oPaint = new Paint();
        oPaint.setColor(getResources().getColor(R.color.player_o));
        oPaint.setStrokeWidth(12f);
        oPaint.setStyle(Paint.Style.STROKE);
        oPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int size = Math.min(getMeasuredWidth(), getMeasuredHeight());
        setMeasuredDimension(size, size);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        cellSize = (getWidth() - 2 * boardPadding) / 3;

        // Draw grid
        for (int i = 1; i < 3; i++) {
            // Vertical lines
            float x = boardPadding + i * cellSize;
            canvas.drawLine(x, boardPadding, x, getHeight() - boardPadding, gridPaint);
            // Horizontal lines
            float y = boardPadding + i * cellSize;
            canvas.drawLine(boardPadding, y, getWidth() - boardPadding, y, gridPaint);
        }

        // Draw X's and O's
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] != null) {
                    float centerX = boardPadding + col * cellSize + cellSize / 2f;
                    float centerY = boardPadding + row * cellSize + cellSize / 2f;
                    float offset = cellSize / 3f;

                    if (board[row][col].equals("X")) {
                        // Draw X
                        canvas.drawLine(
                                centerX - offset, centerY - offset,
                                centerX + offset, centerY + offset,
                                xPaint
                        );
                        canvas.drawLine(
                                centerX + offset, centerY - offset,
                                centerX - offset, centerY + offset,
                                xPaint
                        );
                    } else {
                        // Draw O
                        canvas.drawCircle(centerX, centerY, offset, oPaint);
                    }
                }
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN && listener != null) {
            int row = (int) ((event.getY() - boardPadding) / cellSize);
            int col = (int) ((event.getX() - boardPadding) / cellSize);

            if (row >= 0 && row < 3 && col >= 0 && col < 3) {
                listener.onCellClick(row, col);
                return true;
            }
        }
        return super.onTouchEvent(event);
    }

    public void setCell(int row, int col, String value) {
        board[row][col] = value;
        invalidate();
    }

    public void clearCell(int row, int col) {
        board[row][col] = null;
        invalidate();
    }

    public boolean isCellEmpty(int row, int col) {
        return board[row][col] == null;
    }

    public void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = null;
            }
        }
        invalidate();
    }

    public void setOnCellClickListener(OnCellClickListener listener) {
        this.listener = listener;
    }

    public interface OnCellClickListener {
        void onCellClick(int row, int col);
    }
}