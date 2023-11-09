package com.example.tictactoe;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GameBoard {
    private final StringProperty[][] board;
    private final int size;

    public GameBoard(int size) {
        this.size = size;
        this.board = new SimpleStringProperty[size][size];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = new SimpleStringProperty("");
            }
        }
    }

    public boolean isMoveValid(int row, int col) {
        return board[row][col].get().isEmpty();
    }

    public void makeMove(int row, int col, String marker) {
        if (isMoveValid(row, col)) {
            board[row][col].set(marker);
        }
    }

    public boolean checkForWin() {
        // Implement win checking logic
        return false;
    }

    public boolean checkForTie() {
        // Implement tie checking logic
        return false;
    }

    public StringProperty[][] getBoard() {
        return board;
    }

    public void resetBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j].set("");
            }
        }
    }

    // Other board-related methods...
}
