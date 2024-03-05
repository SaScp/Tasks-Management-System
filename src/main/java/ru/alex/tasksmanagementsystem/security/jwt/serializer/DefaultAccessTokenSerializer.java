package ru.alex.tasksmanagementsystem.security.jwt.serializer;

import ru.alex.tasksmanagementsystem.model.Token;

public class DefaultAccessTokenSerializer implements AccessTokenSerializer {
    @Override
    public String apply(Token token) {
        return "";
    }
}
