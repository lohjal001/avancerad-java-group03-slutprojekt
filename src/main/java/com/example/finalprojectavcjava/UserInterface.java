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
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import static com.example.finalprojectavcjava.HolidayAPI.holidaysArray;

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

            for (JsonValue jv: holidaysArray) {
                JsonObject jo = jv.asObject();
                String redDate = jo.getString("date", "missing");
                if (redDate.equals(dayui.dateString)) {
                    dayui.setTitle(jo.getString("name", "missing"));

                }

            }
        }


    }

    // Konstruktorn för UserInterface
    UserInterface(CustomCalendar calendar) {

        // Skapar en rubrik för kalendern
        Text calendarTitle = new Text("Kalender " + calendar.getYear());
        calendarTitle.setFont(Font.font("DejaVu Sans", 27));
        calendarTitle.setStyle("-fx-background-color: F1EAFF; -fx-text-fill: 392467");

        // Skapar en HBox för rubriken och centrerar den
        header = new HBox(calendarTitle);
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(10, 10, 10, 10));
        header.setStyle("-fx-background-color: FDF7E4;");

        // Skapar en BorderPane för hela gränssnittet och en ScrollPane för möjliggöra scrollande
        body = new BorderPane();
        scrollBody = new ScrollPane(body);
        scrollBody.setFitToWidth(true);
        scrollBody.setStyle("-fx-background-color: FFBB5C;");

        // Sätter in padding för BorderPane
        body.setPadding(new Insets(5, 0, 5, 0));
        body.setStyle("-fx-background-color: FDF7E4;");

        // Lägger till rubriken överst i BorderPane
        body.setTop(header);

        // Skapar en FlowPane för att organisera dagarna
        main = new FlowPane();
        main.setPadding(new Insets(5, 0, 5, 0));
        main.setVgap(10);
        main.setHgap(10);
        main.setPrefWrapLength(170);
        body.setStyle("-fx-background-color: FDF7E4;");


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
            calendarTitle.setText("Kalender " + calendar.getYear());
            renderDays(calendar);
            calendarTitle.setFont(Font.font("DejaVu Sans", 35));
            currentWeek.setFont(Font.font("DejaVu Sans", 22));
        });

        Button nextWeekBtn = new Button(">");
        nextWeekBtn.setOnAction(e -> {
            currentWeek.setText("V. " + calendar.nextWeek());
            calendarTitle.setText("Kalender " + calendar.getYear());
            renderDays(calendar);
            calendarTitle.setFont(Font.font("DejaVu Sans", 35));
            currentWeek.setFont(Font.font("DejaVu Sans", 22));

        });


        // Lägg till navigationskomponenter i HBox
        nav.getChildren().addAll(prevWeekBtn, currentWeek, nextWeekBtn);

        // Lägg till navigationskomponenterna i BorderPane
        body.setBottom(nav);
    }

}
