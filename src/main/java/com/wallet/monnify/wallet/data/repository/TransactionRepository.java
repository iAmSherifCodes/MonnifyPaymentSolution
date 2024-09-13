package com.wallet.monnify.wallet.data.repository;

import com.wallet.monnify.wallet.data.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction, String>{

}