package com.example.tictactoe;

public record MoveRecord(int row, int col, int score) {
    public MoveRecord(int row, int col) {
        this(row, col, 0);
    }

    @Override
    public int score() {
        return score;
    }
}
