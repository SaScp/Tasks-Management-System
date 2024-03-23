package ru.alex.tasksmanagementsystem.service;

import org.springframework.validation.BindingResult;
import ru.alex.tasksmanagementsystem.dto.UserDto;
import ru.alex.tasksmanagementsystem.model.Tokens;

import javax.security.auth.login.FailedLoginException;

public interface LoginService {

    Tokens login(UserDto userDto, BindingResult bindingResult) throws FailedLoginException;
}
