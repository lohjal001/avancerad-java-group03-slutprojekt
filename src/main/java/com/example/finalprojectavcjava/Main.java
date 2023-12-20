package com.example.finalprojectavcjava;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    // Metoden som startar projektet
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    // Metoden som körs vid start av projektet
    public void start(Stage primaryStage) {
        // Skapar och hämtar IDandPasswords-objektet
        IDandPasswords idandPasswords = new IDandPasswords();
        idandPasswords.getLoginInfo();

        // Skapar LoginPage-objekt med IDandPasswords-informationen
        LoginPage loginPage = new LoginPage(idandPasswords.getLoginInfo());

        // Visar inloggningsdialogen och kontrollerar om användaren är inloggad
        boolean loggedIn = loginPage.showLoginDialog();

        if (loggedIn) {
            // Om inloggning lyckas, skapa huvudfönstret och kalenderobjekt
            primaryStage.setTitle("Calendar");

            String userID = loginPage.getUserID();
            CustomCalendar calendar = new CustomCalendar(userID);

            // Skapar UserInterface-objekt med kalenderobjektet
            UserInterface UI = new UserInterface(calendar);

            // Skapar en knapp för att logga ut
            Button logoutButton = new Button("Sign out");
            logoutButton.setOnAction(e -> handleLogout(primaryStage));

            // Skapar en BorderPane för att organisera layouten
            BorderPane layout = new BorderPane();

            // Centrera huvudinnehållet (scrollBody från UI-objektet)
            layout.setCenter(UI.scrollBody);

            // Placera logga ut-knappen i övre högra hörnet och justera marginalerna
            BorderPane.setAlignment(logoutButton, javafx.geometry.Pos.TOP_RIGHT);
            BorderPane.setMargin(logoutButton, new Insets(10, 10, 10, 10)); // Justera dessa värden efter önskat indrag

            // Lägg till logga ut-knappen högst upp i BorderPane
            layout.setTop(logoutButton);

            // Skapa en instans av BottomBox
            BottomBox bottomBox = new BottomBox();

            // Lägg till BottomBox i din layout (till exempel i Bottom-platsen)
            layout.setBottom(bottomBox);

            // Skapa scenen med layouten och storleken
            Scene scene = new Scene(layout, 1500, 620);

            // Sätt scenen för primaryStage och definiera vad som ska hända vid stängning
            primaryStage.setScene(scene);
            primaryStage.setOnCloseRequest(e -> {
                // Eventuell logik som ska köras vid stängning
            });

            // Visa huvudfönstret
            primaryStage.show();
        } else {
            // Om användaren inte är inloggad, visa en uppmaningsruta
            showLoginPrompt();
            primaryStage.close(); // Stäng fönstret om användaren inte är inloggad
        }
    }

    // Metoden för att hantera logik vid utloggning
    private void handleLogout(Stage primaryStage) {
        showLogoutPrompt();
        primaryStage.close();
    }

    // Metoden för att visa uppmaningsruta
    private void showLoginPrompt() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login Required");
        alert.setHeaderText("You need to log in to access the calendar");
        alert.setContentText("Please log in to continue.");

        alert.showAndWait();
    }
    // Metoden för att visa uppmaningsruta vid utloggning
    private void showLogoutPrompt() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Logout Successful");
        alert.setHeaderText("You have been successfully logged out");
        alert.setContentText("Thank you for using the calendar application.");

        alert.showAndWait();
    }
}
