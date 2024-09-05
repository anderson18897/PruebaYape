package com.yape.transaction.service;

import com.yape.transaction.model.Transaction;
import com.yape.transaction.dto.TransactionRequest;
import java.util.UUID;

public interface TransactionService {
    Transaction createTransaction(TransactionRequest request);
    Transaction getTransaction(UUID transactionExternalId);
}
