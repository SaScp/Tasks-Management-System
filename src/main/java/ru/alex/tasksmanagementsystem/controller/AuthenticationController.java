package ru.alex.tasksmanagementsystem.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.alex.tasksmanagementsystem.dto.UserDto;
import ru.alex.tasksmanagementsystem.model.User;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @PostMapping("/registration")
    public String registration(@RequestBody UserDto userDto, BindingResult bindingResult) {
        return "registration";
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDto userDto, BindingResult bindingResult) {
        return "login";
    }
}
