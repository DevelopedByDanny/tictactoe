package com.example.tictactoe;

public class Human extends Player{

    public MoveRecord makeMove(String buttonId) {
        return MoveRecord.of(buttonId, getMarker());
    }

}
