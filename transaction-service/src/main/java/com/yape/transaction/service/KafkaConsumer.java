package com.yape.transaction.service;

import com.yape.transaction.model.Transaction;
import com.yape.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @Autowired
    private TransactionRepository transactionRepository;

    @KafkaListener(topics = "antifraud_response_topic", groupId = "group_id")
    public void listenTransactionStatus(Transaction transaction) {
        Transaction existingTransaction = transactionRepository.findById(transaction.getId())
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        existingTransaction.setStatus(transaction.getStatus());
        transactionRepository.save(existingTransaction);
    }
}
