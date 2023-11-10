package com.example.tictactoe;

import static com.example.tictactoe.Marker.X;

public record MoveRecord(int row, int col, int score, Marker marker) {
    public MoveRecord(int row, int col) {
        this(row, col, 0, X);
    }
    public MoveRecord(int row, int col, int score){
        this(row, col, score, X);
    }
    public MoveRecord(int row, int col, Marker marker){
        this(row, col, 0, marker);
    }
    public static MoveRecord of(String string, Marker marker) {
        var row = Integer.parseInt(string.substring(6, 7));
        var col = Integer.parseInt(string.substring(7));
        return new MoveRecord(row, col, marker);
    }
    public static MoveRecord of(int row, int col, Marker marker) {
        return new MoveRecord(row, col, marker);
    }
}
