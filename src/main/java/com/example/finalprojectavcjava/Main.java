package com.example.finalprojectavcjava;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.HashMap;

// Huvudklassen som är en JavaFX-applikation
public class Main extends Application {

    // Metoden som startar applikationen
    public static void main(String[] args) {
        launch(args);
    }

    // Överlagrad metod som körs vid start av applikationen
    @Override
    public void start(Stage primaryStage) {
        // Öppnar inloggningsdialogen när applikationen startar
        openLoginDialog(primaryStage);
    }

    // Metoden som öppnar inloggningsdialogen
    private void openLoginDialog(Stage primaryStage) {

        // Skapar ett objekt för att hantera användarnas ID och lösenord
        IDandPasswords idandPasswords = new IDandPasswords();
        HashMap<String, String> loginInfo = idandPasswords.getLoginInfo();

        // Skapar ett objekt för inloggningssidan och visar dialogrutan
        LoginPage loginPage = new LoginPage(loginInfo);
        boolean loggedIn = loginPage.showLoginDialog();

        // Om användaren är inloggad
        if (loggedIn) {
            // Sätter titeln på huvudfönstret
            primaryStage.setTitle("Calendar");

            // Hämtar användarens ID från inloggningssidan
            String userID = loginPage.getUserID();

            // Skapar ett kalenderobjekt för användaren
            CustomCalendar calendar = new CustomCalendar(userID);

            // Skapar ett användargränssnitt med kalenderobjektet
            UserInterface UI = new UserInterface(calendar);

            // Skapar en knapp för att logga ut
            Button logoutButton = new Button("Sign out");
            logoutButton.setOnAction(e -> handleLogout(primaryStage));

            // Skapar en layout för att organisera gränssnittet
            BorderPane layout = new BorderPane();
            layout.setCenter(UI.scrollBody);
            BorderPane.setAlignment(logoutButton, javafx.geometry.Pos.TOP_RIGHT);
            BorderPane.setMargin(logoutButton, new javafx.geometry.Insets(10, 10, 10, 10));
            layout.setTop(logoutButton);

            // Skapar en bottenruta för ytterligare funktionalitet
            BottomBox bottomBox = new BottomBox();
            layout.setBottom(bottomBox);

            // Hämtar information om den primära skärmen
            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();

            // Skapar en scen med layouten och sätter storleken baserat på skärmens storlek
            Scene scene = new Scene(layout, bounds.getWidth(), bounds.getHeight());

            // Sätter scenen för huvudfönstret
            primaryStage.setScene(scene);

            // Visar huvudfönstret
            primaryStage.show();
        } else {
            // Om användaren inte är inloggad, visa en uppmaningsruta och inloggningsdialogen öppnas igen
            showLoginPrompt();
            openLoginDialog(primaryStage);
        }
    }

    // Metoden för att hantera utloggning
    private void handleLogout(Stage primaryStage) {
        // Visa en uppmaningsruta om utloggning och öppna inloggningsdialogen igen
        showLogoutPrompt();
        primaryStage.close();
        openLoginDialog(primaryStage);
    }

    // Metoden för att visa uppmaningsruta vid inloggning
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
