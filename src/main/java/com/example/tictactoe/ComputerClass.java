package com.example.tictactoe;
import javafx.beans.property.StringProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import static com.example.tictactoe.GameMode.EASY;
import static com.example.tictactoe.GameMode.HARD;
import static com.example.tictactoe.Marker.O;

public class ComputerClass extends Player {
    private final GameMode gameMode;

    public ComputerClass() {
        super(O);
        this.gameMode = EASY;
    }


    public MoveRecord makeMove(StringProperty[][] board) {
        if (this.gameMode == EASY) {
            return easyMove(board);
        } else return miniMax(board, this.getMarker().name());
    }

    private static MoveRecord miniMax(StringProperty[][] board, String aiMarker) {
        // Determine the current marker based on the state of the board
        String currentMarker = determineCurrentMarker(board, aiMarker);

        // Base case: check if the game has ended and return the appropriate score
        if (isGameOver(board)) {
            return new MoveRecord(-1, -1, evaluateBoardForAI(board, aiMarker));
        }

        int bestScore = (currentMarker.equals(aiMarker)) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        MoveRecord bestMove = new MoveRecord(-1, -1);

        // Iterate through all cells of the board
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                // Check if cell is empty
                if (board[row][col].get().isEmpty()) {
                    // Make move
                    board[row][col].set(currentMarker);

                    // Call minimax recursively and choose the maximum or minimum value
                    int currentScore = miniMax(board, aiMarker).score();

                    // Undo move
                    board[row][col].set("");

                    // Update the best score and best move
                    if (currentMarker.equals(aiMarker) && currentScore > bestScore) {
                        bestScore = currentScore;
                        bestMove = new MoveRecord(row, col, bestScore);
                    } else if (!currentMarker.equals(aiMarker) && currentScore < bestScore) {
                        bestScore = currentScore;
                        bestMove = new MoveRecord(row, col, bestScore);
                    }
                }
            }
        }

        return bestMove;
    }

    private static String determineCurrentMarker(StringProperty[][] board, String aiMarker) {
        int countX = 0;
        int countO = 0;

        // Count markers on the board
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].get().equals("X")) countX++;
                else if (board[i][j].get().equals("O")) countO++;
            }
        }

        // Determine current marker
        if (countX > countO) {
            return "O";
        } else {
            return "X";
        }
    }


    private static int evaluateBoardForAI(StringProperty[][] board, String aiMarker) {
        int aiWinScore = 10;
        int opponentWinScore = -10;

        // Check all rows, columns, and diagonals for a win
        for (int i = 0; i < 3; i++) {
            // Check rows
            if (!board[i][0].get().isEmpty() &&
                    board[i][0].get().equals(board[i][1].get()) &&
                    board[i][1].get().equals(board[i][2].get())) {
                return board[i][0].get().equals(aiMarker) ? aiWinScore : opponentWinScore;
            }

            // Check columns
            if (!board[0][i].get().isEmpty() &&
                    board[0][i].get().equals(board[1][i].get()) &&
                    board[1][i].get().equals(board[2][i].get())) {
                return board[0][i].get().equals(aiMarker) ? aiWinScore : opponentWinScore;
            }
        }

        // Check diagonals
        if (!board[0][0].get().isEmpty() &&
                board[0][0].get().equals(board[1][1].get()) &&
                board[1][1].get().equals(board[2][2].get())) {
            return board[0][0].get().equals(aiMarker) ? aiWinScore : opponentWinScore;
        }
        if (!board[0][2].get().isEmpty() &&
                board[0][2].get().equals(board[1][1].get()) &&
                board[1][1].get().equals(board[2][0].get())) {
            return board[0][2].get().equals(aiMarker) ? aiWinScore : opponentWinScore;
        }

        // If nobody has won, return 0
        return 0;
    }

    private static boolean isGameOver(StringProperty[][] board) {
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


    private MoveRecord easyMove(StringProperty[][] board) {
        Random random = new Random();
        List<MoveRecord> emptySpots = new ArrayList<>();

        // Collect all empty spots
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col].get().isEmpty()) {
                    emptySpots.add(new MoveRecord(row, col, this.getMarker()));
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
