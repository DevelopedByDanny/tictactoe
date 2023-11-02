package com.example.tictactoe;

import javafx.beans.property.StringProperty;

import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

import static com.example.tictactoe.GameMode.*;

public class Computer {
    public static Optional<MoveRecord> move(StringProperty[][] board, GameMode gameMode, String marker) {

        if (gameMode == EASY) {
            return Optional.of(easyMove(board));
        } else {
            return Optional.empty();
        }
    }

    private static MoveRecord easyMove(StringProperty[][] board) {
        Random random = new Random();
        // Find all empty positions
        int size = board.length;
        int emptyCount = Arrays.stream(board).mapToInt(row -> (int) Arrays.stream(row).filter(cell -> cell.get().isEmpty()).count()).sum();

        // No move can be made if the board is full
        if (emptyCount == 0) {
            return null; // or throw an exception, depending on your error handling
        }

        // Choose a random empty cell
        int moveIndex = random.nextInt(emptyCount);
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (board[row][col].get().isEmpty()) {
                    if (moveIndex == 0) {
//                        board[row][col].set(marker); // Place the player's symbol
                        return new MoveRecord(row, col); // Return the move made
                    }
                    moveIndex--;
                }
            }
        }

        throw new IllegalStateException("No empty space found, but emptyCount was not 0");
    }
//    public static void easyComputerMove(){
//
//    }

}
