package com.wallet.monnify.userTest;

import com.wallet.monnify.user.dto.request.CreateUserRequest;
import com.wallet.monnify.user.dto.response.CreateUserResponse;
import com.wallet.monnify.user.services.IUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Slf4j
public class CreateUserTest {
    private final IUser userImpl;


    @Autowired
    public CreateUserTest(IUser userImpl) {
        this.userImpl = userImpl;
    }

//    @AfterEach
//    public void tearDown() {
//        userImpl.deleteAll();
//    }


    @Test
    public void testSignUp() throws Exception {
        String bvn = "11111111111";
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setBvn(bvn);
        createUserRequest.setEmail("test@test.com");
        createUserRequest.setPassword("password");

        CreateUserResponse createUserResponse = userImpl.createUser(createUserRequest);
        assertEquals(createUserResponse.getMessage(), "Hi "+ "John" + "! Sign Up Successful, Please proceed to sign in");

    }

    @Test
    public void testEmailExists() throws Exception {

        String bvn = "11111111111";
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setBvn(bvn);
        createUserRequest.setEmail("test@test.com");
        createUserRequest.setPassword("password");

        CreateUserResponse createUserResponse = userImpl.createUser(createUserRequest);

        String bvn2 = "11111111111";
        CreateUserRequest createUserRequest2 = new CreateUserRequest();
        createUserRequest2.setBvn(bvn2);
        createUserRequest2.setEmail("test@test.com");
        createUserRequest2.setPassword("password");

        assertThrows(Exception.class, ()->userImpl.createUser(createUserRequest2));

    }
}
