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
    private GameMode gameMode;
    private final StringProperty[][] board;
    private final SimpleBooleanProperty boardDisabled;
    private Marker marker;
    GameBoard gameBoard;

    //endregion

    public TicTacToeModel() {
        gameBoard = new GameBoard();
        gameMode = VS;
        marker = X;
        playerTurn = new SimpleStringProperty("Player " + getMarker() + " turn");
        score = new SimpleStringProperty();
        winner = new SimpleStringProperty("");
        boardDisabled = new SimpleBooleanProperty(true);
        board = new StringProperty[3][3];
        welcomeText = new SimpleStringProperty("Tic Tac Toe");
        setBoard();
        playerOne = new Player();
        playerTwo = new ComputerClass();
    }

    //region Getters and Setters
    public boolean isBoardDisabled() {
        return boardDisabled.get();
    }

    public void setModeToHuman() {
        gameMode = VS;
    }

    public SimpleBooleanProperty boardDisabledProperty() {
        return boardDisabled;
    }

    public void setBoardDisabled(boolean boardDisabled) {
        this.boardDisabled.set(boardDisabled);
    }


    public String getPlayerTurn() {
        return playerTurn.get();
    }

    public StringProperty playerTurnProperty() {
        return playerTurn;
    }

    public void setPlayerTurn(String playerTurn) {
        this.playerTurn.set(playerTurn);
    }

    public String getScore() {
        return score.get();
    }

    public StringProperty scoreProperty() {
        return score;
    }

    public void setScore(String score) {
        this.score.set(score);
    }

    public StringProperty winnerProperty() {
        return winner;
    }

    public String getWinner() {
        return winner.get();
    }

    public void setResult(String result) {
        if (result.equals("Tied")) winner.set("Tied");
        else winner.set("Player " + result + " wins!");
    }

    public void resetWinner() {
        this.winner.set("");
    }

    public String getMarker() {
        return marker.toString();
    }

    public String getWelcomeText() {
        return welcomeText.get();
    }

    public StringProperty welcomeTextProperty() {
        return welcomeText;
    }

    public void setWelcomeText(String welcomeText) {
        this.welcomeText.set(welcomeText);

    }

    public StringProperty[][] getBoard() {
        return board;
    }

    public StringProperty stringProperty(int row, int col) {
        return board[row][col];
    }
    //endregion

    private void setBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = new SimpleStringProperty("");
            }
        }
    }


    //region Methods
    public boolean checkForWin() {
        for (int i = 0; i < 3; i++) {
            // Check rows
            if (!board[i][0].get().isEmpty() && board[i][0].get().equals(board[i][1].get()) && board[i][1].get().equals(board[i][2].get())) {
                setResult(board[i][0].get());
                return true;
            }
            // Check columns
            if (!board[0][i].get().isEmpty() && board[0][i].get().equals(board[1][i].get()) && board[1][i].get().equals(board[2][i].get())) {
                setResult(board[0][i].get());
                return true;
            }
        }
        // Check diagonals
        if (!board[0][0].get().isEmpty() && board[0][0].get().equals(board[1][1].get()) && board[1][1].get().equals(board[2][2].get())
                || !board[0][2].get().isEmpty() && board[0][2].get().equals(board[1][1].get()) && board[1][1].get().equals(board[2][0].get())) {
            setResult(board[1][1].get());
            return true;
        }
        if (checkForTie()) {
            setResult("Tied");
            return true;
        }
        return false;
    }

    private boolean checkForTie() {
        return Arrays.stream(board).flatMap(Arrays::stream).noneMatch(cell -> cell != null && cell.get().isEmpty());
    }

    public void startGame() {
        resetWinner();
        gameBoard.resetBoard();
    }

    public void endGame() {


    }

    public void setModeToEasy() {
        gameMode = EASY;
    }

    public void setModeToHard() {
        gameMode = HARD;
    }

    public void placeMarkerOnTheBoard(String buttonId) {

        //new game-board logic
        gameBoard.placeMarker(playerOne.makeMove(buttonId));

        if (!gameBoard.checkForWin()) {
            gameBoard.placeMarker(playerTwo.makeMove( gameBoard.getBoard()));
        }

        gameBoard.setBoardDisabled(gameBoard.checkForWin());
        boardDisabled.set(gameBoard.checkForWin());


//        var row = Integer.parseInt(buttonId.substring(6, 7));
//        var col = Integer.parseInt(buttonId.substring(7));
//        board[row][col].set(getAndThenToggleMarker());
//
//        if (!checkForWin()) {
//            var move = Computer.move(board, gameMode, getMarker());
//            move.ifPresent(moveRecord -> board[moveRecord.row()][moveRecord.col()].set(getAndThenToggleMarker()));
//        }
//        boardDisabled.set(checkForWin());
    }

    private String getAndThenToggleMarker() {
        var currentMarker = getMarker();
        if (marker.equals(X)) marker = O;
        else marker = X;
        return currentMarker;
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            board[i][0].set("");
            board[i][1].set("");
            board[i][2].set("");
        }
    }
    //endregion
}
