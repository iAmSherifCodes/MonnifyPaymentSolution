package com.wallet.monnify.wallet.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class GetAccountBalanceRequest {
    private String accountNumber;
    private String token;
}
