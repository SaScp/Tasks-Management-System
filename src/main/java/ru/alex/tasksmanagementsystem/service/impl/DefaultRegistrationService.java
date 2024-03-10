package ru.alex.tasksmanagementsystem.service.impl;

import org.springframework.validation.BindingResult;
import ru.alex.tasksmanagementsystem.dto.UserDto;
import ru.alex.tasksmanagementsystem.model.Tokens;
import ru.alex.tasksmanagementsystem.service.RegistrationService;

public class DefaultRegistrationService implements RegistrationService {
    @Override
    public Tokens registration(UserDto userDto, BindingResult bindingResult) {
        return null;
    }
}
