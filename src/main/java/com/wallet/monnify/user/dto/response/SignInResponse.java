package com.wallet.monnify.user.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SignInResponse {
    private String token;
    private int expiresIn;
}
