package br.com.tgid.transaction.model.dto;

import br.com.tgid.transaction.model.Customer;
import br.com.tgid.transaction.model.Enterprise;
import br.com.tgid.transaction.model.enums.TransactionTypeEnum;

import java.math.BigDecimal;

public record TransactionRequest(Customer customer, BigDecimal value, Enterprise enterprise, TransactionTypeEnum typeEnum) {
}
