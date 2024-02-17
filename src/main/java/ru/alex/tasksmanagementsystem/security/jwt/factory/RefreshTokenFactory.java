package ru.alex.tasksmanagementsystem.security.jwt.factory;

import org.springframework.security.core.Authentication;
import ru.alex.tasksmanagementsystem.model.Token;

import java.util.function.Function;

public interface RefreshTokenFactory extends Function<Authentication, Token> {
}
