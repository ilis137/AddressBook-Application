package com.example.addressbookbackend.Exceptions;

public class PersonRecordNotFoundException extends RuntimeException{
    public PersonRecordNotFoundException(String message){
        super(message);
    }
}
