package com.wallet.monnify.user.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateUserRequest {
    private String email;
    private String password;
    private String bvn;
}
