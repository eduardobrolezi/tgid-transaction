package br.com.tgid.transaction.config;

import br.com.tgid.transaction.exception.CustomerNotFoundException;
import br.com.tgid.transaction.exception.EnterpriseNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class ExceptionHandlerConfig{

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ExceptionHandlerDto> handle(MethodArgumentNotValidException exception) {

        List<ExceptionHandlerDto> dto = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e ->{
            String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ExceptionHandlerDto error = new ExceptionHandlerDto(e.getField(),message);
            dto.add(error);
        });

        return dto;
    }

    @ResponseStatus(value = NOT_FOUND)
    @ExceptionHandler( CustomerNotFoundException.class)
    public @ResponseBody
    ResponseDto handleException(CustomerNotFoundException e) {
        return new ResponseDto(e.getMessage());
    }

    @ResponseStatus(value = NOT_FOUND)
    @ExceptionHandler(EnterpriseNotFoundException.class)
    public @ResponseBody
    ResponseDto handleException(EnterpriseNotFoundException e) {
        return new ResponseDto(e.getMessage());
    }
}