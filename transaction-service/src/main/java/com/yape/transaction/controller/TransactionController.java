package com.yape.transaction.controller;

import com.yape.transaction.model.Transaction;
import com.yape.transaction.dto.TransactionRequest;
import com.yape.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@Valid @RequestBody TransactionRequest request) {
        Transaction transaction = transactionService.createTransaction(request);
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }

    @GetMapping("/{transactionExternalId}")
    public ResponseEntity<Transaction> getTransaction(@PathVariable UUID transactionExternalId) {
        Transaction transaction = transactionService.getTransaction(transactionExternalId);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }
}
