package com.wallet.monnify.wallet.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter @ToString
public class BankAccount implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @JsonProperty(value = "bankCode")
    private String bankCode;
    private String accountNumber;
    private String accountName;
    private String bankName;
}
