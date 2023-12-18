package com.vismee.springrestcrud.globalexceptionhandler;

import com.vismee.springrestcrud.customexception.EmployeeNotFoundException;
import com.vismee.springrestcrud.exceptionentity.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(EmployeeNotFoundException ex)
    {
        ErrorResponse er = new ErrorResponse();
        er.setStatus(HttpStatus.NOT_FOUND.value());
        er.setMessage(ex.getMessage());
        er.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(er,HttpStatus.NOT_FOUND);

    }
}
