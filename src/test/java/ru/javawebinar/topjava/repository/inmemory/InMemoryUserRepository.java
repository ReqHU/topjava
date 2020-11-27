package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.util.testData.UserTestData;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.testData.UserTestData.ADMIN;
import static ru.javawebinar.topjava.util.testData.UserTestData.USER;

@Repository
public class InMemoryUserRepository extends InMemoryBaseRepository<User> implements UserRepository {

    public void init() {
        map.clear();
        map.put(UserTestData.USER_ID, USER);
        map.put(UserTestData.ADMIN_ID, ADMIN);
    }

    @Override
    public List<User> getAll() {
        return getCollection().stream()
                .sorted(Comparator.comparing(User::getName).thenComparing(User::getEmail))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> getByEmail(String email) {
        Objects.requireNonNull(email, "email must not be null");
        return getCollection().stream()
                .filter(u -> email.equals(u.getEmail()))
                .findFirst();
    }
}