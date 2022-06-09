package ru.yandex.practicum.catsgram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.exceptions.InvalidEmailException;
import ru.yandex.practicum.catsgram.exceptions.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;
import ru.yandex.practicum.catsgram.service.UserService;

import java.util.Set;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public Set<User> findAll() {
        return userService.findAll();
    }

    @PostMapping("/users")
    public User create(@RequestBody User user) throws UserAlreadyExistException, InvalidEmailException {
        return userService.create(user);
    }

    @PutMapping("/users")
    public User update(@RequestBody User user) throws InvalidEmailException {
        return userService.update(user);
    }

    @GetMapping("/users/{email:.+}")
    public User getUserByEMail(@PathVariable String email) {
        return userService.findUserByEmail(email);
    }
}