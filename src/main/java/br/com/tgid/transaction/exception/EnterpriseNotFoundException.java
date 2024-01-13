package br.com.tgid.transaction.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EnterpriseNotFoundException extends Exception {

    public EnterpriseNotFoundException() {
        super("Enterprise not found");
    }

}
