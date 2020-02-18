package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

@Controller
public class MealRestController {

    @Autowired
    private MealService service;

    public List<MealTo> getAll() {
        return MealsUtil.getTos(service.getAll(SecurityUtil.authUserId()), SecurityUtil.authUserCaloriesPerDay());
    }

    public List<MealTo> getAllWithFilter(LocalTime startTime, LocalTime endTime) {
        return MealsUtil.getFilteredTos(service.getAll(SecurityUtil.authUserId()), SecurityUtil.authUserCaloriesPerDay(), startTime, endTime);
    }

    public Meal get(int mealId) {
        return service.get(SecurityUtil.authUserId(), mealId);
    }

    public Meal create(Meal meal) {
        checkNew(meal);
        return service.create(SecurityUtil.authUserId(), meal);
    }

    public void delete(int mealId) {
        service.delete(SecurityUtil.authUserId(), mealId);
    }

    public void update(Meal meal, int mealId) {
        assureIdConsistent(meal, mealId);
        service.update(SecurityUtil.authUserId(), meal);
    }

}