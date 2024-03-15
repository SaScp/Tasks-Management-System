package ru.alex.tasksmanagementsystem.controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.alex.tasksmanagementsystem.dto.UserDto;
import ru.alex.tasksmanagementsystem.model.Create;
import ru.alex.tasksmanagementsystem.model.Find;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @PostMapping("/registration")
    public String registration(@RequestBody @Validated(Create.class) UserDto userDto, BindingResult bindingResult) {
        return "registration";
    }

    @PostMapping("/login")
    public String login(@RequestBody @Validated(Find.class) UserDto userDto, BindingResult bindingResult) {
        return "login";
    }
}
