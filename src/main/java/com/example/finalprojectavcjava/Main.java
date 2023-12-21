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

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        openLoginDialog(primaryStage);
    }

    private void openLoginDialog(Stage primaryStage) {
        IDandPasswords idandPasswords = new IDandPasswords();
        HashMap<String, String> loginInfo = idandPasswords.getLoginInfo();
        LoginPage loginPage = new LoginPage(loginInfo);
        boolean loggedIn = loginPage.showLoginDialog();

        if (loggedIn) {
            primaryStage.setTitle("Calendar");
            String userID = loginPage.getUserID();
            CustomCalendar calendar = new CustomCalendar(userID);
            UserInterface UI = new UserInterface(calendar);

            Button logoutButton = new Button("Sign out");
            logoutButton.setOnAction(e -> handleLogout(primaryStage));

            BorderPane layout = new BorderPane();
            layout.setCenter(UI.scrollBody);
            BorderPane.setAlignment(logoutButton, javafx.geometry.Pos.TOP_RIGHT);
            BorderPane.setMargin(logoutButton, new javafx.geometry.Insets(10, 10, 10, 10));
            layout.setTop(logoutButton);

            BottomBox bottomBox = new BottomBox();
            layout.setBottom(bottomBox);

            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();

            Scene scene = new Scene(layout, bounds.getWidth(), bounds.getHeight());

            // Ändra bakgrundsfärgen för hela scenen
            scene.setFill(javafx.scene.paint.Color.LIGHTGRAY);

            // Ändra textfärgen för knappen
            logoutButton.setStyle("-fx-text-fill: white; -fx-background-color: darkred;");

            // Sätter scenen för huvudfönstret
            primaryStage.setScene(scene);

            primaryStage.show();
        } else {
            showLoginPrompt();
            openLoginDialog(primaryStage);
        }
    }

    private void handleLogout(Stage primaryStage) {
        showLogoutPrompt();
        primaryStage.close();
        openLoginDialog(primaryStage);
    }

    private void showLoginPrompt() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login Required");
        alert.setHeaderText("You need to log in to access the calendar");
        alert.setContentText("Please log in to continue.");

        alert.showAndWait();
    }

    private void showLogoutPrompt() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Logout Successful");
        alert.setHeaderText("You have been successfully logged out");
        alert.setContentText("Thank you for using the calendar application.");

        alert.showAndWait();
    }
}
