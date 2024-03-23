package ru.alex.tasksmanagementsystem.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.alex.tasksmanagementsystem.dto.UserDto;
import ru.alex.tasksmanagementsystem.model.Tokens;
import ru.alex.tasksmanagementsystem.model.response.Token;
import ru.alex.tasksmanagementsystem.repository.UserRepository;
import ru.alex.tasksmanagementsystem.security.jwt.deserializer.RefreshTokenDeserializer;
import ru.alex.tasksmanagementsystem.security.jwt.factory.AccessTokenFactory;
import ru.alex.tasksmanagementsystem.security.jwt.factory.RefreshTokenFactory;
import ru.alex.tasksmanagementsystem.security.jwt.serializer.AccessTokenSerializer;
import ru.alex.tasksmanagementsystem.security.jwt.serializer.RefreshTokenSerializer;
import ru.alex.tasksmanagementsystem.service.JwtService;
import ru.alex.tasksmanagementsystem.service.LoginService;

import javax.security.auth.login.FailedLoginException;
import java.util.Date;
import java.util.Objects;

@Service
@AllArgsConstructor
public class DefaultLoginService implements LoginService {

    private AuthenticationManager authenticationManager;

   private JwtService jwtService;

    @Override
    public Tokens login(UserDto userDto, BindingResult bindingResult) throws FailedLoginException {

        if (bindingResult.hasErrors()) {
            throw new FailedLoginException(Objects
                    .requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        final var authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));

        return jwtService.generateTokens(authentication);
    }

}
