package ru.alex.tasksmanagementsystem.security.jwt.factory;

import org.springframework.stereotype.Component;
import ru.alex.tasksmanagementsystem.model.response.Token;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

@Component
public class AccessJwtFactory implements AccessTokenFactory {

    private Instant now = Instant.now();
    private Duration duration = Duration.ofMinutes(30);

    /**
     * @param token - information from refresh token
     * @return Token for generate JWT Token in Base64
     * */

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
