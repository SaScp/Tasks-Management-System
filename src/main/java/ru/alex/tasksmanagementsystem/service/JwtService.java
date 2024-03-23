package ru.alex.tasksmanagementsystem.service;

import org.springframework.security.core.Authentication;
import ru.alex.tasksmanagementsystem.model.Tokens;

public interface JwtService {


    Tokens generateTokens(Authentication authentication);

}
