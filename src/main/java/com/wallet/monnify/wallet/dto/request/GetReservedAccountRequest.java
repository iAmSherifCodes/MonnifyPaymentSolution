package com.wallet.monnify.wallet.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter @Builder
public class GetReservedAccountRequest {
    private String accountReference;
    private String token;
}
