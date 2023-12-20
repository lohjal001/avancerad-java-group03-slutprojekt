package com.example.finalprojectavcjava;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import static com.example.finalprojectavcjava.WeatherAPI.*;

public class BottomBox extends HBox {

    public BottomBox() {

        // TextView för att visa rubriken "Dagens väder"
        Label textView = new Label("Todays weather");
        textView.setFont(Font.font("Segone UI", 20));

        // TextField för att visa väderbeskrivning och temperatur från WeatherAPI
        Label weatherTextLabel = new Label();
        weatherTextLabel.setText(weatherDescription + temp);
        weatherTextLabel.setFont(Font.font("Segone UI", 20));


        // Laddar en väderikon från OpenWeatherMap API baserat på ikonID
        Image iconImage = new Image("http://openweathermap.org/img/wn/" + iconID + ".png");
        ImageView weatherImageView = new ImageView();
        weatherImageView.setFitHeight(100);
        weatherImageView.setFitWidth(100);
        weatherImageView.setImage(iconImage);


        // Lägger till TextView och ImageView i HBox
        getChildren().addAll(textView, weatherTextLabel, weatherImageView);
    }
}
