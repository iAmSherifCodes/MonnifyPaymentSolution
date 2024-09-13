package com.wallet.monnify.wallet.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter @Getter @ToString
public class GetReservedAccountTransactionJsonResponse {
    @JsonProperty(value = "content")
    private List<GetReservedAccountTransactionResponse> response;
}
