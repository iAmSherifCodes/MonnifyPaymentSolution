package com.wallet.monnify.userTest;

import com.wallet.monnify.user.dto.request.SignInRequest;
import com.wallet.monnify.user.dto.response.SignInResponse;
import com.wallet.monnify.user.services.IUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest @Slf4j
public class ApiRequestTest {

    private final IUser iUser;

    @Autowired
    public ApiRequestTest(IUser iUser) {
        this.iUser = iUser;
    }

    @Test
    public void testApiTokenRequest() throws Exception {
        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setPassword("password");
        signInRequest.setEmail("test@test.com");

        SignInResponse res = iUser.signIn(signInRequest);
        assertThat(res).isNotNull();
        assertThat(res.getToken()).isNotNull();
        assertThat(res.getExpiresIn()).isNotNull();
    }
}
