package com.wallet.monnify.wallet.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Getter @Setter @Document @Builder
public class Account {
    @Id
    private String id;
    private String accountName;//
    private String accountNumber;
    private String bankName;
    private BigDecimal balance;
    private String currencyCode;
    @Indexed(unique = true) @JsonProperty(value = "accountReference")
    private String accountReference;
    @JsonProperty(value = "contractCode")
    private String contractCode;
    private String customerEmail;
    private String customerName;
    @JsonProperty(value = "accounts")
    private List<BankAccount> accounts;
    private String collectionChannel;
    private String reservationReference;
    private String reservedAccountType;//
    private String status;
    private String createdOn;//
    private String bvn;
    private String nin;
}
