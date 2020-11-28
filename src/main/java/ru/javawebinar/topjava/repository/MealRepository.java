package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MealRepository {

    // null if updated meal do not belong to userId
    Optional<Meal> save(Meal meal, int userId);

    // false if meal do not belong to userId
    boolean delete(int id, int userId);

    Optional<Meal> get(int id, int userId);

    // ORDERED dateTime desc
    List<Meal> getAll(int userId);

    // ORDERED dateTime desc
    List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId);

    default Optional<Meal> getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }

}
