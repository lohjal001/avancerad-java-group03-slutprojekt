package com.example.finalprojectavcjava;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.util.List;
import java.time.format.DateTimeFormatter;

public class DayUI {

    public static Text title;
    private static Text holidayText;
    public String dateString;
    StackPane stack;
    private VBox ul;

    // Metoden för att uppdatera evenemangslistan
    private void setEvents(List<Event> events, CustomCalendar calendar, CustomDate currentDate) {
        ul.getChildren().clear(); // Rensa alla element
        for (Event event : events) {
            HBox li = new HBox();
            li.setPadding(new Insets(6, 0, 0, 10));

            Text txt = new Text();
            txt.setWrappingWidth(130);
            txt.setText(event.getContent());

            Region r = new Region();
            HBox.setHgrow(r, Priority.ALWAYS);

            Button rmBtn = new Button();
            rmBtn.setText("\uD83D\uDDD1\uFE0F");

            // Lyssnaren för att ta bort evenemanget
            rmBtn.setOnAction(rmEvent -> {
                calendar.removeEvent(event);
                setEvents(calendar.getEventNamesByDate(currentDate.date), calendar, currentDate); // Uppdatera listan
            });

            li.getChildren().addAll(txt, r, rmBtn);
            ul.getChildren().add(li);
        }
    }

    // Konstruktorn för DayUI
    DayUI(CustomDate currentDate, CustomCalendar calendar) {
        stack = new StackPane();
        VBox dayBox = new VBox();
        dayBox.setPadding(new Insets(0, 2, 0, 2));
        dayBox.minHeight(500.0);
        dayBox.maxWidth(200);
        dayBox.setPadding(new Insets(10, 5, 10, 5));

        //skapar en String med dagarnas datum för att jämföra mot API array
        dateString= currentDate.date.getYear()-1+ DateTimeFormatter.ofPattern("-MM-dd").format(currentDate.date);

        // Uppdaterar bakgrundsfärg beroende på om det är dagens datum
        if (currentDate.isToday()) {
            dayBox.setStyle("-fx-background-color: #AFC8AD;");
        } else {
            dayBox.setStyle("-fx-background-color: #88AB8E;");
        }

        Rectangle r = new Rectangle();
        r.setX(0.0f);
        r.setY(0.0f);
        r.setWidth(200.0f);
        r.setHeight(500.0f);
        r.setFill(Color.web("67729D"));  // Lila

        VBox header = new VBox();
        header.setAlignment(Pos.BASELINE_CENTER);
        header.setPadding(new Insets(0, 0, 20, 0));

        // Titel och undertitel
        Text title = new Text();
        title.setText(currentDate.weekday);
        title.setFont(Font.font("DejaVu Sans", 22));

        Text subtitle = new Text();
        subtitle.setText(currentDate.dayAndMonth);
        subtitle.setFont(Font.font("DejaVu Sans", 17));

        holidayText = new Text();
        holidayText.setFont(Font.font("DejaVu Sans", 12));

        header.getChildren().addAll(title, holidayText, subtitle);

        // Listan med evenemang
        ul = new VBox(10);
        ScrollPane ulScroll = new ScrollPane(ul);
        ulScroll.setPrefViewportHeight(325);
        ulScroll.setStyle("-fx-background-color: #FED9ED;");

        ul.setMaxWidth(175);
        ul.setMinHeight(325);

        // Hämtar evenemang för aktuellt datum och visar dem i listan
        List<Event> events = calendar.getEventNamesByDate(currentDate.date);
        setEvents(events, calendar, currentDate);

        // Textfält för att ange nytt evenemang
        TextField textField = new TextField();
        textField.setTooltip(new Tooltip("Enter info"));

        // Knapp för att lägga till nytt evenemang
        Button btn = new Button();
        btn.setPrefWidth(170);
        btn.setStyle("-fx-background-color: #FFBB5C; -fx-text-fill: #D0C11");
        btn.setPadding(new Insets(5, 5, 5, 5));
        btn.setText("Add Event");

        // Lyssnaren för att lägga till nytt evenemang
        btn.setOnAction(event -> {
            if (!textField.getText().isEmpty()) {
                calendar.addEvent(new Event(currentDate.date, textField.getText()));
                textField.setText("");
                setEvents(calendar.getEventNamesByDate(currentDate.date), calendar, currentDate);
            }
        });

        VBox btnWrapper = new VBox(btn);
        btnWrapper.setPadding(new Insets(10, 0, 0, 0));
        btnWrapper.setAlignment(Pos.CENTER);

        // Lägger till element i dagens box
        dayBox.getChildren().addAll(header, ulScroll, textField, btnWrapper);

        stack.getChildren().add(r);
        stack.getChildren().add(dayBox);
    }

    public void setTitle(String s){
        holidayText.setText(s);
    }
}
