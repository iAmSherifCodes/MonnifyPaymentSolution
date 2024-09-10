package com.wallet.monnify.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.util.List;
import static com.wallet.monnify.utils.Constants.*;


public class JwtUtils {
    public static String generateAccessToken(List<String> authorities){
        String token = JWT.create()
                .withClaim(ROLES, authorities)
                .withIssuer(APP_NAME)
                .sign(Algorithm.HMAC512(SECRET));

        return token;


    }
}
