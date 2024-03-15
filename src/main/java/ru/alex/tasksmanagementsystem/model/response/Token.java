package ru.alex.tasksmanagementsystem.model.response;

import java.time.Instant;
import java.util.List;

public record Token(String UUID,
                    String subject,
                    List<String> authorities,
                    Instant issuerAt,
                    Instant expireAt) {
}
