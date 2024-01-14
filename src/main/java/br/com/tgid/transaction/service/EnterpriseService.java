package br.com.tgid.transaction.service;

import br.com.tgid.transaction.model.Enterprise;
import br.com.tgid.transaction.model.Tax;
import br.com.tgid.transaction.model.enums.TransactionTypeEnum;
import br.com.tgid.transaction.repository.EnterpriseRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class EnterpriseService {

    private EnterpriseRepository enterpriseRepository;

    public EnterpriseService(EnterpriseRepository enterpriseRepository) {
        this.enterpriseRepository = enterpriseRepository;
    }

    public void changeBalance(Enterprise enterprise, BigDecimal value, TransactionTypeEnum typeEnum) {

        BigDecimal taxValue = getTaxValue(enterprise, typeEnum);

        BigDecimal amount;

        if (typeEnum == TransactionTypeEnum.DEPOSIT){
            amount = enterprise.getBalance().add(value.subtract(taxValue));
        }else {
            amount = enterprise.getBalance().subtract(value.add(taxValue));
        }
        enterprise.setBalance(amount);

        enterpriseRepository.save(enterprise);
    }

    private static BigDecimal getTaxValue(Enterprise enterprise, TransactionTypeEnum typeEnum) {
        Tax tax = enterprise.getTaxes()
                .stream()
                .filter(tax1 -> tax1.getTypeEnum() == typeEnum)
                .findFirst()
                .orElse(new Tax(BigDecimal.ZERO));

        return tax.getValue();
    }

}
