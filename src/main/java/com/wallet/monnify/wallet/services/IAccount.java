package com.wallet.monnify.wallet.services;

import com.wallet.monnify.wallet.dto.request.CreateReservedAccountRequest;
import com.wallet.monnify.wallet.dto.response.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAccount {
    CreateReservedAccountResponse createReservedAccount(CreateReservedAccountRequest createReservedAccountRequest) throws Exception;
    CreateReservedAccountResponse getReservedAccount (String accountReference) throws Exception;
    List<GetReservedAccountTransactionResponse> getReservedAccountTransactions (String accountReference, int pageNumber, int pageSize) throws Exception;
}
