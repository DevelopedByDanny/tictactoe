package com.example.tictactoe;

import static com.example.tictactoe.Marker.O;
import static com.example.tictactoe.Marker.X;

public abstract class Player {
    private int score;
    private Marker marker;
    public Player() {
        this.marker = X;
        this.score = 0;
    }

    public Player(Marker marker) {
        this.marker = marker;
        this.score = 0;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int incrementScore() {
        return ++score;
    }

    public int getScore() {
        return score;
    }

    public Marker getMarker() {
        return marker;
    }

    protected void setMarker(Marker marker) {
        this.marker = marker;
    }

    public void toggleMarker() {
        if (marker.equals(X))
            marker = O;
        else marker = X;
    }
}