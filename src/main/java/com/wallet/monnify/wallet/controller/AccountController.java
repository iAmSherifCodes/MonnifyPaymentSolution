package com.wallet.monnify.wallet.controller;

import com.wallet.monnify.wallet.dto.request.CreateReservedAccountRequest;
import com.wallet.monnify.wallet.dto.response.CreateReservedAccountResponse;
import com.wallet.monnify.wallet.dto.response.GetReservedAccountTransactionResponse;
import com.wallet.monnify.wallet.services.IAccount;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1") @AllArgsConstructor
@CrossOrigin("*")
public class AccountController {
    private final IAccount accountService;

    @PostMapping("create-reserved-account")
    public ResponseEntity<CreateReservedAccountResponse> createReservedAccount(@RequestBody CreateReservedAccountRequest createReservedAccountRequest){
        try{
            return new ResponseEntity<>(accountService.createReservedAccount(createReservedAccountRequest), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/get-reserved-account")
    public ResponseEntity<CreateReservedAccountResponse> getReservedAccount(@RequestParam String accountReference){
        try{
            return new ResponseEntity<>(accountService.getReservedAccount(accountReference), HttpStatus.OK);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/get-reserved-account-transactions")
    public ResponseEntity<List<GetReservedAccountTransactionResponse>> getReservedAccountTransactions(@RequestParam String accountReference, @RequestParam  int pageNumber, @RequestParam  int pageSize){
        try{
            return new ResponseEntity<>(accountService.getReservedAccountTransactions(accountReference, pageNumber, pageSize), HttpStatus.OK);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
