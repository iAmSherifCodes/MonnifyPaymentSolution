package com.wallet.monnify.user.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class AccessTokenResponse {
    @JsonProperty(value = "accessToken")
    private String accessToken;
    @JsonProperty(value = "expiresIn")
    private int expiresIn;
}
