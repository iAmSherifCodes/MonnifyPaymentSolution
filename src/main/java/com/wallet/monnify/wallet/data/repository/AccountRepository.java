package com.wallet.monnify.wallet.data.repository;

import com.wallet.monnify.wallet.data.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {
}
