package com.wallet.monnify.wallet.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter @ToString
public class BankAccount {
    @JsonProperty(value = "bankCode")
    private String bankCode;
    private String accountNumber;
    private String accountName;
    private String bankName;
}
