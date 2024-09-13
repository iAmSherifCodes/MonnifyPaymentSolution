package com.wallet.monnify.wallet.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wallet.monnify.wallet.data.model.BankAccount;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Getter @Setter @ToString
public class CreateReservedAccountResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @JsonProperty(value = "contractCode")
    private String contractCode;
    @JsonProperty(value = "accountReference")
    private String accountReference;
    private String accountName;
    private String currencyCode;
    private BigDecimal balance;
    private String customerEmail;
    private String customerName;
    @JsonProperty(value = "accounts")
    private List<BankAccount> accounts;
    private String collectionChannel;
    private String reservationReference;
    private String reservedAccountType;
    private String status;
    private String createdOn;
}
