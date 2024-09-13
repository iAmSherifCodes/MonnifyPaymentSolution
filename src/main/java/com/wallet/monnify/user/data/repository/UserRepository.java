package com.wallet.monnify.user.data.repository;

import com.wallet.monnify.user.data.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface
UserRepository extends MongoRepository<User, String> {

    Optional<User> findUserByEmail(String email);
    @Query("{'account.accountReference':  ?0}")
    Optional<User> findUserByAccount_AccountReference(String accountReference);
}
