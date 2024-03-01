package ru.alex.tasksmanagementsystem.security.authentication;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import ru.alex.tasksmanagementsystem.model.Token;

import java.util.Collection;

public class UserDetailsWithToken extends User {

    private final Token token;

    public UserDetailsWithToken(String username, String password, Collection<? extends GrantedAuthority> authorities, Token token) {
        super(username, password, authorities);
        this.token = token;
    }

    public UserDetailsWithToken(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, Token token) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.token = token;
    }
}
