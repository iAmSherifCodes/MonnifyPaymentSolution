package com.wallet.monnify.wallet.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter @Setter @ToString
public class GetTransactionDataCustomerDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @JsonProperty(value = "email")
    private String email;
    @JsonProperty(value = "name")
    private String name;
    @JsonProperty(value = "merchantCode")
    private String merchantCode;
}
