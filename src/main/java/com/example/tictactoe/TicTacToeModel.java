package com.example.tictactoe;

import javafx.beans.property.*;

import static com.example.tictactoe.Marker.*;

public class TicTacToeModel {
    public final StringProperty welcomeText;
    private final StringProperty winner;
    private StringProperty playerTurn;
    private StringProperty score;
    private final StringProperty[][] board;
    private boolean xTurn;
    private Marker marker;

    public boolean isBoardDisabled() {
        return boardDisabled.get();
    }

    public SimpleBooleanProperty boardDisabledProperty() {
        return boardDisabled;
    }

    public void setBoardDisabled(boolean boardDisabled) {
        this.boardDisabled.set(boardDisabled);
    }

    private SimpleBooleanProperty boardDisabled = new SimpleBooleanProperty(this, "boardDisabled");


    public TicTacToeModel() {
        marker = X;
        playerTurn = new SimpleStringProperty();
        score = new SimpleStringProperty();
        winner = new SimpleStringProperty("");
        xTurn = true;
        boardDisabled.set(true);
        board = new StringProperty[3][3];
        welcomeText = new SimpleStringProperty("Tic Tac Toe");
        setBoard();
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

    public void setxTurn(boolean xTurn) {
        this.xTurn = xTurn;
    }

    public StringProperty winnerProperty() {
        return winner;
    }

    public String getWinner() {
        return winner.get();
    }

    public void setWinner(String winner) {
        this.winner.set("Player " + winner + " wins!");
    }

    public void resetWinner() {
        this.winner.set("");
    }

    public boolean isxTurn() {
        return xTurn;
    }

    public String toggleTurn() {
        this.xTurn = !xTurn;
        return isxTurn() ? "O" : "X";
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

    private void setBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = new SimpleStringProperty("");
            }
        }
    }

    public StringProperty stringProperty(int row, int col) {
        return board[row][col];
    }

    public void setBoard(int row, int col) {
        board[row][col].set("X");
    }

    public boolean checkForWin() {
        for (int i = 0; i < 3; i++) {
            // Check rows
            if (!board[i][0].get().isEmpty() && board[i][0].get().equals(board[i][1].get()) && board[i][1].get().equals(board[i][2].get())) {
                setWinner(board[i][0].get());
                return true;
            }
            // Check columns
            if (!board[0][i].get().isEmpty() && board[0][i].get().equals(board[1][i].get()) && board[1][i].get().equals(board[2][i].get())) {
                setWinner(board[0][i].get());
                return true;
            }
        }
        // Check diagonals
        if (!board[0][0].get().isEmpty() && board[0][0].get().equals(board[1][1].get()) && board[1][1].get().equals(board[2][2].get())
                || !board[0][2].get().isEmpty() && board[0][2].get().equals(board[1][1].get()) && board[1][1].get().equals(board[2][0].get())) {
            setWinner(board[1][1].get());
            return true;
        }
        return false;
    }

    public void startGame() {
        resetWinner();
        resetBoard();

        xTurn = true;
        boardDisabled.set(false);


    }

    public void playVsHuman() {
        startGame();
    }

    public void placeMarkerOnTheBoard(String buttonId) {
        var row = Integer.parseInt(buttonId.substring(6, 7));
        var col = Integer.parseInt(buttonId.substring(7));
//        board[row][col].set(toggleTurn());
        board[row][col].set(getMarker());
        toggleMarker();

        boardDisabled.set(checkForWin());
    }

    private void toggleMarker() {
        if (marker.equals(X)) marker = O;
        else marker = X;
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j].set("");
            }
        }
    }
}

record BoardUpdate(String marker, int row, int col) {
}