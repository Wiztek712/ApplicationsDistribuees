package com.example.project.pilote;

public class DuplicatePiloteNumberException extends RuntimeException{

    public DuplicatePiloteNumberException(int number){
        super("A pilote with the number '" + number + "'already exists.");
    }
}
