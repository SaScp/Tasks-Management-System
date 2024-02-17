package ru.alex.tasksmanagementsystem.service;

import ru.alex.tasksmanagementsystem.model.User;

public interface UserService {

    User save();

    void update();

    User findByAuthentication();

    User findByEmail();

}
