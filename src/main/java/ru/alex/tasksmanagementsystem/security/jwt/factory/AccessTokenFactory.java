package ru.alex.tasksmanagementsystem.security.jwt.factory;

import ru.alex.tasksmanagementsystem.model.Token;

import java.util.function.Function;

public interface AccessTokenFactory extends Function<Token, Token> {
}
