package com.wallet.monnify.wallet.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class CreateResponse {
    @JsonProperty(value = "responseBody")
    private ReservedAccountResponse responseBody;
}
