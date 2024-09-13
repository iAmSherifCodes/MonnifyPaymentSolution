package com.wallet.monnify.user.data.model;

import com.wallet.monnify.wallet.data.model.Account;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;

@Getter @Builder
@Document(value = "users") @Setter
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
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
    private String apiToken;
    private Account account;
}
