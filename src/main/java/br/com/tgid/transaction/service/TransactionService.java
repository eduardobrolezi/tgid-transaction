package br.com.tgid.transaction.service;

import br.com.tgid.transaction.model.Transaction;
import br.com.tgid.transaction.model.dto.TransactionRequest;
import br.com.tgid.transaction.model.dto.TransactionResponse;
import br.com.tgid.transaction.repository.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {


    private TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public TransactionResponse create(TransactionRequest transactionRequest){

        return transactionRepository.save(Transaction.toModel(transactionRequest)).toDto();
    }




}
