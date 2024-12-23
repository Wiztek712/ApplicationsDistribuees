package com.example.project.pilote;

import com.example.project.team.Team;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "pilote", uniqueConstraints = @UniqueConstraint(columnNames = "number"))
public class Pilote {
    
    @Id
    @GeneratedValue
    Long id;

    String name;
    int number;

    @ManyToOne
    @JoinColumn(name = "team_id")
    @JsonBackReference
    Team team;
    
    public Pilote(){}

    public Pilote(String name, int number, Team team) {
        this.name = name;
        this.number = number;
        this.team = team;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public Team getTeam() {
        return team;
    }
    public void setTeam(Team team) {
        this.team = team;
    }

    
}
