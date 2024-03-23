package ru.alex.tasksmanagementsystem.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.alex.tasksmanagementsystem.model.Tokens;
import ru.alex.tasksmanagementsystem.model.response.Token;
import ru.alex.tasksmanagementsystem.security.jwt.factory.AccessTokenFactory;
import ru.alex.tasksmanagementsystem.security.jwt.factory.RefreshTokenFactory;
import ru.alex.tasksmanagementsystem.security.jwt.serializer.AccessTokenSerializer;
import ru.alex.tasksmanagementsystem.security.jwt.serializer.RefreshTokenSerializer;
import ru.alex.tasksmanagementsystem.service.JwtService;

import java.util.Date;

@Service
@AllArgsConstructor
public class DefaultJwtService implements JwtService {

    @Qualifier("accessJwtFactory")
    private AccessTokenFactory accessTokenFactory;

    @Qualifier("refreshJwtFactory")
    private RefreshTokenFactory refreshTokenFactory;

    @Qualifier("defaultAccessTokenSerializer")
    private AccessTokenSerializer accessTokenSerializer;

    @Qualifier("defaultRefreshTokenSerializer")
    private RefreshTokenSerializer refreshTokenSerializer;


    @Override
    public Tokens generateTokens(Authentication authentication) {
        Token refresh = refreshTokenFactory.apply(authentication);
        Token access = accessTokenFactory.apply(refresh);

        return new Tokens(accessTokenSerializer.apply(access), Date.from(access.expireAt()),
                refreshTokenSerializer.apply(refresh), Date.from(refresh.expireAt()));
    }
}
