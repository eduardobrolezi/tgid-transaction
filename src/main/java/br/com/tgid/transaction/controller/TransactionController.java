package br.com.tgid.transaction.controller;

import br.com.tgid.transaction.model.dto.TransactionRequest;
import br.com.tgid.transaction.model.dto.TransactionResponse;
import br.com.tgid.transaction.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping(value = "/transaction")
public class TransactionController {

    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionResponse> create(TransactionRequest transactionRequest){
       TransactionResponse transactionResponse = transactionService.create(transactionRequest);
        return ResponseEntity.created(URI.create("")).body(transactionResponse);
    }
}
