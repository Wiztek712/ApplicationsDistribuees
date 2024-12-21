package com.example.project;

public class TeamNotFoundException extends RuntimeException {
    
    
    TeamNotFoundException(Long id) {
	    super("Could not find employee " + id);
	}
}
