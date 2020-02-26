package ru.javawebinar.topjava.service;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void get() {
        Meal meal_1 = service.get(MEAL_1_ID, ADMIN_ID);
        assertMatch(meal_1, MEAL_1);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound1() {
        service.get(MEAL_8_ID, ADMIN_ID);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound2() {
        service.get(MEAL_1_ID, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void delete() {
        service.delete(MEAL_1_ID, ADMIN_ID);
        service.get(MEAL_1_ID, ADMIN_ID);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound1() {
        service.delete(MEAL_8_ID, ADMIN_ID);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound2() {
        service.delete(MEAL_1_ID, USER_ID);
    }

    @Test
    public void getBetweenHalfOpen() {
        List<Meal> filtered = service.getBetweenHalfOpen(
                LocalDate.of(2020, Month.JANUARY, 30),
                LocalDate.of(2020, Month.JANUARY, 30),
                ADMIN_ID
        );
        assertMatch(filtered, MEAL_3, MEAL_2, MEAL_1);
    }

    @Test
    public void getAll() {
        List<Meal> all = service.getAll(ADMIN_ID);
        assertMatch(all, MEAL_7, MEAL_6, MEAL_5, MEAL_4, MEAL_3, MEAL_2, MEAL_1);
    }

    @Test
    public void update() {
        Meal updated = getAdminUpdated();
        service.update(updated, ADMIN_ID);
        assertMatch(service.get(MEAL_1_ID, ADMIN_ID), updated);
    }

    @Test(expected = NotFoundException.class)
    public void updateNotFound1() {
        Meal updated = getAdminUpdated();
        service.update(updated, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void updateNotFound2() {
        Meal updated = getUserUpdated();
        service.update(updated, ADMIN_ID);
    }

    @Test
    public void create() {
        Meal newMeal = getNew();
        Meal created = service.create(newMeal, ADMIN_ID);
        Integer newId = created.getId();
        newMeal.setId(newId);
        assertMatch(created, newMeal);
        assertMatch(service.get(newId, ADMIN_ID), newMeal);
    }
}