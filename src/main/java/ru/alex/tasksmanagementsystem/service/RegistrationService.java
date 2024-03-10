package ru.alex.tasksmanagementsystem.service;

import org.springframework.validation.BindingResult;
import ru.alex.tasksmanagementsystem.dto.UserDto;
import ru.alex.tasksmanagementsystem.model.Tokens;

public interface RegistrationService {

    Tokens registration(UserDto userDto, BindingResult bindingResult);
}
