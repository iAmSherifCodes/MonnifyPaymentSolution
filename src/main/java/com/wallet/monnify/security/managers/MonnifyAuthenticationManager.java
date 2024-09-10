package com.wallet.monnify.security.managers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import static com.wallet.monnify.security.exceptions.ExceptionMessages.AUTHENTICATION_NOT_SUPPORTED;

@Component @RequiredArgsConstructor
public class MonnifyAuthenticationManager implements AuthenticationManager {
    private final AuthenticationProvider authenticationProvider;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authenticationProvider.supports(authentication.getClass())){
            return authenticationProvider.authenticate(authentication);
        }
        throw new RuntimeException(AUTHENTICATION_NOT_SUPPORTED.getMessage());

    }
}
