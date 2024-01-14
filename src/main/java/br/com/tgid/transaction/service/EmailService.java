package br.com.tgid.transaction.service;

import br.com.tgid.transaction.model.enums.TransactionTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class EmailService {

    private static Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String to, TransactionTypeEnum typeEnum, BigDecimal value) {


        String contactInformation = "tgid@example.com";
        String companyName = "Transform and grow in digital";

        String emailTemplate = """
                Greetings,
                                        
                We are writing to confirm that a recent transaction has been processed successfully. Here are the transaction details:
                                        
                Transaction Type: %s
                Amount: %s
                                        
                If you have any questions or concerns regarding this transaction, please feel free to contact our customer support team at %s.
                                        
                Thank you for choosing %s!
                                        
                Best regards,
                %s
                """;

        String formattedEmail = String.format(emailTemplate,
                typeEnum, value,
                contactInformation, companyName, companyName);


        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Transaction Confirmation");
        message.setText(formattedEmail);
        try {
            javaMailSender.send(message);
        }
        catch(Exception e) {
            logger.error("Error to send email");
        }


    }
}