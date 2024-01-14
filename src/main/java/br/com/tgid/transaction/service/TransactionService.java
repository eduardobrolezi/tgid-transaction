package br.com.tgid.transaction.service;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class TransactionService {

    private static Logger logger = LoggerFactory.getLogger(TransactionService.class);
    private TransactionRepository transactionRepository;

    private CustomerRepository customerRepository;

    private EnterpriseRepository enterpriseRepository;

    private EnterpriseService enterpriseService;

    private EmailService emailService;

    public TransactionService(TransactionRepository transactionRepository, CustomerRepository customerRepository, EnterpriseRepository enterpriseRepository, EnterpriseService enterpriseService, EmailService emailService) {
        this.transactionRepository = transactionRepository;
        this.customerRepository = customerRepository;
        this.enterpriseRepository = enterpriseRepository;
        this.enterpriseService = enterpriseService;
        this.emailService = emailService;
    }

    public TransactionResponse create(TransactionRequest transactionRequest) throws CustomerNotFoundException, EnterpriseNotFoundException {

        Customer customer = customerRepository.findById(transactionRequest.customerid()).orElseThrow(CustomerNotFoundException::new);
        Enterprise enterprise = enterpriseRepository.findById(transactionRequest.enterpriseid()).orElseThrow(EnterpriseNotFoundException::new);

        enterpriseService.changeBalance(enterprise, transactionRequest.value(), transactionRequest.typeEnum());

        Transaction transaction = transactionRepository.save(Transaction.toModel(transactionRequest, customer, enterprise));

        postToWebhook(transaction);

        emailService.sendEmail(customer.getEmail(),transactionRequest.typeEnum(), transactionRequest.value());

        return transaction.toDto();
    }

    private static void postToWebhook(Transaction transaction) {
      try {String webhookUrl = "https://webhook.site/dc34dabc-1234-5678-abcd-0123456789ab";

          RestTemplate restTemplate = new RestTemplate();
          restTemplate.postForEntity(webhookUrl, transaction, String.class);

      }
      catch(Exception e){
          logger.error("Error to do webhook");
      }
    }


}
