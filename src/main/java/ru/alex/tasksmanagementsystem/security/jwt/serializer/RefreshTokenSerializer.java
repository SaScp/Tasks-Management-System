package ru.alex.tasksmanagementsystem.security.jwt.serializer;

import ru.alex.tasksmanagementsystem.model.response.Token;

import java.util.function.Function;

public interface RefreshTokenSerializer extends Function<Token, String> {
}
