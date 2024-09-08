package com.wallet.monnify.wallet.controller;

import com.wallet.monnify.wallet.dto.request.CreateRequest;
import com.wallet.monnify.wallet.dto.response.ReservedAccountResponse;
import com.wallet.monnify.wallet.services.IAccount;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1") @AllArgsConstructor
@CrossOrigin("*")
public class AccountController {
    private final IAccount accountService;

    @PostMapping("create-reserved-account")
    public ResponseEntity<ReservedAccountResponse> createReservedAccount(@RequestBody CreateRequest createRequest){
        try{
            return new ResponseEntity<>(accountService.createReservedAccount(createRequest), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/get-reserved-account")
    public ResponseEntity<ReservedAccountResponse> getReservedAccount(@RequestParam String accountReference, @RequestParam String apiToken){
        try{
            return new ResponseEntity<>(accountService.getReservedAccount(accountReference, apiToken), HttpStatus.CREATED);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

//    @GetMapping("/get-reserved-account?accountNumber={accountNumber}&token={apiToken}")
//    public ResponseEntity<BalanceResponse> getReservedAccountBalance(@PathVariable String accountNumber, @PathVariable String apiToken){
//        try{
//            return new ResponseEntity<>(accountService.getAccountBalance(accountNumber, apiToken), HttpStatus.CREATED);
//        }catch (Exception e) {
//            throw new RuntimeException(e.getMessage());
//        }
//    }
}
