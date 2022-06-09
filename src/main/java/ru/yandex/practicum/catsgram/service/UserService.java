package ru.yandex.practicum.catsgram.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.catsgram.controller.UserController;
import ru.yandex.practicum.catsgram.exceptions.InvalidEmailException;
import ru.yandex.practicum.catsgram.exceptions.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private Set<User> users = new HashSet<>();

    public Set<User> findAll() {
        log.debug("Текущее количество постов: {}", users.size());
        return users;
    }

    public User create(User user) throws UserAlreadyExistException, InvalidEmailException {
        for (User userEx : users) {
            if (userEx.getEmail().equals(user.getEmail())) {
                throw new UserAlreadyExistException("User already exist");
            }
        }
        if (user.getEmail().isBlank()) {
            throw new InvalidEmailException("Incorrect email");
        }
        users.add(user);
        return user;
    }

    public User update(User user) throws InvalidEmailException {
        if (user.getEmail().isBlank() || user.getEmail() == null) {
            throw new InvalidEmailException("Incorrect email");
        }
        users.add(user);
        return user;
    }

    public User findUserByEmail(String email) {
        User foundUser = null;
        for (User savedUser : users) {
            if (savedUser.getEmail().equals(email)) {
                foundUser = savedUser;
            }
        }
        return foundUser;
    }
}