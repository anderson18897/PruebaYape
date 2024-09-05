package com.yape.transaction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {
    @NotNull(message = "Account external ID debit is required")
    private String accountExternalIdDebit;

    @NotNull(message = "Account external ID credit is required")
    private String accountExternalIdCredit;

    private int transferTypeId;

    @NotNull(message = "Transaction value is required")
    private BigDecimal transactionValue;
}
