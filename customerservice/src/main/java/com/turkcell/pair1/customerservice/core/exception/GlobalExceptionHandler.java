package com.turkcell.pair1.customerservice.core.exception;

import com.turkcell.pair1.customerservice.core.exception.details.BusinessProblemDetails;
import com.turkcell.pair1.customerservice.core.exception.details.FeignProblemDetails;
import com.turkcell.pair1.customerservice.core.exception.details.ValidationProblemDetails;
import com.turkcell.pair1.customerservice.core.exception.types.BusinessException;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({BusinessException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BusinessProblemDetails handleBusinessException(BusinessException exception) {
        BusinessProblemDetails problemDetails = new BusinessProblemDetails();
        problemDetails.setDetail(exception.getMessage());
        return problemDetails;
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationProblemDetails handleValidationException(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        Map<String, String> errorDetails = new HashMap<>();
        for (FieldError error : fieldErrors) {
            errorDetails.put(error.getField(), error.getDefaultMessage());
        }

        ValidationProblemDetails problemDetails = new ValidationProblemDetails();
        problemDetails.setDetail(errorDetails);

        return problemDetails;

    }

    @ExceptionHandler({FeignException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public FeignProblemDetails handleFeignException(FeignException exception) {
        FeignProblemDetails problemDetails = new FeignProblemDetails();
        problemDetails.setDetail(exception.getMessage());
        return problemDetails;
    }

    /*@ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ProblemDetails handleException() {
        return new ProblemDetails("Unknown Error","Some error occurred.","https://turkcell.com/exceptions/unknown");
    }*/
}
