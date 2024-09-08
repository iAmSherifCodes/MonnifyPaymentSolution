package com.wallet.monnify.wallet.services;

import com.wallet.monnify.user.data.model.User;
import com.wallet.monnify.user.data.repository.UserRepository;
import com.wallet.monnify.wallet.data.model.Account;
import com.wallet.monnify.wallet.data.repository.AccountRepository;
import com.wallet.monnify.wallet.dto.request.CreateRequest;
import com.wallet.monnify.wallet.dto.response.BalanceResponse;
import com.wallet.monnify.wallet.dto.response.CreateResponse;
import com.wallet.monnify.wallet.dto.response.GetBalanceResponseData;
import com.wallet.monnify.wallet.dto.response.ReservedAccountResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.Optional;
import java.util.Random;

@Service @AllArgsConstructor @Slf4j
public class AccountImplementation implements IAccount{

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;


    @Override
    public ReservedAccountResponse createReservedAccount(CreateRequest createRequest) throws Exception {
        createRequest.setAccountReference(generateAccountReference());
        // Drop this in AppConfig
        createRequest.setContractCode("2459409159");
        CreateResponse responseObject;
        try{
            responseObject = makeApiRequest(createRequest);
        } catch (Exception e){
            throw new Exception(e.getMessage()+ "::From Api Call");
        }

        assert responseObject != null;

        String accountNumber = responseObject.getResponseBody().getAccounts().get(0).getAccountNumber();
        String bankName = responseObject.getResponseBody().getAccounts().get(0).getBankName();

        Account newAccount = buildAccount(responseObject, accountNumber, bankName);
        Account savedAccount = accountRepository.save(newAccount);

        Optional<User> foundUser = userRepository.findUserByEmail(createRequest.getCustomerEmail());
        foundUser.ifPresent(user -> {
            user.setAccount(savedAccount);
            userRepository.save(user);
        });

        log.info("{{}}::", responseObject.getResponseBody());
        return responseObject.getResponseBody();
    }

    private static CreateResponse makeApiRequest(CreateRequest createRequest) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setBearerAuth(createRequest.getToken());
        String url = "https://sandbox.monnify.com/api/v2/bank-transfer/reserved-accounts";
        HttpEntity<Object> httpEntity = new HttpEntity<>(createRequest, httpHeaders);
        log.info("{{}}::", httpEntity.toString());
        CreateResponse responseObject = restTemplate.postForObject(url, httpEntity, CreateResponse.class);
        return responseObject;
    }

    private static Account buildAccount(CreateResponse responseObject, String accountNumber, String bankName) {
        Account newAccount = Account.builder()
                .accountName(responseObject.getResponseBody().getAccountName())
                .accountNumber(accountNumber)
                .accountReference(responseObject.getResponseBody().getAccountReference())
                .createdOn(responseObject.getResponseBody().getCreatedOn())
                .reservedAccountType(responseObject.getResponseBody().getReservedAccountType())
                .currencyCode(responseObject.getResponseBody().getCurrencyCode())
                .customerEmail(responseObject.getResponseBody().getCustomerEmail())
                .customerName(responseObject.getResponseBody().getCustomerName())
                .collectionChannel(responseObject.getResponseBody().getCollectionChannel())
                .reservationReference(responseObject.getResponseBody().getReservationReference())
                .status(responseObject.getResponseBody().getStatus())
                .bvn(responseObject.getResponseBody().getBvn())
                .nin(responseObject.getResponseBody().getNin())
                .bankName(bankName)
                .build();
        return newAccount;
    }

    private String generateAccountReference(){
        String prefix = "MONN-";
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";

        Random random = new Random();
        StringBuilder randomString = new StringBuilder(prefix);
        for (int i = 0; i < 3; i++) {
            int index = random.nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }
        for (int i = 0; i < 3; i++) {
            int index = random.nextInt(digits.length());
            randomString.append(digits.charAt(index));
        }
        return randomString.toString();
    }

    @Override
    public ReservedAccountResponse getReservedAccount(String accountReference, String apiToken) throws Exception {
        try {
            return makeApiRequest(accountReference, apiToken);
        }  catch (Exception e){
            throw new Exception(e.getMessage()+ "::From Api Call");
        }
    }

    private static ReservedAccountResponse makeApiRequest(String accountReference, String apiToken) {
        String url = "https://sandbox.monnify.com/api/v2/bank-transfer/reserved-accounts/"+ accountReference;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setBearerAuth(apiToken);
        HttpEntity<Object> httpEntity = new HttpEntity<>(httpHeaders);
        log.info("{{}}::", httpEntity.toString());
        CreateResponse responseObject = restTemplate.exchange(url, HttpMethod.GET, httpEntity, CreateResponse.class).getBody();
        assert responseObject != null;
        return responseObject.getResponseBody();
    }

    @Override
    public BalanceResponse getAccountBalance(String accountNumber, String apiToken) throws Exception {
//        Account foundAccount = accountRepository.findByAccountNumber(accountBalanceRequest.getAccountNumber()).orElseThrow(()->new Exception("Account Number not found"));
        String url = "https://sandbox.monnify.com/api/v1/disbursements/wallet/balance?accountNumber="+accountNumber;
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//        httpHeaders.setBearerAuth(accountBalanceRequest.getToken());
//        HttpEntity<Object> httpEntity = new HttpEntity<>(httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        // Drop these in app config
        String apiKey = "MK_TEST_HB7664REQY";
        String clientSecret = "VPPNGR850N6HXKBLWQ99E12AH34WNW7S";
        String credentials = apiKey + ":" + clientSecret;
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());
        httpHeaders.setBasicAuth(encodedCredentials);
        log.info("{{}}::", httpHeaders.toString());

        HttpEntity<?> httpEntity = new HttpEntity<>("{}", httpHeaders);
        GetBalanceResponseData responseObject = restTemplate.exchange(url, HttpMethod.GET, httpEntity, GetBalanceResponseData.class).getBody();
        assert responseObject != null;
        log.info("{{}}::", responseObject.getResponse().toString());
//        foundAccount.setBalance(responseObject.getResponse().getBalance());
//        accountRepository.save(foundAccount);
        return responseObject.getResponse();
    }
}
