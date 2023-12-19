package com.example.finalprojectavcjava;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    // Instansvariabel för att IDandPasswords-objektet
    private IDandPasswords idandPasswords;

    // Metoden som körs vid start av JavaFX-applikationen
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Skapar och hämtar IDandPasswords-objektet
        idandPasswords = new IDandPasswords();
        idandPasswords.getLoginInfo();

        // Skapar LoginPage-objekt med IDandPasswords-informationen
        LoginPage loginPage = new LoginPage(idandPasswords.getLoginInfo());

        // Visar inloggningsdialogen och kontrollerar om användaren är inloggad
        boolean loggedIn = loginPage.showLoginDialog();

        if (loggedIn) {
            // Om inloggning lyckas, skapa huvudfönstret

            primaryStage.setTitle("Calendar");

            // Hämtar användarinformationen från LoginPage och överför till CustomCalendar
            String userID = loginPage.getUserID();
            CustomCalendar calendar = new CustomCalendar(userID);

            // Skapar UserInterface-objekt och definierar scenen
            UserInterface UI = new UserInterface(calendar);
            Scene scene = new Scene(UI.scrollBody, 1500, 620);

            // Sätter scenen för primaryStage och definierar vad som ska hända vid stängning
            primaryStage.setScene(scene);
            primaryStage.setOnCloseRequest(e -> {
            });

            // Visar huvudfönstret
            primaryStage.show();
        } else {
            // Åtgärder som ska vidtas om användaren inte är inloggad (ej nödvändigt)
        }
    }
}
