package com.yape.transaction.service.impl;

import com.yape.transaction.exception.TransactionNotFoundException;
import com.yape.transaction.model.Transaction;
import com.yape.transaction.dto.TransactionRequest;
import com.yape.transaction.repository.TransactionRepository;
import com.yape.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private KafkaTemplate<String, Transaction> kafkaTemplate;

    @Override
    public Transaction createTransaction(TransactionRequest request) {
        Transaction transaction = new Transaction();
        transaction.setAccountExternalIdDebit(UUID.fromString(request.getAccountExternalIdDebit()));
        transaction.setAccountExternalIdCredit(UUID.fromString(request.getAccountExternalIdCredit()));
        transaction.setTransferTypeId(request.getTransferTypeId());
        transaction.setTransactionValue(request.getTransactionValue());
        transaction.setStatus("pending");
        transaction.setCreatedAt(LocalDateTime.now());

        if (transaction.getTransactionValue() != null && transaction.getTransactionValue().compareTo(BigDecimal.valueOf(1000)) > 0) {
            transaction.setStatus("rejected");
        } else {
            kafkaTemplate.send("transaction_topic", transaction);
        }

        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction getTransaction(UUID transactionExternalId) {
        return transactionRepository.findById(transactionExternalId)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction with ID " + transactionExternalId + " not found"));
    }
}
