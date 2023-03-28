package com.example.ecommerceonhtml.exception;

public class DuplicateRecordException extends RuntimeException{
    public DuplicateRecordException(String massage){
        super(massage);
    }
}
