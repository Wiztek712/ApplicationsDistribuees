package com.example.project.team;

public class DuplicateTeamNameException extends RuntimeException{

    public DuplicateTeamNameException(String name){
        super("A team with the name '" + name + "' already exists.");
    }
}
