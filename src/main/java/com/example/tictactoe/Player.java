package com.example.tictactoe;

import javafx.beans.property.StringProperty;

import static com.example.tictactoe.Marker.O;
import static com.example.tictactoe.Marker.X;

public class Player {
    //Test MAin
//    public static void main(String[] args){
//        var player = new Player();
//        var player2 = new ComputerClass();
//        var gameboard = new GameBoard();
//        System.out.println(player.marker.name());
//        System.out.println(player2.getMarker().name());
//        gameboard.placeMarker(player.makeMove("button11"));
//        System.out.println(gameboard.checkForWin());
//        var move = player2.makeMove(gameboard.getBoard());
//        System.out.print("This is the computer player marker: ");
//        System.out.println(move.marker());
//        gameboard.placeMarker(move);
//    }


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

    public MoveRecord makeMove(String buttonId) {
        return MoveRecord.of(buttonId, marker);
    }

    public void incrementScore() {
        score++;
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