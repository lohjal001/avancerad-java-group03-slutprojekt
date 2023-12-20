package com.example.finalprojectavcjava;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class CustomDate {
    LocalDate date;
    String weekday;
    String dayAndMonth;

    // Hämtar ordningsnumret för att placeras efter månadens dag
    private String getOrdinalNumber(int num) {
        switch (num % 10) {
            case 1:
                return "st";
            case 2:
                return "nd";
            case 3:
                return "rd";
            default:
                return "th";
        }
    }

    // Gör första bokstaven i en sträng versal och resten gemener
    private String makeFirstUpperCase(String str) {
        return str.substring(0, 1) + str.substring(1).toLowerCase();
    }

    // Konstruktorn för CustomDate
    public CustomDate(LocalDate date) {
        this.date = date;
        updateDateInfo();
    }

    // Metod för att uppdatera informationen om datumet
    private void updateDateInfo() {
        DayOfWeek currentDay = date.getDayOfWeek();
        weekday = makeFirstUpperCase(currentDay.name());
        dayAndMonth = String.format("%d%s of %s", date.getDayOfMonth(), getOrdinalNumber(date.getDayOfMonth()), makeFirstUpperCase(date.getMonth().name()));
    }

    // Kontrollerar om datumet är dagens datum
    boolean isToday() {
        LocalDate currDate = LocalDate.now();
        return currDate.equals(date);
    }

    // Metod för att sätta ett nytt datum och uppdatera informationen
    public void setDate(LocalDate date) {
        this.date = date;
        updateDateInfo();
    }
}
