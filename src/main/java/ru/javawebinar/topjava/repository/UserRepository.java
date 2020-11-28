package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    // null if not found, when updated
    User save(User user);

    // false if not found
    boolean delete(int id);

    Optional<User> get(int id);

    Optional<User> getByEmail(String email);

    List<User> getAll();

    default Optional<User> getWithMeals(int id) {
        throw new UnsupportedOperationException();
    }

}