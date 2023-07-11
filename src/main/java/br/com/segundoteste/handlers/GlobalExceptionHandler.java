package br.com.segundoteste.handlers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import br.com.segundoteste.exceptions.CandidatoException;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(CandidatoException.BadRequestException.class)
    @ResponseBody
    public ResponseEntity<String> handleBadRequestException(CandidatoException.BadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(CandidatoException.ConflictException.class)
    @ResponseBody
    public ResponseEntity<String> handleConflictException(CandidatoException.ConflictException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(CandidatoException.NotFoundException.class)
    @ResponseBody
    public ResponseEntity<String> handleNotFoundException(CandidatoException.NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}
