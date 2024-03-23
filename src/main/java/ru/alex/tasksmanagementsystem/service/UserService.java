package ru.alex.tasksmanagementsystem.service;

import org.springframework.security.core.Authentication;
import ru.alex.tasksmanagementsystem.model.user.User;

public interface UserService {

    Authentication save();

    void update();

    User findByAuthentication(Authentication authentication);

    User findByEmail(String email);

}
