package com.example.finalprojectavcjava;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.HashMap;

public class LoginPage {

    private Stage stage;
    private TextField userIDField;
    private PasswordField userPasswordField;
    private Label userIDLabel;
    private Label userPasswordLabel;
    private Label messageLabel;
    private HashMap<String, String> logininfo;

    // Konstruktorn för LoginPage
    public LoginPage(HashMap<String, String> loginInfoOriginal) {
        logininfo = loginInfoOriginal;

        // Skapar en ny scen med ett rutnätslayout
        stage = new Stage();
        stage.setTitle("Login");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20, 20, 20, 20));
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

        // Lägger till element i rutnätet
        gridPane.add(userIDLabel, 0, 0);
        gridPane.add(userPasswordLabel, 0, 1);
        gridPane.add(userIDField, 1, 0);
        gridPane.add(userPasswordField, 1, 1);
        gridPane.add(loginButton, 1, 2);
        gridPane.add(resetButton, 2, 2);
        gridPane.add(messageLabel, 1, 3);

        // Skapar en scen
        Scene scene = new Scene(gridPane, 400, 200);
        stage.setScene(scene);
    }

    // Hanterar inloggningen när Login-knappen klickas
    private void handleLogin() {
        String userID = userIDField.getText();
        String password = userPasswordField.getText();

        if (logininfo.containsKey(userID)) {
            if (logininfo.get(userID).equals(password)) {
                // Visa meddelande om lyckad inloggning
                messageLabel.setTextFill(Color.GREEN);
                messageLabel.setText("Login successful");

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
        stage.showAndWait();

        // Returnerar sant när inloggningen är klar
        return true;
    }

    // Hämtar användar-ID från inloggningsdialogen
    public String getUserID() {
        return null;
    }
}
