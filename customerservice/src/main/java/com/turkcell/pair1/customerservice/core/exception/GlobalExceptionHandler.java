package com.turkcell.pair1.customerservice.core.exception;

import com.turkcell.pair1.customerservice.core.exception.types.BusinessException;
import com.turkcell.pair1.customerservice.core.exception.types.DuplicateEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({BusinessException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleBusinessException(BusinessException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler({DuplicateEntityException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, Object> handleBusinessException(DuplicateEntityException exception) {
        Map<String, Object> response = new HashMap<>();
        if (!exception.getErrors().isEmpty()) {
            response.putAll(exception.getErrors());
        } else {
            response.put("error", exception.getMessage());
        }
        return response;
    }
}
