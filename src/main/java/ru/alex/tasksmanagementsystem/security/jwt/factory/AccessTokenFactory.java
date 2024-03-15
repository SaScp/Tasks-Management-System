package ru.alex.tasksmanagementsystem.security.jwt.factory;

import ru.alex.tasksmanagementsystem.model.response.Token;

import java.util.function.Function;

public interface AccessTokenFactory extends Function<Token, Token> {
}
