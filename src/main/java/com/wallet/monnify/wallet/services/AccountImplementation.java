package com.wallet.monnify.wallet.services;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallet.monnify.config.AppConfig;
import com.wallet.monnify.user.data.model.User;
import com.wallet.monnify.user.data.repository.UserRepository;
import com.wallet.monnify.utils.Constants;
import com.wallet.monnify.wallet.data.model.Account;
import com.wallet.monnify.wallet.data.model.Transaction;
import com.wallet.monnify.wallet.data.repository.AccountRepository;
import com.wallet.monnify.wallet.data.repository.TransactionRepository;
import com.wallet.monnify.wallet.dto.request.CreateReservedAccountRequest;
import com.wallet.monnify.wallet.dto.response.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

import static com.wallet.monnify.utils.Constants.ACCOUNT_NOT_FOUND;
import static com.wallet.monnify.utils.Constants.USER_NOT_FOUND;

@Service @AllArgsConstructor @Slf4j
public class AccountImplementation implements IAccount{

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final AppConfig appConfig;
    private final ObjectMapper objectMapper;
    private final ModelMapper modelMapper;
    private final TransactionRepository transactionRepository;


    @Override
    public CreateReservedAccountResponse createReservedAccount(CreateReservedAccountRequest createReservedAccountRequest) throws Exception {
        createReservedAccountRequest.setAccountReference(generateAccountReference());
        createReservedAccountRequest.setContractCode(appConfig.getMonnifyContractCode());
        User foundUser = userRepository.findUserByEmail(createReservedAccountRequest.getCustomerEmail()).orElseThrow(()-> new Exception(USER_NOT_FOUND));
        try{
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            httpHeaders.setBearerAuth(foundUser.getApiToken());
            HttpEntity<Object> httpEntity = new HttpEntity<>(createReservedAccountRequest, httpHeaders);
            String responseObject = restTemplate.postForObject(appConfig.getMonnifyCreateAccountUrl(), httpEntity, String.class);
            CreateReservedAccountResponse  newReservedAccount = readJsonAndConvertToCreateReservedAccountResponse(responseObject);

            Account newAccount = objectMapper.convertValue(newReservedAccount, Account.class);
            Account savedAccount = accountRepository.save(newAccount);

            foundUser.setAccount(savedAccount);
            userRepository.save(foundUser);
            return newReservedAccount;
        } catch (Exception e){
            throw new Exception(e.getMessage()+ "::From Api Call");
        }

    }

    private String generateAccountReference(){
        String prefix = appConfig.getAccountReferencePrefix();
        String characters = Constants.ALPHABETS;
        String digits = Constants.DIGITS;

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

    @Override @Cacheable(value = "get-reserved-account", key = "#accountReference")
    public CreateReservedAccountResponse getReservedAccount(String accountReference) throws Exception {
        User foundUser = userRepository.findUserByAccount_AccountReference(accountReference).orElseThrow(()-> new Exception(ACCOUNT_NOT_FOUND));
        log.info("USER{{}}", foundUser);

        try {
            String url = appConfig.getMonnifyGetReservedAccountUrl() + accountReference;
            String responseObject = makeGetReservedAccountRequestToMonnifyApi(foundUser.getApiToken(), url);
            return readJsonAndConvertToCreateReservedAccountResponse(responseObject);
        }  catch (Exception e){
            throw new Exception(e.getMessage()+ "::From Api Call");
        }
    }

    private CreateReservedAccountResponse readJsonAndConvertToCreateReservedAccountResponse(String responseObject) {
        try{
            JsonNode rootNode = objectMapper.readTree(responseObject);
            JsonNode responseBody = rootNode.path("responseBody");
            JsonParser parser = objectMapper.treeAsTokens(responseBody);
            CreateReservedAccountResponse createReservedAccountResponse = objectMapper.readValue(parser, CreateReservedAccountResponse.class);
            log.info("{{}}", createReservedAccountResponse.toString());
            return createReservedAccountResponse;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // TODO
    // implement caching using Redis
    @Override  @Cacheable(value = "get-reserved-account-transactions", key = "#accountReference")
    public List<GetReservedAccountTransactionResponse> getReservedAccountTransactions(String accountReference, int pageNumber, int pageSize) throws Exception {
        User foundUser = userRepository.findUserByAccount_AccountReference(accountReference).orElseThrow(()-> new Exception(ACCOUNT_NOT_FOUND));

        String url = appConfig.getMonnifyGetReservedAccountTransactionUrl()+"?accountReference="+accountReference+"&page="+pageNumber+"&size="+pageSize;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setBearerAuth(foundUser.getApiToken());
        HttpEntity<Object> httpEntity = new HttpEntity<>(httpHeaders);
        String responseObject = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class).getBody();
        Map<String, Object> responseMap = objectMapper.readValue(responseObject, new TypeReference<>() {});

        Map<String, Object> responseBody = (Map<String, Object>)responseMap.get("responseBody");
        List<GetReservedAccountTransactionResponse> transactions = objectMapper.convertValue(responseBody.get("content"), new TypeReference<>() {});

        transactions.forEach(transaction -> {
            Transaction newTransaction = modelMapper.map(transaction, Transaction.class);
            transactionRepository.save(newTransaction);
        });

        return transactions;
    }

    private String makeGetReservedAccountRequestToMonnifyApi(String apiToken, String url) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setBearerAuth(apiToken);
        HttpEntity<Object> httpEntity = new HttpEntity<>(httpHeaders);
        log.info("{{}}", httpEntity);
        return restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class).getBody();
    }

}
