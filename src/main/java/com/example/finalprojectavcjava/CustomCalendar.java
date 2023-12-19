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

    // Sparar kalendern till en temporär fil
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

    // Läser in kalenderdatan från en fil
    private void readFile() {
        try {
            FileInputStream fis = new FileInputStream("calendar-data.tmp");
            ObjectInputStream ois = new ObjectInputStream(fis);
            events = (ArrayList<Event>) ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
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

    // Hämtar en CustomDate för en specifik veckodag
    CustomDate getDayOfWeek(int dayIndex) { // dayIndex is between 1 and 7
        DayOfWeek currentDay = currentDate.getDayOfWeek();
        LocalDate newDate = currentDate;
        if (dayIndex > currentDay.getValue()) {
            // Vi tittar in i framtiden
            newDate = currentDate.plusDays(dayIndex - currentDay.getValue());
        } else if (dayIndex < currentDay.getValue()) {
            // Vi tittar in i det förflutna
            newDate = currentDate.minusDays(currentDay.getValue() - dayIndex);
        }
        return new CustomDate(newDate);
    }

    // Hämtar veckonummer
    int getWeekOfYear() {
        return weekOfYear;
    }

    // Hämtar år
    int getYear() {
        return year;
    }

    // Hämtar listan över händelser
    ArrayList<Event> getEvents() {
        return events;
    }

    // Hämtar händelser för ett specifik datum
    List<Event> getEventNamesByDate(LocalDate date) {
        return events.stream().filter(e -> e.getDate().equals(date)).collect(Collectors.toList());
    }

    // Lägger till en händelse
    void addEvent(Event newEvent) {
        events.add(newEvent);
        saveFile();
    }

    // Tar bort en händelse
    void removeEvent(Event oldEvent) {
        events.remove(oldEvent);
        saveFile();
    }

    // Går till nästa vecka och uppdaterar veckonummer och år
    int nextWeek() {
        currentDate = currentDate.plusWeeks(1);
        weekOfYear = currentDate.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear());
        year = currentDate.getYear();
        return weekOfYear;
    }

    // Går till föregående vecka och uppdaterar veckonummer och år
    int prevWeek() {
        currentDate = currentDate.minusWeeks(1);
        weekOfYear = currentDate.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear());
        year = currentDate.getYear();
        return weekOfYear;
    }

    // Visar gränssnittet för kalendern
    public void showCalendarInterface() {
    }
}
