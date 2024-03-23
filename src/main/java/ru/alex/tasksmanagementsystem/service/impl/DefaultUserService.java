package ru.alex.tasksmanagementsystem.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.alex.tasksmanagementsystem.model.user.User;
import ru.alex.tasksmanagementsystem.service.UserService;

@Service
public class DefaultUserService implements UserService {
    @Override
    public Authentication save() {
        return null;
    }

    @Override
    public void update() {

    }

    @Override
    public User findByAuthentication(Authentication authentication) {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }
}
