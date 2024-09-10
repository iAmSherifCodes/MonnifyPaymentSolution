package com.wallet.monnify.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallet.monnify.user.dto.request.SignInRequest;
import com.wallet.monnify.user.dto.response.MonnifyResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import static com.wallet.monnify.utils.JwtUtils.generateAccessToken;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
public class MonnifyAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response){

        try{
            InputStream inputStream = request.getInputStream();
            SignInRequest loginRequest =  objectMapper.readValue(inputStream, SignInRequest.class);

            String email = loginRequest.getEmail();
            String password = loginRequest.getPassword();


            Authentication toAuthenticate = new UsernamePasswordAuthenticationToken(email, password);

            Authentication authenticationResult  = authenticationManager.authenticate(toAuthenticate);

            SecurityContextHolder.getContext().setAuthentication(authenticationResult);

            return authenticationResult;

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain filterChain,
                                            Authentication authResult) throws IOException {

        Collection<? extends GrantedAuthority> roles = authResult.getAuthorities();
        var role = roles
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        String token = generateAccessToken(role);
        var apiResponse = MonnifyResponse.builder().message(token).build();

        response.setContentType(APPLICATION_JSON_VALUE);
        objectMapper.writeValue(response.getOutputStream(), apiResponse);
   }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException {

        response.setContentType(APPLICATION_JSON_VALUE);
        var apiResponse =  MonnifyResponse.builder().message(failed.getMessage()).build();
        objectMapper.writeValue(response.getOutputStream(), apiResponse);
    }
}
