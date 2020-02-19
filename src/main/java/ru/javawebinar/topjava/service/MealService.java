package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.ValidationUtil;

import java.time.LocalDate;
import java.util.Collection;

@Service
public class MealService {

    private final MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Meal create(int userId, Meal meal) {
        return repository.save(userId, meal);
    }

    public void delete(int userId, int mealId) {
        ValidationUtil.checkNotFoundWithId(repository.delete(userId, mealId), mealId);
    }

    public Meal get(int userId, int mealId) {
        return ValidationUtil.checkNotFoundWithId(repository.get(userId, mealId), mealId);
    }

    public Collection<Meal> getAll(int userId) {
        return repository.getAll(userId);
    }

    public Collection<Meal> getAllWithDateFilter(int userId, LocalDate start, LocalDate end) {
        return repository.getAllWithDateFilter(userId, start, end);
    }

    public void update(int userId, Meal meal) {
        ValidationUtil.checkNotFoundWithId(repository.save(userId, meal), meal.getId());
    }
}