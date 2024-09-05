package com.yape.transaction.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "account_external_id_debit")
    private UUID accountExternalIdDebit;

    @Column(name = "account_external_id_credit")
    private UUID accountExternalIdCredit;

    @Column(name = "transfer_type_id")
    private int transferTypeId;

    @Column(name = "transaction_value", precision = 10, scale = 2)
    private BigDecimal transactionValue;

    @Column(name = "status")
    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
