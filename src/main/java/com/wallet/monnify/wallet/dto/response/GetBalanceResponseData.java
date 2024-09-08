package com.wallet.monnify.wallet.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString
public class GetBalanceResponseData {
    @JsonProperty(value = "responseBody")
    private BalanceResponse response;
}
