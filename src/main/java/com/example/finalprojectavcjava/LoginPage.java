package com.example.finalprojectavcjava;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.HashMap;

// LoginPage är en subklass av Stage, vilket gör den till ett fönster i JavaFX
public class LoginPage extends Stage {

    // Instansvariabler för att hantera användargränssnittselement
    private Stage stage;
    private TextField userIDField;
    private PasswordField userPasswordField;
    private Label userIDLabel;
    private Label userPasswordLabel;
    private Label messageLabel;
    private HashMap<String, String> logininfo;
    private boolean loginSuccess;

    // Konstruktorn för LoginPage
    public LoginPage(HashMap<String, String> loginInfoOriginal) {
        logininfo = loginInfoOriginal;

        // Skapar ett nytt scenobjekt och konfigurerar layouten med ett rutnät
        stage = new Stage();
        stage.setTitle("Logga in");
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        // Skapar och konfigurerar användargränssnittselement
        userIDLabel = new Label("Användarnamn:");
        userPasswordLabel = new Label("Lösenord:");
        messageLabel = new Label();

        userIDField = new TextField();
        userPasswordField = new PasswordField();

        Button loginButton = new Button("Logga in");
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
        GridPane.setMargin(userIDLabel, new Insets(0, 0, 0, 10));
        GridPane.setMargin(userPasswordLabel, new Insets(0, 0, 0, 10));
        GridPane.setMargin(userIDField, new Insets(0, 0, 0, 10));
        GridPane.setMargin(userPasswordField, new Insets(0, 0, 0, 10));

        gridPane.add(userIDLabel, 0, 0);
        gridPane.add(userPasswordLabel, 0, 1);
        gridPane.add(userIDField, 1, 0);
        gridPane.add(userPasswordField, 1, 1);

        // Lägger till knapparna i en HBox
        HBox buttonBox = new HBox(5);
        buttonBox.getChildren().addAll(loginButton, resetButton);
        GridPane.setMargin(buttonBox, new Insets(0, 0, 0, 10));

        gridPane.add(buttonBox, 1, 2);
        gridPane.add(messageLabel, 1, 3);

        // Lägger till KeyEvent-lyssnare för Enter-tangenten på userIDField och userPasswordField
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

        // Skapar en scen med rutnätet och sätter storlek
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
                messageLabel.setText("Lyckad inloggning!");

                // Sätt variabeln loginSuccess till true
                loginSuccess = true;

                // Stäng login-fönstret
                Platform.runLater(() -> {
                    stage.close();
                });
            } else {
                // Visa meddelande om felaktigt lösenord
                messageLabel.setTextFill(Color.RED);
                messageLabel.setText("Felaktigt lösenord");
            }
        } else {
            // Visa meddelande om användarnamn inte hittas
            messageLabel.setTextFill(Color.RED);
            messageLabel.setText("Kan ej hitta användare");
        }
    }

    // Visar inloggningsdialogen och väntar på dess stängning
    public boolean showLoginDialog() {
        // Visa inloggningsdialogen och vänta på dess stängning
        stage.showAndWait();

        // Kontrollerar om inloggningen var framgångsrik
        return loginSuccess;
    }

    // Hämtar användar-ID från inloggningsdialog
    public String getUserID() {
        return userIDField.getText();
    }

    public Parent getGridPane() {
        return null;
    }

    public boolean isLoginSuccess() {
        return false;
    }
}
