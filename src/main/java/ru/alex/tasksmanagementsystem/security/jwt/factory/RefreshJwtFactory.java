package ru.alex.tasksmanagementsystem.security.jwt.factory;

import jakarta.annotation.Nullable;
import org.springframework.security.core.Authentication;
import ru.alex.tasksmanagementsystem.model.Token;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author Alexander
 * */
public class RefreshJwtFactory implements RefreshTokenFactory {

    private Instant now = Instant.now();
    private Duration duration = Duration.ofDays(90);

    /**
     * @param authentication information about authentication user
     * @return Token for generate JWT Token in Base64
     * */
    @Override
    public Token apply(@Nullable Authentication authentication) {
        List<String> authorities = authentication
                .getAuthorities()
                .stream().map(auth -> "LEADER_" + auth.getAuthority())
                .toList();
        authorities.add("REFRESH_LOGOUT");
        authorities.add("REFRESH_UPDATE");
        return new Token(UUID.randomUUID().toString(), authentication.getName(), authorities, now, now.plus(duration));
    }
}
