package br.com.tgid.transaction.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.PRECONDITION_FAILED)
public class BusinessException extends RuntimeException{
    public BusinessException(String message) {
        super(message);

    }


}
