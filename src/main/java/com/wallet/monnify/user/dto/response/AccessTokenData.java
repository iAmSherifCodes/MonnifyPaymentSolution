package com.wallet.monnify.user.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AccessTokenData {
    @JsonProperty(value = "responseBody")
    private AccessTokenResponse responseBody;
}
