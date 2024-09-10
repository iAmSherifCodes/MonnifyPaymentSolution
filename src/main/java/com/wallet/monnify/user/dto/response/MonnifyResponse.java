package com.wallet.monnify.user.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter @Builder @Getter
public class MonnifyResponse <T>{
    private T message;
}
