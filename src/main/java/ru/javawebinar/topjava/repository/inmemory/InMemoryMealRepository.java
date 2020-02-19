package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private Map<Integer, List<Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(meal -> save(1, meal));
    }

    @Override
    public Meal save(int userId, Meal meal) {
        if (!repository.containsKey(userId)) {
            repository.put(userId, new ArrayList<>());
        }
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.get(userId).add(meal);
            return meal;
        }
        try {
            return repository.get(userId).set(meal.getId() - 1, meal);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public boolean delete(int userId, int mealId) {
        try {
            return repository.get(userId).remove(mealId - 1) != null;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    @Override
    public Meal get(int userId, int mealId) {
        try {
            return repository.get(userId).get(mealId - 1);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        return repository.get(userId);
    }
}

