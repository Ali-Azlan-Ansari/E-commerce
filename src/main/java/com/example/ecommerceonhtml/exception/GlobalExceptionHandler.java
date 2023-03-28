package com.example.ecommerceonhtml.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = DuplicateRecordException.class)
    public ResponseEntity<ErrorMassage<String>> duplicateRecordException(DuplicateRecordException ex){
        ErrorMassage errorMassage=ErrorMassage.builder()
                                  .body(ex.getMessage())
                                  .dateTime(LocalDateTime.now())
                                  .build();
        return new ResponseEntity(errorMassage,HttpStatus.CONFLICT);
    }
    @ExceptionHandler(value = RecordNotFoundException.class)
    public ResponseEntity<ErrorMassage<String>> recordNotFoundException(RecordNotFoundException ex){
        ErrorMassage errorMassage=ErrorMassage.builder()
                .body(ex.getMessage())
                .dateTime(LocalDateTime.now())
                .build();
        return new ResponseEntity(errorMassage,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = InvalidDataException.class)
    public ResponseEntity<ErrorMassage<String>> invalidDataException(InvalidDataException ex){
        ErrorMassage errorMassage=ErrorMassage.builder()
                .body(ex.getMessage())
                .dateTime(LocalDateTime.now())
                .build();
        return new ResponseEntity(errorMassage,HttpStatus.BAD_REQUEST);
    }
}
