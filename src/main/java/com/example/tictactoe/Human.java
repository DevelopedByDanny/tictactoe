package com.example.tictactoe;

public class Human implements Player {
    private int score;
    private final Marker marker;

    public Human(Marker marker) {
        this.marker = marker;
        this.score = 0;
    }

    @Override
    public void incrementScore() {
        score++;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public Marker getMarker() {
        return marker;
    }
}