package com.wallet.monnify.user.dto.kyc;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class BvnResponseObject {
    @JsonProperty(value = "success")
    private boolean success;
    @JsonProperty(value = "data")
    private BvnResponseData data;
}

