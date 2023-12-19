package com.example.finalprojectavcjava;

import java.io.Serializable;
import java.time.LocalDate;

public class Event implements Serializable {

    // Datum och innehåll för ett evenemang
    private LocalDate date;
    private String content;

    // Konstruktorn för att skapa ett evenemang med givet datum och innehåll
    Event(LocalDate date, String content) {
        this.date = date;
        this.content = content;
    }

    // Getter-metod för att hämta innehållet i evenemanget
    public String getContent() {
        return content;
    }

    // Getter-metod för att hämta datumet för evenemanget
    public LocalDate getDate() {
        return date;
    }
}
