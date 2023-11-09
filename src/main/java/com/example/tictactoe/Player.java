package com.example.tictactoe;

public interface Player {
    void incrementScore();
    int getScore();
    Marker getMarker();
}