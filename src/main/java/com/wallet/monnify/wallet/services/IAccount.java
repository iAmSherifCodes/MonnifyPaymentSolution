package com.wallet.monnify.wallet.services;

import com.wallet.monnify.wallet.dto.request.CreateRequest;
import com.wallet.monnify.wallet.dto.response.CreateResponse;
import com.wallet.monnify.wallet.dto.response.ReservedAccountResponse;

public interface IAccount {
    CreateResponse createReservedAccount(CreateRequest createRequest);
    ReservedAccountResponse getReservedAccount (String accountReference);
}
