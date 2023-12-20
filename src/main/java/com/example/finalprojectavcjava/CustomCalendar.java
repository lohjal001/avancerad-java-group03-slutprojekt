package com.example.finalprojectavcjava;

import java.io.*;
import java.time.DayOfWeek;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Calendar;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class CustomCalendar {

    private String userID;
    private Calendar calendar;
    private ArrayList<Event> events = new ArrayList<Event>();
    public LocalDate currentDate;
    private int weekOfYear;
    private int year;

    // Metoden för att spara kalendern till en temporär fil
    public void saveFile() {
        try {
            FileOutputStream fos = new FileOutputStream("calendar-data.tmp");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(events);
            oos.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    // Metoden för att läsa in kalenderdatan från en fil
    private void readFile() {
        try {
            FileInputStream fis = new FileInputStream("calendar-data.tmp");
            ObjectInputStream ois = new ObjectInputStream(fis);
            events = (ArrayList<Event>) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    // Konstruktorn för CustomCalendar
    public CustomCalendar(String userID) {
        currentDate = LocalDate.now();
        calendar = Calendar.getInstance();
        weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
        year = calendar.get(Calendar.YEAR);
        readFile();
    }

    // Metoden för att hämta en CustomDate för en specifik veckodag
    CustomDate getDayOfWeek(int dayIndex) { // dayIndex is between 1 and 7
        DayOfWeek currentDay = currentDate.getDayOfWeek();
        LocalDate newDate = currentDate;
        if (dayIndex > currentDay.getValue()) {
            // Framåt i tiden
            newDate = currentDate.plusDays(dayIndex - currentDay.getValue());
        } else if (dayIndex < currentDay.getValue()) {
            // Bakåt i tiden
            newDate = currentDate.minusDays(currentDay.getValue() - dayIndex);
        }
        return new CustomDate(newDate);
    }

    // Metoden för att hämta veckonummer
    int getWeekOfYear() {
        return weekOfYear;
    }

    // Metoden för att hämta år
    int getYear() {
        return year;
    }

    // Metoden för att hämta listan över händelser
    ArrayList<Event> getEvents() {
        return events;
    }

    // Metoden för att hämta händelser för ett specifikt datum
    List<Event> getEventNamesByDate(LocalDate date) {
        return events.stream().filter(e -> e.getDate().equals(date)).collect(Collectors.toList());
    }

    // Metoden för att lägga till en händelse
    void addEvent(Event newEvent) {
        events.add(newEvent);
        saveFile();
    }

    // Metoden för att ta bort en händelse
    void removeEvent(Event oldEvent) {
        events.remove(oldEvent);
        saveFile();
    }

    // Metoden för att gå till nästa vecka och uppdatera veckonummer och år
    int nextWeek() {
        currentDate = currentDate.plusWeeks(1);
        weekOfYear = currentDate.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear());
        year = currentDate.getYear();
        return weekOfYear;
    }

    // Metoden för att gå till föregående vecka och uppdatera veckonummer och år
    int prevWeek() {
        currentDate = currentDate.minusWeeks(1);
        weekOfYear = currentDate.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear());
        year = currentDate.getYear();
        return weekOfYear;
    }

    // Metoden för att visa gränssnittet för kalendern
    public void showCalendarInterface() {
    }
}
