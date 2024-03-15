package ru.alex.tasksmanagementsystem.service;

import ru.alex.tasksmanagementsystem.model.user.User;

public interface UserService {

    User save();

    void update();

    User findByAuthentication();

    User findByEmail();

}
