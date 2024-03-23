package ru.alex.tasksmanagementsystem.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.alex.tasksmanagementsystem.dto.UserDto;
import ru.alex.tasksmanagementsystem.model.Tokens;
import ru.alex.tasksmanagementsystem.security.jwt.serializer.AccessTokenSerializer;
import ru.alex.tasksmanagementsystem.security.jwt.serializer.RefreshTokenSerializer;
import ru.alex.tasksmanagementsystem.service.JwtService;
import ru.alex.tasksmanagementsystem.service.RegistrationService;
import ru.alex.tasksmanagementsystem.service.UserService;
import ru.alex.tasksmanagementsystem.util.exception.RegistrationException;

import java.util.Objects;

@Service
public class DefaultRegistrationService implements RegistrationService {

    private UserService userService;

    private JwtService jwtService;

    public DefaultRegistrationService(DefaultUserService defaultUserService, JwtService jwtService) {
        this.userService = defaultUserService;
        this.jwtService = jwtService;
    }

    @Override
    public Tokens registration(UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new RegistrationException(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        return jwtService.generateTokens(userService.save());
    }
}
