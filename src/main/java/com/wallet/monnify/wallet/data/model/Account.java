package com.wallet.monnify.wallet.data.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Getter @Setter @Document
public class Account {
    @Id
    private String id;
    private String accountName;
    private String accountNumber;
    private BigDecimal balance;
    private String currency;
    @Indexed(unique = true)
    private String accountReference;
}
