package com.example.tictactoe;

import javafx.beans.property.*;

import java.util.Arrays;

import static com.example.tictactoe.GameMode.*;
import static com.example.tictactoe.Marker.*;

public class TicTacToeModel {
    //region Fields
    public final StringProperty welcomeText;
    private final Player playerOne;
    private final ComputerClass playerTwo;
    private final StringProperty winner;
    private final StringProperty playerTurn;
    private final StringProperty score;
    GameBoard gameBoard;

    //endregion

    public TicTacToeModel() {
        gameBoard = new GameBoard();
        playerOne = new Player();
        playerTwo = new ComputerClass();
        playerTurn = new SimpleStringProperty("Player X turn");
        score = new SimpleStringProperty();
        winner = new SimpleStringProperty("");
        welcomeText = new SimpleStringProperty("Tic Tac Toe");
    }

    //region Getters and Setters



    public StringProperty playerTurnProperty() {
        return playerTurn;
    }



    public StringProperty scoreProperty() {
        return score;
    }


    public StringProperty winnerProperty() {
        return winner;
    }


    public void setResult(String result) {
        if (result.equals("Tied")) winner.set("Tied");
        else winner.set("Player " + result + " wins!");
    }

    public StringProperty welcomeTextProperty() {
        return welcomeText;
    }

    public void setWelcomeText(String welcomeText) {
        this.welcomeText.set(welcomeText);
    }


    //endregion




    //region Methods
    public void startGame() {
        gameBoard.PrepBoard();
    }

    public void endGame() {


    }
    public void placeMarkerOnTheBoard(String buttonId) {

        //new game-board logic
        gameBoard.placeMarker(playerOne.makeMove(buttonId));

        if (!gameBoard.isGameOver()) {
            gameBoard.placeMarker(playerTwo.makeMove(gameBoard.getBoard()));
        }

        gameBoard.setBoardDisabled(gameBoard.isGameOver());

    }

    //endregion
}
