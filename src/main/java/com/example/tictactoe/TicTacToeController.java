package com.example.tictactoe;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class HelloController {

    public Button button1;

    public Label welcomeText;
    public Label anotherLabel;

    private Model model = new Model();

    public Model getModel(){
        return model;
    }

    public void initialize() {
        welcomeText.textProperty().bind(model.messageProperty());
    }

    @FXML
    protected void onHelloButtonAction() {
        model.setMessage("Welcome to JavaFX Application!");
    }

    public void onLabelClicked(MouseEvent mouseEvent) {

    }
}