package com.wallet.monnify.wallet.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter @Setter @ToString
public class BalanceResponse {
    private String message;
    @JsonProperty(value = "availableBalance")
    private BigDecimal balance;
}
