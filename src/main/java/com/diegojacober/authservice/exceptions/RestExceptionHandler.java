package com.diegojacober.authservice.exceptions;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.diegojacober.authservice.errors.ErrorObject;
import com.diegojacober.authservice.errors.ErrorResponse;

@ControllerAdvice
public class RestExceptionHandler{

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodNotValidException(MethodArgumentNotValidException ex) {
        
        System.out.println(ex.getBindingResult()
                .getFieldErrors().stream()
                .map(fieldError -> "Campo: " + fieldError.getField() + ", Mensagem de Erro: " + fieldError.getDefaultMessage())
                .collect(Collectors.toList()));

        List<ErrorObject> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> new ErrorObject(fieldError.getDefaultMessage(), fieldError.getField()))
                .collect(Collectors.toList());

        ErrorResponse apiErrorMessage = new ErrorResponse("CAMPOS INVÁLIDOS", 400, "BAD REQUEST", errors);

        System.out.println(apiErrorMessage);

        return ResponseEntity.badRequest().body(apiErrorMessage);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, WebRequest request) {
        String errorMessage = "Valor de permissão inválido";

        List<ErrorObject> errors = Arrays.asList(new ErrorObject(errorMessage, "role"));
        // Você pode personalizar a mensagem de erro conforme desejado
        ErrorResponse apiErrorMessage = new ErrorResponse("CAMPOS INVÁLIDOS", 400, "BAD REQUEST", errors);

        return ResponseEntity.badRequest().body(apiErrorMessage);
    }
}