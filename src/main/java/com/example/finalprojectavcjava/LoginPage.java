package com.example.finalprojectavcjava;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.HashMap;

public class LoginPage extends Stage {

    private Stage stage;
    private TextField userIDField;
    private PasswordField userPasswordField;
    private Label userIDLabel;
    private Label userPasswordLabel;
    private Label messageLabel;
    private HashMap<String, String> logininfo;
    private boolean loginSuccess; // New variable to track login status

    // Konstruktorn för LoginPage
    public LoginPage(HashMap<String, String> loginInfoOriginal) {
        logininfo = loginInfoOriginal;

        // Skapar en ny scen med ett rutnätslayout
        stage = new Stage();
        stage.setTitle("Login");
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        // Skapar och konfigurerar användargränssnittselement
        userIDLabel = new Label("userID:");
        userPasswordLabel = new Label("password:");
        messageLabel = new Label();

        userIDField = new TextField();
        userPasswordField = new PasswordField();

        Button loginButton = new Button("Login");
        Button resetButton = new Button("Reset");
        loginButton.setStyle("-fx-background-color: #67729D; -fx-text-fill: #FFF");
        resetButton.setStyle("-fx-background-color: #67729D; -fx-text-fill: #FFF");

        // Anger åtgärder för knapparna när de klickas
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleLogin();
            }
        });

        resetButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                userIDField.clear();
                userPasswordField.clear();
            }
        });

        // Lägger till element i rutnätet och sätter marginaler
        GridPane.setMargin(userIDLabel, new Insets(0, 0, 0, 10));
        GridPane.setMargin(userPasswordLabel, new Insets(0, 0, 0, 10));
        GridPane.setMargin(userIDField, new Insets(0, 0, 0, 10));
        GridPane.setMargin(userPasswordField, new Insets(0, 0, 0, 10));

        gridPane.add(userIDLabel, 0, 0);
        gridPane.add(userPasswordLabel, 0, 1);
        gridPane.add(userIDField, 1, 0);
        gridPane.add(userPasswordField, 1, 1);

        // Lägger till knapparna i en HBox och sätter marginaler
        HBox buttonBox = new HBox(5);
        buttonBox.getChildren().addAll(loginButton, resetButton);
        GridPane.setMargin(buttonBox, new Insets(0, 0, 0, 10));

        gridPane.add(buttonBox, 1, 2);
        gridPane.add(messageLabel, 1, 3);
        gridPane.setStyle("-fx-background-color: CEDEBD; -fx-text-fill: #FFF");

        // Lägg till KeyEvent-lyssnare för Enter-tangenten på userIDField och userPasswordField
        userIDField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    handleLogin();
                }
            }
        });

        userPasswordField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    handleLogin();
                }
            }
        });

        // Skapar en scen
        Scene scene = new Scene(gridPane, 400, 200);
        stage.setScene(scene);
    }

    // Hanterar inloggningen när Login-knappen klickas eller Enter trycks
    private void handleLogin() {
        String userID = userIDField.getText();
        String password = userPasswordField.getText();

        if (logininfo.containsKey(userID)) {
            if (logininfo.get(userID).equals(password)) {
                // Visa meddelande om lyckad inloggning
                messageLabel.setTextFill(Color.GREEN);
                messageLabel.setText("Login successful");

                // Set the loginSuccess variable to true
                loginSuccess = true;

                // Stänger login-fönstret
                Platform.runLater(() -> {
                    stage.close();
                });
            } else {
                // Visar meddelande om felaktigt lösenord
                messageLabel.setTextFill(Color.RED);
                messageLabel.setText("Wrong password");
            }
        } else {
            // Visar meddelande om användarnamn inte hittas
            messageLabel.setTextFill(Color.RED);
            messageLabel.setText("Username not found");
        }
    }

    // Visar inloggningsdialogen och väntar på dess stängning
    public boolean showLoginDialog() {
        // Show the login dialog and wait for its closure
        stage.showAndWait();

        // Check if the login was successful
        return loginSuccess;
    }

    // Hämtar användar-ID från inloggningsdialogen
    public String getUserID() {
        return userIDField.getText();
    }
}
