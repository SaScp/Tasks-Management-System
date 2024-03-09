package ru.alex.tasksmanagementsystem.model;

import java.util.Date;

public record Tokens(String accessToken, Date accessExpireAt, String refreshToken, Date refreshExpireAt) {
}
