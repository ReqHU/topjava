package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

public class UserMeal {
    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    private static HashMap<LocalDate, Integer> dailyCalories = new HashMap<>();

    public UserMeal(LocalDateTime dateTime, String description, int calories) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        dailyCalories.merge(getDateTime().toLocalDate(), getCalories(), Integer::sum);
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public static HashMap<LocalDate, Integer> getDailyCalories() {
        return dailyCalories;
    }
}
