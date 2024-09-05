CREATE TABLE transactions (
  id UUID PRIMARY KEY,
  account_external_id_debit UUID,
  account_external_id_credit UUID,
  transfer_type_id INT,
  transaction_value DECIMAL(10, 2),
  status VARCHAR(255),
  created_at TIMESTAMP
);

