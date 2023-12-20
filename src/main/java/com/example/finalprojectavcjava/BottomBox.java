package com.example.finalprojectavcjava;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class BottomBox extends HBox {

    public BottomBox() {
        // TextView
        Label textView = new Label("Väder");

        // ImageView
        Image image = new Image("file:path/to/your/image.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);

        // Lägger till TextView och ImageView i HBox
        getChildren().addAll(textView, imageView);
    }
}
