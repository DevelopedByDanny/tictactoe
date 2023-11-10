package com.example.tictactoe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameBoardTest {

    private GameBoard gameBoard;

    @BeforeEach
    public void setUp() {
        gameBoard = new GameBoard();
    }

    @Test
    public void testBoardInitialization() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals("", gameBoard.stringProperty(i, j).get(), "Board should initialize with empty cells");
            }
        }
    }

    @Test
    public void testValidMove() {
        MoveRecord move = new MoveRecord(0, 0);
        gameBoard.placeMarker(move, "X");
        assertEquals("X", gameBoard.stringProperty(0, 0).get(), "Valid move should place marker");
    }

    @Test
    public void testInvalidMove() {
        gameBoard.placeMarker(new MoveRecord(0, 0), "X");
        gameBoard.placeMarker(new MoveRecord(0, 0), "O");
        assertNotEquals("O", gameBoard.stringProperty(0, 0).get(), "Invalid move should not replace existing marker");
    }

    @Test
    public void testInvalidMoveInverted() {
        gameBoard.placeMarker(new MoveRecord(2, 2), "O");
        gameBoard.placeMarker(new MoveRecord(2, 2), "X");
        assertNotEquals("O", gameBoard.stringProperty(0, 0).get(), "Invalid move should not replace existing marker");
    }

    @Test
    public void testHorizontalWinTopRow() {
        gameBoard.placeMarker(new MoveRecord(0, 0), "O");
        gameBoard.placeMarker(new MoveRecord(0, 1), "O");
        gameBoard.placeMarker(new MoveRecord(0, 2), "O");
        assertTrue(gameBoard.checkForWin(), "Should return true for a horizontal win in the top row");
    }

    @Test
    public void testHorizontalWinMiddleRow() {
        gameBoard.placeMarker(new MoveRecord(1, 0), "X");
        gameBoard.placeMarker(new MoveRecord(1, 1), "X");
        gameBoard.placeMarker(new MoveRecord(1, 2), "X");
        assertTrue(gameBoard.checkForWin(), "Should return true for a horizontal win in the middle row");
    }

    @Test
    public void testHorizontalWinBottomRow() {
        gameBoard.placeMarker(new MoveRecord(2, 0), "X");
        gameBoard.placeMarker(new MoveRecord(2, 1), "X");
        gameBoard.placeMarker(new MoveRecord(2, 2), "X");
        assertTrue(gameBoard.checkForWin(), "Should return true for a horizontal win in the bottom row");
    }

    @Test
    public void testVerticalWinLeftColumn() {
        gameBoard.placeMarker(new MoveRecord(0, 0), "X");
        gameBoard.placeMarker(new MoveRecord(1, 0), "X");
        gameBoard.placeMarker(new MoveRecord(2, 0), "X");
        assertTrue(gameBoard.checkForWin(), "Should return true for a vertical win in the left column");
    }

    @Test
    public void testVerticalWinMiddleColumn() {
        gameBoard.placeMarker(new MoveRecord(0, 1), "X");
        gameBoard.placeMarker(new MoveRecord(1, 1), "X");
        gameBoard.placeMarker(new MoveRecord(2, 1), "X");
        assertTrue(gameBoard.checkForWin(), "Should return true for a vertical win in the middle column");
    }

    @Test
    public void testVerticalWinRightColumn() {
        gameBoard.placeMarker(new MoveRecord(0, 2), "X");
        gameBoard.placeMarker(new MoveRecord(1, 2), "X");
        gameBoard.placeMarker(new MoveRecord(2, 2), "X");
        assertTrue(gameBoard.checkForWin(), "Should return true for a vertical win in the right column");
    }

    @Test
    public void testCheckForTie() {
        gameBoard.placeMarker(new MoveRecord(0, 0), "X");
        gameBoard.placeMarker(new MoveRecord(0, 1), "O");
        gameBoard.placeMarker(new MoveRecord(0, 2), "X");

        gameBoard.placeMarker(new MoveRecord(1, 0), "O");
        gameBoard.placeMarker(new MoveRecord(1, 1), "X");
        gameBoard.placeMarker(new MoveRecord(1, 2), "O");

        gameBoard.placeMarker(new MoveRecord(2, 0), "O");
        gameBoard.placeMarker(new MoveRecord(2, 1), "X");
        gameBoard.placeMarker(new MoveRecord(2, 2), "X");
        assertTrue(gameBoard.checkForTie(), "Should return true for a tie");
    }

    @Test
    public void testDiagonalWinSecondary() {
        gameBoard.placeMarker(new MoveRecord(0, 2), "X");
        gameBoard.placeMarker(new MoveRecord(1, 1), "X");
        gameBoard.placeMarker(new MoveRecord(2, 0), "X");
        assertTrue(gameBoard.checkForWin(), "Should return true for a diagonal win on the secondary diagonal");
    }

    @Test
    public void testBoardReset() {
        gameBoard.placeMarker(new MoveRecord(0, 0), "X");
        gameBoard.PrepBoard();
        assertEquals("", gameBoard.stringProperty(0, 0).get(), "Reset board should clear all markers");
        assertFalse(gameBoard.isBoardDisabled(), "Board should be enabled after reset");
    }

}
