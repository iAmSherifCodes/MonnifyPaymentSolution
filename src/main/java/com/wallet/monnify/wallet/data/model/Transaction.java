package com.wallet.monnify.wallet.data.model;

import com.wallet.monnify.wallet.dto.response.GetTransactionDataCustomerDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter @Setter @ToString @Document //@RedisHash(value = "Transaction")
public class Transaction implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private GetTransactionDataCustomerDTO customerDTO;
    private Double providerAmount;
    private String paymentMethod;
    private String createdOn;
    private BigDecimal amount;
    private Boolean flagged;
    private String providerCode;
    private Double fee;
    private String currencyCode;
    private String completedOn;
    private String paymentDescription;
    private String paymentStatus;
    private String transactionReference;
    private String paymentReference;
    private String merchantCode;
    private String merchantName;
    private BigDecimal payableAmount;
    private BigDecimal amountPaid;
    private Boolean completed;
}
