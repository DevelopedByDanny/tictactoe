package com.example.tictactoe;

import javafx.beans.property.StringProperty;

import java.util.*;

import static com.example.tictactoe.GameMode.*;

public class Computer {
    public static Optional<MoveRecord> move(StringProperty[][] board, GameMode gameMode, String marker) {

        if (gameMode == EASY) {
            return Optional.of(easyMove(board));
        }
        else if (gameMode == HARD) {
            return Optional.of(miniMax(board, marker));
        } else {
            return Optional.empty();
        }
    }

    private static MoveRecord miniMax(StringProperty[][] board, String marker) {
        MoveRecord bestMove = new MoveRecord(-1, -1);

        // Base case: check if the game has ended and return the appropriate score
        if (isGameOver(board)) {
            return new MoveRecord(-1, -1, evaluateBoardForAI(board));
        }

        int bestScore = (marker.equals("O")) ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        // Iterate through all cells of the board
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                // Check if cell is empty
                if (board[row][col].get().isEmpty()) {
                    // Make move
                    board[row][col].set(marker);

                    // Call minimax recursively and choose the maximum or minimum value
                    int currentScore = miniMax(board, (marker.equals("O") ? "X" : "O")).score();

                    // Undo move
                    board[row][col].set("");

                    // Update the best score and best move
                    if (marker.equals("O") && currentScore > bestScore) {
                        bestScore = currentScore;
                        bestMove = new MoveRecord(row, col, bestScore);
                    } else if (!marker.equals("O") && currentScore < bestScore) {
                        bestScore = currentScore;
                        bestMove = new MoveRecord(row, col, bestScore);
                    }
                }
            }
        }
        return bestMove;
    }

    public static int evaluateBoardForAI(StringProperty[][] board) {
        // Check all rows, columns, and diagonals for a win
        for (int i = 0; i < 3; i++) {
            // Check rows
            if (board[i][0].get().equals(board[i][1].get()) &&
                    board[i][1].get().equals(board[i][2].get())) {
                if (board[i][0].get().equals("O")) {
                    return 10;
                } else if (board[i][0].get().equals("X")) {
                    return -10;
                }
            }

            // Check columns
            if (board[0][i].get().equals(board[1][i].get()) &&
                    board[1][i].get().equals(board[2][i].get())) {
                if (board[0][i].get().equals("O")) {
                    return 10;
                } else if (board[0][i].get().equals("X")) {
                    return -10;
                }
            }
        }

        // Check diagonals
        if (board[0][0].get().equals(board[1][1].get()) &&
                board[1][1].get().equals(board[2][2].get())) {
            if (board[0][0].get().equals("O")) {
                return 10;
            } else if (board[0][0].get().equals("X")) {
                return -10;
            }
        }
        if (board[0][2].get().equals(board[1][1].get()) &&
                board[1][1].get().equals(board[2][0].get())) {
            if (board[0][2].get().equals("O")) {
                return 10;
            } else if (board[0][2].get().equals("X")) {
                return -10;
            }
        }

        // If nobody has won, return 0
        return 0;
    }

    public static boolean isGameOver(StringProperty[][] board) {
        // Check for win
        for (int i = 0; i < 3; i++) {
            // Check rows and columns
            if (!board[i][0].get().isEmpty() &&
                    board[i][0].get().equals(board[i][1].get()) &&
                    board[i][0].get().equals(board[i][2].get())) {
                return true; // There's a win in a row.
            }

            if (!board[0][i].get().isEmpty() &&
                    board[0][i].get().equals(board[1][i].get()) &&
                    board[0][i].get().equals(board[2][i].get())) {
                return true; // There's a win in a column.
            }
        }

        // Check diagonals
        if (!board[0][0].get().isEmpty() &&
                board[0][0].get().equals(board[1][1].get()) &&
                board[0][0].get().equals(board[2][2].get())) {
            return true; // There's a win in the main diagonal.
        }

        if (!board[0][2].get().isEmpty() &&
                board[0][2].get().equals(board[1][1].get()) &&
                board[0][2].get().equals(board[2][0].get())) {
            return true; // There's a win in the secondary diagonal.
        }

        // Check for draw by looking for any empty cells
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col].get().isEmpty()) {
                    // At least one cell is empty, so not a draw, game can continue
                    return false;
                }
            }
        }

        // If we reach this point, there are no wins and no empty spaces, so it's a draw
        return true;
    }


//    private static MoveRecord miniMax(StringProperty[][] board, String marker) {
//        var bestMove = new MoveRecord(-1, -1);
//
//        int bestScore;
//        if (marker.equals("O")) { // If AI
//            bestScore = Integer.MIN_VALUE;
//        } else {
//            bestScore = Integer.MAX_VALUE;
//        }
//
//        // Iterate through all cells of the board
//        for (int row = 0; row < board.length; row++) {
//            for (int col = 0; col < board[row].length; col++) {
//                // Check if cell is empty
//                if (board[row][col].get().isEmpty()) {
//                    // Make move
//                    board[row][col].set(marker);
//
//                    // Call minimax recursively and choose the maximum or minimum value
//                    int currentScore;
//                    if (marker.equals("O")) {
//                        currentScore = miniMax(board, "X").score();
//                    } else {
//                        currentScore = miniMax(board, "O").score();
//                    }
//
//                    // Undo move
//                    board[row][col].set("");
//
//                    // Update the best score and best move
//                    if (marker.equals("O") && currentScore > bestScore) {
//                        bestScore = currentScore;
//                        bestMove = new MoveRecord(row, col, bestScore);
//                    } else if (!marker.equals("O") && currentScore < bestScore) {
//                        bestScore = currentScore;
//                        bestMove = new MoveRecord(row, col, bestScore);
//                    }
//                }
//            }
//        }
//        return bestMove;
//    }

    private static MoveRecord easyMove(StringProperty[][] board) {
        Random random = new Random();
        List<MoveRecord> emptySpots = new ArrayList<>();

        // Collect all empty spots
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col].get().isEmpty()) {
                    emptySpots.add(new MoveRecord(row, col));
                }
            }
        }

        // Check if there are any empty spots left
        if (emptySpots.isEmpty()) {
            return null;
        }

        return emptySpots.get(random.nextInt(emptySpots.size())); // Return the chosen move
    }

}


