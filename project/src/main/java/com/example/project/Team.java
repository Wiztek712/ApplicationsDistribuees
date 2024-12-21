package com.example.project;

import java.util.List;

public class Team {

    private Long id;
    private String name;
    private String stadium;
    private List<String> colours;

    Team(){
    }

    Team(Long id, String name, String stadium, List<String> colours){
        this.name = name;
        this.stadium = stadium;
        this.colours = colours;
    }

    public Object getName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getName'");
    }

    public Object getRole() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRole'");
    }

    public void setName(Object name2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setName'");
    }

    public void setRole(Object role) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setRole'");
    }

    public Long getId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getId'");
    }
}
