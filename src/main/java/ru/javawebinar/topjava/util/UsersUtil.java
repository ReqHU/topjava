package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;

public class UsersUtil {
    public static final List<User> USERS = Arrays.asList(
            new User("User1", "User1@mail.ru", "User1Password", Role.ROLE_ADMIN, Role.ROLE_USER),
            new User("User2", "User2@mail.ru", "User2Password", Role.ROLE_USER)
    );
}
