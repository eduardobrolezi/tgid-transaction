package br.com.tgid.transaction.controller;

import br.com.tgid.transaction.exception.BusinessException;
import br.com.tgid.transaction.exception.CustomerNotFoundException;
import br.com.tgid.transaction.exception.EnterpriseNotFoundException;
import br.com.tgid.transaction.model.dto.TransactionRequest;
import br.com.tgid.transaction.model.dto.TransactionResponse;
import br.com.tgid.transaction.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping(value = "/transaction")
public class TransactionController {

    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<TransactionResponse> create(@Valid @RequestBody TransactionRequest transactionRequest) throws CustomerNotFoundException, EnterpriseNotFoundException {
       TransactionResponse transactionResponse = transactionService.create(transactionRequest);
        return ResponseEntity.created(URI.create("")).body(transactionResponse);
    }

}
