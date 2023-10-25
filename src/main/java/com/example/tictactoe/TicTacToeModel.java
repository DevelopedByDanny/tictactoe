package com.example.tictactoe;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TicTacToeModel {
    private StringProperty welcomeText;

    public String getDisplayWinner() {
        return displayWinner.get();
    }

    public StringProperty displayWinnerProperty() {
        return displayWinner;
    }

    public void setDisplayWinner(String displayWinner) {
        this.displayWinner.set("Player " + displayWinner + " wins!");
    }

    public void resetWinner(){
        this.displayWinner.set("");
    }
    private StringProperty displayWinner;
    private boolean xTurn;

    public boolean isxTurn() {
        return xTurn;
    }

    public void toggleTurn() {
        this.xTurn = !xTurn;
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

    private final StringProperty[][] board;

    public TicTacToeModel() {
        displayWinner = new SimpleStringProperty();
        xTurn = true;
        board = new StringProperty[3][3];
        welcomeText = new SimpleStringProperty("Tic Tac Toe");
        setBoard();
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

    public String checkForWin() {
        for (int i = 0; i < 3; i++) {
            // Check rows
            if (!board[i][0].get().isEmpty() && board[i][0].get().equals(board[i][1].get()) && board[i][1].get().equals(board[i][2].get())) {
                setDisplayWinner(board[i][0].get());
                return board[i][0].get();
            }
            // Check columns
            if (!board[0][i].get().isEmpty() && board[0][i].get().equals(board[1][i].get()) && board[1][i].get().equals(board[2][i].get())) {
                setDisplayWinner(board[0][i].get());
                return board[0][i].get();
            }
        }
        // Check diagonals
        if (!board[0][0].get().isEmpty() && board[0][0].get().equals(board[1][1].get()) && board[1][1].get().equals(board[2][2].get())
                || !board[0][2].get().isEmpty() && board[0][2].get().equals(board[1][1].get()) && board[1][1].get().equals(board[2][0].get())) {
            setDisplayWinner(board[1][1].get());
            return board[1][1].get();
        }
        return null;  // Return null if no winner
    }

    public void startGame() {
        resetBoard();
        xTurn = true;
        resetWinner();
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j].set("");
            }
        }
    }
}