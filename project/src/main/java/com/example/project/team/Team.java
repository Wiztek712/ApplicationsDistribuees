package com.example.project.team;

import java.util.List;

import com.example.project.pilote.Pilote;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;

@Entity
public class Team {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String headQuarters;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Pilote> pilotes;

    public Team(){}

    public Team(String name, String headQuarters, List<Pilote> pilotes){
        this.name = name;
        this.headQuarters = headQuarters;
        this.pilotes = pilotes;
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

    public String getHeadQuarters() {
        return headQuarters;
    }

    public void setHeadQuarters(String headQuarters) {
        this.headQuarters = headQuarters;
    }

    public List<Pilote> getPilotes() {
        return pilotes;
    }

    public void setPilotes(List<Pilote> pilotes) {
        this.pilotes = pilotes;
        for (Pilote pilote : pilotes) {
            pilote.setTeam(this);
        }
    }
}
