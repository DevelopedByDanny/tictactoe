package com.example.tictactoe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class TicTacToeController {
    @FXML
    private Label welcomeLabel;
    public Label winner;
    public Label playerTurn;
    public Label score;
    public Label turnLabel;

    private final TicTacToeModel gameModel = new TicTacToeModel();
    @FXML
    private Button
            button00, button01, button02,
            button10, button11, button12,
            button20, button21, button22;

    public TicTacToeModel getGameModel() {
        return gameModel;
    }

    public void initialize() {
        welcomeLabel.textProperty().bindBidirectional(gameModel.welcomeTextProperty());
        winner.textProperty().bindBidirectional(gameModel.winnerProperty());
        playerTurn.textProperty().bindBidirectional(gameModel.playerTurnProperty());
        score.textProperty().bindBidirectional(gameModel.scoreProperty());

        button00.disableProperty().bindBidirectional(gameModel.boardDisabledProperty());
        button01.disableProperty().bindBidirectional(gameModel.boardDisabledProperty());
        button02.disableProperty().bindBidirectional(gameModel.boardDisabledProperty());
        button10.disableProperty().bindBidirectional(gameModel.boardDisabledProperty());
        button11.disableProperty().bindBidirectional(gameModel.boardDisabledProperty());
        button12.disableProperty().bindBidirectional(gameModel.boardDisabledProperty());
        button20.disableProperty().bindBidirectional(gameModel.boardDisabledProperty());
        button21.disableProperty().bindBidirectional(gameModel.boardDisabledProperty());
        button22.disableProperty().bindBidirectional(gameModel.boardDisabledProperty());

        button00.textProperty().bindBidirectional(gameModel.stringProperty(0, 0));
        button01.textProperty().bindBidirectional(gameModel.stringProperty(0, 1));
        button02.textProperty().bindBidirectional(gameModel.stringProperty(0, 2));
        button10.textProperty().bindBidirectional(gameModel.stringProperty(1, 0));
        button11.textProperty().bindBidirectional(gameModel.stringProperty(1, 1));
        button12.textProperty().bindBidirectional(gameModel.stringProperty(1, 2));
        button20.textProperty().bindBidirectional(gameModel.stringProperty(2, 0));
        button21.textProperty().bindBidirectional(gameModel.stringProperty(2, 1));
        button22.textProperty().bindBidirectional(gameModel.stringProperty(2, 2));
    }

    public void onButtonAction(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        if (button.getText().isEmpty()) {
            gameModel.placeMarkerOnTheBoard(button.getId());
        }

//        if (button.getText().isEmpty()) {
//            button.setText(gameModel.isxTurn() ? "X" : "O");
//            gameModel.toggleTurn();
//        }
//        gameModel.checkForWin();
    }

    public void onClickStartGame(ActionEvent actionEvent) {
        gameModel.playVsHuman();
    }

    public void onPlayEasyComputer(ActionEvent actionEvent) {
    }

    public void onPlayHardComputer(ActionEvent actionEvent) {
    }
}