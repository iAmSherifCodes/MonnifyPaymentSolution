package com.wallet.monnify.security.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

import static com.wallet.monnify.utils.AppUtils.publicPath;
import static com.wallet.monnify.utils.Constants.*;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class MonnifyAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if( publicPath().contains(request.getServletPath())) filterChain.doFilter(request,response);
        else {
            String authorizationHeader = request.getHeader(AUTHORIZATION);

            String authorizationToken = authorizationHeader.substring(SEVEN);

            JWTVerifier verifier = JWT.require(Algorithm.HMAC512(SECRET))
                    .withIssuer(APP_NAME)
                    .withClaimPresence(ROLES)
                    .build();
            DecodedJWT decodedToken = verifier.verify(authorizationToken);

            List<SimpleGrantedAuthority> authorities = decodedToken.getClaim(ROLES).asList(SimpleGrantedAuthority.class);
            Authentication authentication = new UsernamePasswordAuthenticationToken(null, null, authorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        }
    }
}
