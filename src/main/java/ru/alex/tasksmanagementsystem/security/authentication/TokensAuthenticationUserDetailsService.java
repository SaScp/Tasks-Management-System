package ru.alex.tasksmanagementsystem.security.authentication;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import ru.alex.tasksmanagementsystem.model.Token;

/**
 * @author Alexander
 */

public class TokensAuthenticationUserDetailsService implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {


    private final JdbcTemplate jdbcTemplate;

    public TokensAuthenticationUserDetailsService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**@return UserDetails from authentication Token
     * @param preAuthenticatedAuthenticationToken authentication info */
    @Override
    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken preAuthenticatedAuthenticationToken) throws UsernameNotFoundException {
        if (preAuthenticatedAuthenticationToken.getPrincipal() instanceof Token token) {

            return  new UserDetailsWithToken(token.subject(), "", true,
                    Boolean.TRUE.equals(this.jdbcTemplate.queryForObject("select exists(select id from t_deactivated_token where id = ?)",
                            Boolean.class, token.UUID())), true, true,
                    token.authorities().stream().map(SimpleGrantedAuthority::new).toList(), token);
        }
        return null;
    }
}


