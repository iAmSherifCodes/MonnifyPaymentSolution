package com.wallet.monnify.security;

import com.wallet.monnify.security.filters.MonnifyAuthenticationFilter;
import com.wallet.monnify.security.filters.MonnifyAuthorizationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.wallet.monnify.utils.Role.USER;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration @AllArgsConstructor @EnableWebSecurity(debug = true)
public class MonnifySecurityConfig {
    private final AuthenticationManager authenticationManager;

    @Bean
    public SecurityFilterChain securityFilter(HttpSecurity httpSecurity) throws Exception {
//        final List<String> publicEndPoints = AppUtils.publicPath();
        return httpSecurity
                .addFilterAt(new MonnifyAuthenticationFilter(authenticationManager),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new MonnifyAuthorizationFilter(), MonnifyAuthenticationFilter.class)
                .sessionManagement(customizer->customizer.sessionCreationPolicy(STATELESS))
                .csrf(c->c.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(c->c.requestMatchers(POST,  "/sign-in",
                        "/v1/sign-up").permitAll())
                .authorizeHttpRequests(c->c.anyRequest().hasAuthority(USER.name()))
                .build();
    }

    private String[] publicEndpoints(){
        return new String[]{
                "/api/v1", "/sign-in",
                "/v1/sign-up"
        };
    }
}
