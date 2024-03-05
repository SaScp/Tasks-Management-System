package ru.alex.tasksmanagementsystem.security.jwt.serializer;

import ru.alex.tasksmanagementsystem.model.Token;

public class DefaultRefreshTokenSerializer implements RefreshTokenSerializer {
    @Override
    public String apply(Token token) {
        return "";
    }
}
