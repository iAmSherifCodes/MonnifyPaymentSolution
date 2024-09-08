package com.wallet.monnify.user.data.model;

import com.wallet.monnify.wallet.data.model.Account;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter @Document @Setter
public class User {
    @Id
    private String id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    @Indexed(unique = true)
    private String email;
    private String bvn;
    private String nin;
    private String phone;
    private String token;
    private Account account;
}
