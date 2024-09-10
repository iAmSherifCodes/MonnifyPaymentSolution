package com.wallet.monnify.security.providers;

import com.wallet.monnify.utils.AppUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;

import static com.wallet.monnify.security.exceptions.ExceptionMessages.INVALID_CREDENTIALS_EXCEPTION;

@Component @AllArgsConstructor
public class MonnifyAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getPrincipal().toString();
        UserDetails user =userDetailsService.loadUserByUsername(email);

        String password = authentication.getCredentials().toString();

        boolean isValidPassword = AppUtils.passwordEncoder().matches(password, user.getPassword());

        if (isValidPassword){
            Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
            Authentication authenticationResult = new UsernamePasswordAuthenticationToken(email, password, authorities);
            return authenticationResult;

        }

        throw new BadCredentialsException(INVALID_CREDENTIALS_EXCEPTION.getMessage());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
