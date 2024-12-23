package com.example.project.pilote;

public class PiloteNotFoundException extends RuntimeException {    
    
    public PiloteNotFoundException(Long id) {
	    super("Could not find pilote " + id);
	}
}
