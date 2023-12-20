package com.example.finalprojectavcjava;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import static com.example.finalprojectavcjava.WeatherAPI.*;

public class BottomBox extends HBox {

    public BottomBox() {
        // TextView för att visa rubriken "Dagens väder"
        Label textView = new Label("Dagens väder");

        // ImageView för att visa en bild (just nu en statisk bild, inte kopplad till vädret)
        Image image = new Image("file:path/to/your/image.jpg");
        ImageView imageView = new ImageView(image);

        // TextField för att visa väderbeskrivning och temperatur från WeatherAPI
        TextField weatherTextField = new TextField();
        weatherTextField.setText(weatherDescription + temp);

        // Laddar en väderikon från OpenWeatherMap API baserat på ikonID
        Image iconImage = new Image("http://openweathermap.org/img/w/" + iconID + ".png");
        ImageView weatherImageView = new ImageView();
        weatherImageView.setImage(image);

        // Lägger till TextView och ImageView i HBox
        getChildren().addAll(textView, imageView);
    }
}
