package com.api.desafio_gazin.exception;

import static java.lang.String.format;


import com.api.desafio_gazin.exception.exceptions.BusinessException;
import com.api.desafio_gazin.exception.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler({ BusinessException.class })
    @ResponseBody
    public ResponseEntity<String> exceptionHandler(BusinessException e) {
        log.error(format("Exceção de negócio: { %s }", e.getMessage()), e);
        return ResponseEntity
                .badRequest()
                .body(e.getMessage());
    }

    @ExceptionHandler({ ResourceNotFoundException.class })
    @ResponseBody
    public ResponseEntity<String> exceptionHandler(ResourceNotFoundException e) {
        log.error(format("Resource não encontrado: { %s }", e.getMessage()), e);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    @ExceptionHandler({ Exception.class })
    @ResponseBody
    public ResponseEntity<String> exceptionHandler(Exception e) {
        log.error(" ===  Erro desconhecido capturado pelo handler === ");
        log.error(format("Erro desconhecido: { %s }", e.getMessage()), e);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }
}
