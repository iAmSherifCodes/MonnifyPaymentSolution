package com.wallet.monnify.user.services;

import com.wallet.monnify.user.dto.request.CreateUserRequest;
import com.wallet.monnify.user.dto.request.SignInRequest;
import com.wallet.monnify.user.dto.response.CreateUserResponse;
import com.wallet.monnify.user.dto.response.SignInResponse;

public interface IUser {
    CreateUserResponse createUser(CreateUserRequest createUserRequest) throws Exception;
    SignInResponse signIn(SignInRequest signInRequest) throws Exception;
    void deleteAll();
}
