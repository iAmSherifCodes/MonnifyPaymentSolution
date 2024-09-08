package com.wallet.monnify.user.controller;

import com.wallet.monnify.user.dto.request.CreateUserRequest;
import com.wallet.monnify.user.dto.request.SignInRequest;
import com.wallet.monnify.user.services.IUser;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/v1") @AllArgsConstructor @CrossOrigin("*")
public class UserController {
    private final IUser userService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> createProfile(@RequestBody CreateUserRequest createUserRequest){
        try {
            return new ResponseEntity<>(userService.createUser(createUserRequest), HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody SignInRequest signInRequest){
        try{
            return new ResponseEntity<>(userService.signIn(signInRequest), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}