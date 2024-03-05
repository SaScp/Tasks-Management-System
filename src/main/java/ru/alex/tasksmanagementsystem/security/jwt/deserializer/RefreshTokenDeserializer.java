package ru.alex.tasksmanagementsystem.security.jwt.deserializer;

import ru.alex.tasksmanagementsystem.model.Token;

import java.util.function.Function;

public interface RefreshTokenDeserializer extends Function<String, Token> {
}
