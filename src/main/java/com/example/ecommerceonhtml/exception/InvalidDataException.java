package com.example.ecommerceonhtml.exception;

public class InvalidDataException extends RuntimeException{
    public InvalidDataException(String massage){
        super(massage);
    }
}
