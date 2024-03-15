package ru.alex.tasksmanagementsystem.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.alex.tasksmanagementsystem.dto.UserDto;
import ru.alex.tasksmanagementsystem.model.Tokens;
import ru.alex.tasksmanagementsystem.service.LoginService;

@Service
public class DefaultLoginService implements LoginService {
    @Override
    public Tokens login(UserDto userDto, BindingResult bindingResult) {
        return null;
    }
}
