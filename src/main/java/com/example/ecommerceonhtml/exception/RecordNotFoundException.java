package com.example.ecommerceonhtml.exception;

public class RecordNotFoundException extends RuntimeException{
    public RecordNotFoundException(String massage){
        super(massage);
    }
}
