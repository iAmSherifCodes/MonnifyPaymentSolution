package com.wallet.monnify.wallet;

import com.wallet.monnify.user.dto.request.CreateUserRequest;
import com.wallet.monnify.user.dto.request.SignInRequest;
import com.wallet.monnify.user.dto.response.CreateUserResponse;
import com.wallet.monnify.user.dto.response.SignInResponse;
import com.wallet.monnify.user.services.IUser;
import com.wallet.monnify.wallet.dto.request.CreateRequest;
import com.wallet.monnify.wallet.dto.request.GetAccountBalanceRequest;
import com.wallet.monnify.wallet.dto.request.GetReservedAccountRequest;
import com.wallet.monnify.wallet.dto.response.BalanceResponse;
import com.wallet.monnify.wallet.dto.response.ReservedAccountResponse;
import com.wallet.monnify.wallet.services.IAccount;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest @Slf4j
public class CreateReservedAccountTest {
    private final IAccount accountImplementation;
    private final IUser userImplementation;

    @Autowired
    public CreateReservedAccountTest(IAccount accountImplementation, IUser userImplementation) {
        this.accountImplementation = accountImplementation;
        this.userImplementation = userImplementation;
    }

    @Test
    void testCreateReservedAccount() throws Exception {
        String bvn = "11111111111";
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setBvn(bvn);
        createUserRequest.setEmail("test@test.com");
        createUserRequest.setPassword("password");

        CreateUserResponse createUserResponse = userImplementation.createUser(createUserRequest);
        assertEquals(createUserResponse.getMessage(), "Hi "+ "John" + "! Sign Up Successful, Please proceed to sign in");

        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setPassword("password");
        signInRequest.setEmail("test@test.com");

        SignInResponse res = userImplementation.signIn(signInRequest);

        CreateRequest createRequest = new CreateRequest();
        createRequest.setCurrencyCode("NGN");
        createRequest.setAccountName("John Doe");
        createRequest.setBvn("54848484888");
        createRequest.setCustomerEmail("test@test.com");
        createRequest.setCustomerName("John Doe");
        createRequest.setGetAllAvailableBanks(false);
        createRequest.setBvn("54848484888");
        createRequest.setNin("34848484058");
        createRequest.setToken(res.getToken());
        List<String> preferredBanks = List.of("232");
        createRequest.setPreferredBanks(preferredBanks);

        ReservedAccountResponse createResponse = accountImplementation.createReservedAccount(createRequest);
        assertThat(createResponse).isNotNull();
        assertThat(createResponse.getAccountName()).isEqualTo("John");
    }

    @Test
    void testGetReservedAccount() throws Exception {
//        MONN-IdA758
        String bvn = "11111111111";
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setBvn(bvn);
        createUserRequest.setEmail("test2@test.com");
        createUserRequest.setPassword("password");

        CreateUserResponse createUserResponse = userImplementation.createUser(createUserRequest);
        assertEquals(createUserResponse.getMessage(), "Hi "+ "John" + "! Sign Up Successful, Please proceed to sign in");

        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setPassword("password");
        signInRequest.setEmail("test2@test.com");

        SignInResponse res = userImplementation.signIn(signInRequest);

        CreateRequest createRequest = new CreateRequest();
        createRequest.setCurrencyCode("NGN");
        createRequest.setAccountName("John Doe");
        createRequest.setBvn("54848484888");
        createRequest.setCustomerEmail("test2@test.com");
        createRequest.setCustomerName("John Doe");
        createRequest.setGetAllAvailableBanks(false);
        createRequest.setBvn("54848484888");
        createRequest.setNin("34848484058");
        createRequest.setToken(res.getToken());
        List<String> preferredBanks = List.of("232");
        createRequest.setPreferredBanks(preferredBanks);

        ReservedAccountResponse createResponse = accountImplementation.createReservedAccount(createRequest);


        ReservedAccountResponse getReservedAccountResponse =  accountImplementation.getReservedAccount(createResponse.getAccountReference(),res.getToken());
        assertThat(getReservedAccountResponse).isNotNull();
        assertThat(getReservedAccountResponse.getAccountName()).isEqualTo("Sherif Olanrewaju Awofiranye-Joh");
        assertThat(getReservedAccountResponse.getAccountReference()).isEqualTo(createResponse.getAccountReference());
    }

    @Test
    void testGetBalance() throws Exception {
        String bvn = "11111111111";
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setBvn(bvn);
        createUserRequest.setEmail("test20@test.com");
        createUserRequest.setPassword("password");

        CreateUserResponse createUserResponse = userImplementation.createUser(createUserRequest);
        assertEquals(createUserResponse.getMessage(), "Hi "+ "John" + "! Sign Up Successful, Please proceed to sign in");

        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setPassword("password");
        signInRequest.setEmail("test20@test.com");

        SignInResponse res = userImplementation.signIn(signInRequest);

        CreateRequest createRequest = new CreateRequest();
        createRequest.setCurrencyCode("NGN");
        createRequest.setAccountName("John Doe");
        createRequest.setBvn("54848484888");
        createRequest.setCustomerEmail("test20@test.com");
        createRequest.setCustomerName("John Doe");
        createRequest.setGetAllAvailableBanks(false);
        createRequest.setBvn("54848484888");
        createRequest.setNin("34848484058");
        createRequest.setToken(res.getToken());
        List<String> preferredBanks = List.of("232");
        createRequest.setPreferredBanks(preferredBanks);

        ReservedAccountResponse createResponse = accountImplementation.createReservedAccount(createRequest);
//        log.info("{{}}", createResponse.getAccounts().get(0).getAccountNumber());7756196331
//        BalanceResponse balanceResponse = accountImplementation.getAccountBalance(createResponse.getAccounts().get(0).getAccountNumber(), res.getToken());
//        assertThat(balanceResponse.getBalance()).isNotNull();
    }
}
