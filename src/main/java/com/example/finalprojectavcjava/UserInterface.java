package com.example.finalprojectavcjava;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class UserInterface {

    public ScrollPane scrollBody;
    public BorderPane body;
    public HBox header;

    public FlowPane main;
    void renderDays(CustomCalendar calendar) {
        main.getChildren().clear();
        for (int i = 1; i <= 7; i++) {
            DayUI dayui = new DayUI(calendar.getDayOfWeek(i), calendar);
            main.getChildren().add(dayui.stack);
        }
    }

    UserInterface(CustomCalendar calendar) {
        Text calendarTitle = new Text("Calendar of " + String.valueOf(calendar.getYear()));
        calendarTitle.setFont(Font.font("Segone UI", 22));
        header = new HBox(calendarTitle);
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(10, 10, 10, 10));

        body = new BorderPane();
        scrollBody = new ScrollPane(body);
        scrollBody.setFitToWidth(true);
        scrollBody.setStyle("-fx-background-color: ddd;");

        body.setPadding(new Insets(5, 0, 5, 0));
        body.setTop(header);

        main = new FlowPane();
        main.setPadding(new Insets(5, 0, 5, 0));
        main.setVgap(10);
        main.setHgap(10);
        main.setPrefWrapLength(170);

        renderDays(calendar);

        main.setAlignment(Pos.BASELINE_CENTER);
        body.setCenter(main);

        HBox nav = new HBox();
        nav.setPadding(new Insets(10, 0, 10, 0));
        nav.setAlignment(Pos.CENTER);

        Text currentWeek = new Text("V. " + String.valueOf(calendar.getWeekOfYear()));
        currentWeek.setWrappingWidth(60);
        currentWeek.setTextAlignment(TextAlignment.CENTER);

        Button prevWeekBtn = new Button("<");
        prevWeekBtn.setOnAction(e -> {
            currentWeek.setText("V. " + String.valueOf(calendar.prevWeek()));
            calendarTitle.setText("Calendar of " + String.valueOf(calendar.getYear()));
            renderDays(calendar);
        });

        Button nextWeekBtn = new Button(">");
        nextWeekBtn.setOnAction(e -> {
            currentWeek.setText("V. " + String.valueOf(calendar.nextWeek()));
            calendarTitle.setText("Calendar of " + String.valueOf(calendar.getYear()));
            renderDays(calendar);
        });

        // Text field for event input
        TextField eventTextField = new TextField();
        eventTextField.setPromptText("Add Event");

        // Button for adding event
        Button addEventBtn = new Button("Add Event");
        addEventBtn.setOnAction(event -> {
            String eventText = eventTextField.getText();
            if (!eventText.isEmpty()) {
                calendar.addEvent(new Event(calendar.currentDate, eventText));
                eventTextField.clear();
                renderDays(calendar);
            }
        });

        nav.getChildren().addAll(prevWeekBtn, currentWeek, nextWeekBtn, eventTextField, addEventBtn);

        body.setBottom(nav);
    }

    public void showCalendarInterface() {
    }
}
