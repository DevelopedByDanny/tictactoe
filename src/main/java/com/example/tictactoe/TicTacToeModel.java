package com.example.tictactoe;

import javafx.beans.property.*;

public class TicTacToeModel {
    //region Fields
    GameBoard gameBoard;
    private final Human playerOne;
    private final ComputerClass playerTwo;
    public final StringProperty welcomeText;
    private final StringProperty winner;
    private final StringProperty playerOneScoreLabel;
    private final StringProperty playerTwoScoreLabel;

    //endregion

    //region Constructors
    public TicTacToeModel() {
        gameBoard = new GameBoard();
        playerOne = new Human();
        playerTwo = new ComputerClass();
        playerOneScoreLabel = new SimpleStringProperty("Player X Score: ");
        playerTwoScoreLabel = new SimpleStringProperty("Player O Score: ");
        winner = new SimpleStringProperty("");
        welcomeText = new SimpleStringProperty("Tic Tac Toe");
    }
    //endregion

    //region Getters and Setters
    public StringProperty playerOneScoreLabelProperty() {
        return playerOneScoreLabel;
    }

    public StringProperty playerTwoScoreLabelProperty() {
        return playerTwoScoreLabel;
    }

    public StringProperty winnerProperty() {
        return winner;
    }


    public String getWinner() {
        return winner.get();
    }

    public void setWinner(String winner) {
        this.winner.set(winner);
    }

    public void setPlayerOneScoreLabel(int score) {
        this.playerOneScoreLabel.set("Player X Score: " + score);
    }

    public void setPlayerTwoScoreLabel(int score) {
        this.playerTwoScoreLabel.set("Player O Score: " + score);
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
        setWinner("");
    }

    public void endGame() {

        playerOne.setScore(0);
        playerTwo.setScore(0);
        //TODO bind the controller directly to the playerscore
        setPlayerOneScoreLabel(0);
        setPlayerTwoScoreLabel(0);
    }

    public void placeMarkerOnTheBoard(String buttonId) {
        gameBoard.placeMarker(playerOne.makeMove(buttonId));
        if (!gameBoard.checkForWin() && !gameBoard.checkForTie()) {
            gameBoard.placeMarker(playerTwo.makeMove(gameBoard.getBoard()));
            if (gameBoard.checkForWin()) {
                setPlayerTwoScoreLabel(playerTwo.incrementScore());
                setWinner("Player " + playerTwo.getMarker().name() + " wins!");
            }
        } else {
            if (gameBoard.checkForWin()) {
                setPlayerOneScoreLabel(playerOne.incrementScore());
                setWinner("Player " + playerOne.getMarker().name() + " wins!");
            }
        }

        if (gameBoard.checkForTie()) {
            setWinner("This is a Tie!");
            gameBoard.setBoardDisabled(true);
        }
    }
    //endregion
}
