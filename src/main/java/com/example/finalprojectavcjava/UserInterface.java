package com.example.finalprojectavcjava;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class UserInterface {

    // ScrollPane för att möjliggöra att scrolla på sidan
    public ScrollPane scrollBody;

    // BorderPane för att organisera gränssnittets layout
    public BorderPane body;

    // HBox som innehåller rubriken för kalendern
    public HBox header;

    // FlowPane för att organisera dagarna i veckan
    public FlowPane main;

    // Ritar ut dagarna i veckan baserat på kalendern
    void renderDays(CustomCalendar calendar) {
        main.getChildren().clear();
        for (int i = 1; i <= 7; i++) {
            // Skapar DayUI-objekt för varje dag och lägger till dem i FlowPane
            DayUI dayui = new DayUI(calendar.getDayOfWeek(i), calendar);
            main.getChildren().add(dayui.stack);
        }
    }

    // Konstruktorn för UserInterface
    UserInterface(CustomCalendar calendar) {

        // Skapar en rubrik för kalendern
        Text calendarTitle = new Text("Calendar " + calendar.getYear());
        calendarTitle.setFont(Font.font("Roboto UI", 27));

        // Skapar en HBox för rubriken och centrerar den
        header = new HBox(calendarTitle);
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(10, 10, 10, 10));

        // Skapar en BorderPane för hela gränssnittet och en ScrollPane för möjliggöra scrollande
        body = new BorderPane();
        scrollBody = new ScrollPane(body);
        scrollBody.setFitToWidth(true);
        scrollBody.setStyle("-fx-background-color: ddd;");

        // Sätter in padding för BorderPane
        body.setPadding(new Insets(5, 0, 5, 0));

        // Lägger till rubriken överst i BorderPane
        body.setTop(header);

        // Skapar en FlowPane för att organisera dagarna
        main = new FlowPane();
        main.setPadding(new Insets(5, 0, 5, 0));
        main.setVgap(10);
        main.setHgap(10);
        main.setPrefWrapLength(170);

        // Renderar dagarna baserat på den aktuella kalendern
        renderDays(calendar);

        // Centrerar FlowPane i BorderPane
        main.setAlignment(Pos.BASELINE_CENTER);
        body.setCenter(main);

        // Skapar en HBox för navigationsknappar och information om veckonummer
        HBox nav = new HBox();
        nav.setPadding(new Insets(10, 0, 10, 0));
        nav.setAlignment(Pos.CENTER);

        // Visar aktuellt veckonummer
        Text currentWeek = new Text("V. " + calendar.getWeekOfYear());
        currentWeek.setWrappingWidth(60);
        currentWeek.setTextAlignment(TextAlignment.CENTER);

        // Knappar för att navigera till föregående och nästa vecka
        Button prevWeekBtn = new Button("<");
        prevWeekBtn.setOnAction(e -> {
            currentWeek.setText("V. " + calendar.prevWeek());
            calendarTitle.setText("Calendar " + calendar.getYear());
            renderDays(calendar);
        });

        Button nextWeekBtn = new Button(">");
        nextWeekBtn.setOnAction(e -> {
            currentWeek.setText("V. " + calendar.nextWeek());
            calendarTitle.setText("Calendar " + calendar.getYear());
            renderDays(calendar);
        });

        // Textfält för att ange evenemangstext
        TextField eventTextField = new TextField();
        eventTextField.setPromptText("New event");

        // Knapp för att lägga till evenemang
        Button addEventBtn = new Button("New event");
        addEventBtn.setOnAction(event -> {
            String eventText = eventTextField.getText();
            if (!eventText.isEmpty()) {
                // Lägg till evenemang och uppdatera gränssnittet
                calendar.addEvent(new Event(calendar.currentDate, eventText));
                eventTextField.clear();
                renderDays(calendar);
            }
        });

        // Lägg till navigationskomponenter i HBox
        nav.getChildren().addAll(prevWeekBtn, currentWeek, nextWeekBtn, eventTextField, addEventBtn);

        // Lägg till navigationskomponenterna i BorderPane
        body.setBottom(nav);
    }

}
