package com.wallet.monnify.user.services;

//import com.wallet.monnify.config.AppConfig;
import com.wallet.monnify.user.data.model.User;
import com.wallet.monnify.user.data.repository.UserRepository;
import com.wallet.monnify.user.dto.kyc.BvnRequestObject;
import com.wallet.monnify.user.dto.kyc.BvnResponseData;
import com.wallet.monnify.user.dto.kyc.BvnResponseObject;
import com.wallet.monnify.user.dto.request.CreateUserRequest;
import com.wallet.monnify.user.dto.request.SignInRequest;
import com.wallet.monnify.user.dto.response.AccessTokenData;
import com.wallet.monnify.user.dto.response.AccessTokenResponse;
import com.wallet.monnify.user.dto.response.CreateUserResponse;
import com.wallet.monnify.user.dto.response.SignInResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@Service @Slf4j @AllArgsConstructor
public class UserImplementation implements IUser {


    private final UserRepository userRepository;
//    private AppConfig appConfig;


    // extract the user details from bvn using kyc platform during sign up
    @Override
    public CreateUserResponse createUser(CreateUserRequest createUserRequest) throws Exception {
        String email = createUserRequest.getEmail();
        boolean isPresent = userRepository.findUserByEmail(email).isPresent();
        if (isPresent) {
            throw new Exception("Email already exists");
        }
        String password = createUserRequest.getPassword();
        String bvn = createUserRequest.getBvn();

        BvnResponseData responseEntity = getUserDataFromBvn(bvn);
        User newUser = populateNewUser(email, password, bvn, responseEntity);

        userRepository.save(newUser);

        CreateUserResponse createUserResponse = new CreateUserResponse();
        createUserResponse.setMessage("Hi "+ responseEntity.getFirstName() +"! Sign Up Successful, Please proceed to sign in");

        log.info("{{}} ::", responseEntity.getFirstName());
        return createUserResponse;
    }

    private static User populateNewUser(String email, String password, String bvn, BvnResponseData responseEntity) {
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setBvn(bvn);
        newUser.setFirstName(responseEntity.getFirstName());
        newUser.setLastName(responseEntity.getLastName());
        return newUser;
    }

    private static BvnResponseData getUserDataFromBvn(String bvn) {
        String id = bvn;
        BvnRequestObject bvnRequestObject = new BvnRequestObject(id, true);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("token", "waiWn30I.Wf43omqx07h5Jjy1L0EacRDiVlQHeK734DBj");
        //appConfig.getKycApiKey());
        HttpEntity<BvnRequestObject> httpEntity = new HttpEntity<>(bvnRequestObject, httpHeaders);
        BvnResponseObject responseEntity = restTemplate.postForObject("https://api.sandbox.youverify.co/v2/api/identity/ng/bvn", httpEntity, BvnResponseObject.class);

        assert responseEntity != null;
        return responseEntity.getData();
    }

    @Override
    public SignInResponse signIn(SignInRequest signInRequest) throws Exception {
        String email = signInRequest.getEmail();
        String password = signInRequest.getPassword();

        User foundUser= userRepository.findUserByEmail(email).orElseThrow(()->new Exception("User email not found"));
        AccessTokenResponse apiRequest = null;
        boolean passwordMatch = foundUser.getPassword().equals(password);
        if (passwordMatch) {
            apiRequest = apiRequest();
        } else {
            throw new Exception("Wrong password");
        }
        SignInResponse signInResponse = new SignInResponse();
        signInResponse.setToken(apiRequest.getAccessToken());
        signInResponse.setExpiresIn(apiRequest.getExpiresIn());
        foundUser.setToken(apiRequest().getAccessToken());
        userRepository.save(foundUser);

        return signInResponse;
    }

    private AccessTokenResponse apiRequest(){
        //drop this in appConfig
        String sandboxUrl = "https://sandbox.monnify.com/api/v1/auth/login";

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
        AccessTokenData responseEntity = restTemplate.postForObject(sandboxUrl, httpEntity, AccessTokenData.class);
        assert responseEntity != null;
        log.info("{{}}::", responseEntity.getResponseBody().getAccessToken());


        return responseEntity.getResponseBody();
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }
}