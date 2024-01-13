package br.com.tgid.transaction.model.dto;

import br.com.tgid.transaction.model.Customer;
import br.com.tgid.transaction.model.Enterprise;
import br.com.tgid.transaction.model.enums.TransactionTypeEnum;
import jakarta.validation.Valid;

import java.math.BigDecimal;

public record TransactionRequest(Long customerid , BigDecimal value, Long enterpriseid, TransactionTypeEnum typeEnum) {
}
