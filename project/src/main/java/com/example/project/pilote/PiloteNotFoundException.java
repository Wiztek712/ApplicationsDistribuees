package com.example.project.pilote;

public class PiloteNotFoundException extends RuntimeException {    
    
    PiloteNotFoundException(Long id) {
	    super("Could not find pilote " + id);
	}
}
