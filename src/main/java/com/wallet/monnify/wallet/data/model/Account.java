package com.wallet.monnify.wallet.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Document(value = "accounts")
@Data
public class Account implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private String accountName;
    private String accountNumber;
    private String bankName;
    private BigDecimal balance;
    private String currencyCode;
    @JsonProperty(value = "accountReference") @Indexed(unique = true)
    private String accountReference;
    @JsonProperty(value = "contractCode")
    private String contractCode;
    private String customerEmail;
    private String customerName;
    @JsonProperty(value = "accounts")
    private List<BankAccount> accounts;
    private String collectionChannel;
    private String reservationReference;
    private String reservedAccountType;
    private String status;
    private String createdOn;//
    private String bvn;
    private String nin;
}
