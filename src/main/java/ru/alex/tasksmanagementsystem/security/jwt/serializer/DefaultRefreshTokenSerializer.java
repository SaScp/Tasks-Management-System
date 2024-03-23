package ru.alex.tasksmanagementsystem.security.jwt.serializer;

import org.springframework.stereotype.Component;
import ru.alex.tasksmanagementsystem.model.response.Token;

@Component
public class DefaultRefreshTokenSerializer implements RefreshTokenSerializer {
    @Override
    public String apply(Token token) {
        return "";
    }
}
