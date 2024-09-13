package com.wallet.monnify.wallet.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
@Getter @Setter @ToString
public class GetReservedAccountTransactionResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @JsonProperty(value = "customerDTO")
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
