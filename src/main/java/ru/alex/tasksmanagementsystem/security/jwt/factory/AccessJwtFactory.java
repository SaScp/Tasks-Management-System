package ru.alex.tasksmanagementsystem.security.jwt.factory;

import ru.alex.tasksmanagementsystem.model.Token;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

public class AccessJwtFactory implements AccessTokenFactory {

    private Instant now = Instant.now();
    private Duration duration = Duration.ofMinutes(30);

    @Override
    public Token apply(Token token) {
        return new Token(UUID.randomUUID().toString(), token.subject(),
                token.authorities()
                        .stream()
                        .filter(role -> role.startsWith("LEADER_"))
                        .map(role -> role.replace("LEADER_", ""))
                        .toList(), now, now.plus(duration));
    }
}
