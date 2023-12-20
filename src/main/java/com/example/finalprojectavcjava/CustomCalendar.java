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

    LocalDate currentDate;
    private int weekOfYear;
    private int year;

    //Save the calendar to a tmp file
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
    CustomCalendar(String userID) {
        currentDate = LocalDate.now();
        calendar = Calendar.getInstance();

        weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
        year = calendar.get(Calendar.YEAR);
        readFile();
    }

    CustomDate getDayOfWeek(int dayIndex) { //dayIndex is between 1 and 7
        DayOfWeek currentDay = currentDate.getDayOfWeek();
        LocalDate newDate = currentDate;
        if (dayIndex > currentDay.getValue()) {
            //We are looking into the future
            newDate = currentDate.plusDays(dayIndex-currentDay.getValue());
        } else if (dayIndex < currentDay.getValue()) {
            //We are looking into the past
            newDate = currentDate.minusDays(currentDay.getValue()-dayIndex);
        }
        return new CustomDate(newDate);
    }

    int getWeekOfYear() {
        return weekOfYear;
    }

    int getYear() {
        return year;
    }
    ArrayList<Event> getEvents() {
        return events;
    }

    List<Event> getEventNamesByDate(LocalDate date) {
        return events.stream().filter(e-> e.getDate().equals(date)).collect(Collectors.toList());
    }



    void addEvent(Event newEvent) {
        events.add(newEvent);
        saveFile();
    }
    void removeEvent(Event oldEvent) {
        events.remove(oldEvent);
        saveFile();
    }

    int nextWeek() {
        currentDate = currentDate.plusWeeks(1);
        weekOfYear = currentDate.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear());
        year = currentDate.getYear();
        return weekOfYear;
    }

    int prevWeek() {
        currentDate = currentDate.minusWeeks(1);
        weekOfYear = currentDate.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear());
        year = currentDate.getYear();
        return weekOfYear;
    }

    public void showCalendarInterface() {
    }

    public CustomDate getCustomDate(int i) {

        return null;
    }
}

