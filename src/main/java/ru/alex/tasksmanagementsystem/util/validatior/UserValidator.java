package ru.alex.tasksmanagementsystem.util.validatior;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.alex.tasksmanagementsystem.dto.UserDto;
import ru.alex.tasksmanagementsystem.service.UserService;
import ru.alex.tasksmanagementsystem.service.impl.DefaultUserService;

import java.util.Optional;

@Component
public class UserValidator implements Validator {

    private UserService userService;

    public UserValidator(DefaultUserService defaultUserService) {
        this.userService = defaultUserService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDto userDto = (UserDto) target;

        if (Optional.of(userService.findByEmail(userDto.getEmail())).isPresent()) {
            errors.rejectValue("email", "", "User with email must be create");
        }
    }
}
