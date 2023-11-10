package com.example.tictactoe;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.util.Arrays;
import static com.example.tictactoe.Marker.*;

public class GameBoard {
    private final int BOARD_SIZE = 3;
    private final SimpleBooleanProperty boardDisabled;
    private final StringProperty[][] board;
    Marker currentMarker;

    public boolean isBoardDisabled() {
        return boardDisabled.get();
    }
    public SimpleBooleanProperty boardDisabledProperty() {
        return boardDisabled;
    }
    public void setBoardDisabled(boolean boardDisabled) {
        this.boardDisabled.set(boardDisabled);
    }

    public void setCurrentMarker(Marker currentMarker) {
        this.currentMarker = currentMarker;
    }

    public GameBoard() {
        this.boardDisabled = new SimpleBooleanProperty(true);
        this.board = new SimpleStringProperty[BOARD_SIZE][BOARD_SIZE];
        this.currentMarker = X;
        initializeBoard();
    }

    public StringProperty stringProperty(int row, int col) {
        return board[row][col];
    }
    private void initializeBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = new SimpleStringProperty("");
            }
        }
    }

    public boolean isMoveValid(int row, int col) {
        return board[row][col].get().isEmpty();
    }

    public void placeMarker(MoveRecord moveRecord, String marker) {
        if (isMoveValid(moveRecord.row(), moveRecord.col())) {
            board[moveRecord.row()][moveRecord.col()].set(marker);
        }
    }
    public void placeMarker(MoveRecord moveRecord) {
        if (isMoveValid(moveRecord.row(), moveRecord.col())) {
            board[moveRecord.row()][moveRecord.col()].set(moveRecord.marker().name());
        }
    }

    public boolean checkForWin() {
        if (checkRowsAndColumns()) return disableBoard();
        else if (checkDiagonals()) return disableBoard();
        else return false;
    }

    private boolean disableBoard() {
        setBoardDisabled(true);
        return true;
    }
    private void enableBoard(){
        setBoardDisabled(false);
    }

    private boolean checkDiagonals() {
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
        return false;
    }

    private boolean checkRowsAndColumns(){
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
        return false;
    }
    public boolean checkForTie() {
        return Arrays.stream(board).flatMap(Arrays::stream).noneMatch(cell -> cell != null && cell.get().isEmpty());
    }

    public StringProperty[][] getBoard() {
        return board;
    }

    public void PrepBoard() {
        resetBoard();
        enableBoard();
    }

    private void resetBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j].set("");
            }
        }
    }

}
