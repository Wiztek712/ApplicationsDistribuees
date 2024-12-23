package com.example.project.team;

public class TeamNotFoundException extends RuntimeException {
    
    public TeamNotFoundException(Long id) {
	    super("Could not find team " + id);
	}
}
