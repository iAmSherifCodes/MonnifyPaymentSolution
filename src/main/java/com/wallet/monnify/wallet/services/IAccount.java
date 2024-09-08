package com.wallet.monnify.wallet.services;

import com.wallet.monnify.wallet.dto.request.CreateRequest;
import com.wallet.monnify.wallet.dto.request.GetAccountBalanceRequest;
import com.wallet.monnify.wallet.dto.request.GetReservedAccountRequest;
import com.wallet.monnify.wallet.dto.response.BalanceResponse;
import com.wallet.monnify.wallet.dto.response.ReservedAccountResponse;

public interface IAccount {
    ReservedAccountResponse createReservedAccount(CreateRequest createRequest) throws Exception;
    ReservedAccountResponse getReservedAccount (String accountReference, String apiToken) throws Exception;
    BalanceResponse getAccountBalance(String accountNumber, String apiToken) throws Exception;
}
