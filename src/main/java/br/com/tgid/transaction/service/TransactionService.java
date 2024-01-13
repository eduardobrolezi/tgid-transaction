package br.com.tgid.transaction.service;

import br.com.tgid.transaction.exception.BusinessException;
import br.com.tgid.transaction.exception.CustomerNotFoundException;
import br.com.tgid.transaction.exception.EnterpriseNotFoundException;
import br.com.tgid.transaction.model.Customer;
import br.com.tgid.transaction.model.Enterprise;
import br.com.tgid.transaction.model.Transaction;
import br.com.tgid.transaction.model.dto.TransactionRequest;
import br.com.tgid.transaction.model.dto.TransactionResponse;
import br.com.tgid.transaction.repository.CustomerRepository;
import br.com.tgid.transaction.repository.EnterpriseRepository;
import br.com.tgid.transaction.repository.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {


    private TransactionRepository transactionRepository;
    private CustomerRepository customerRepository;
    private EnterpriseRepository enterpriseRepository;

    public TransactionService(TransactionRepository transactionRepository, CustomerRepository customerRepository, EnterpriseRepository enterpriseRepository) {
        this.transactionRepository = transactionRepository;
        this.customerRepository = customerRepository;
        this.enterpriseRepository = enterpriseRepository;
    }

    public TransactionResponse create(TransactionRequest transactionRequest) throws CustomerNotFoundException, EnterpriseNotFoundException {

        Customer customer = customerRepository.findById(transactionRequest.customerid()).orElseThrow(CustomerNotFoundException::new);
        Enterprise enterprise = enterpriseRepository.findById(transactionRequest.enterpriseid()).orElseThrow(EnterpriseNotFoundException::new);

        return transactionRepository.save(Transaction.toModel(transactionRequest, customer, enterprise)).toDto();
    }




}
