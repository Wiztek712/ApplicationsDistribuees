package com.example.project.team;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;

@Entity
public class Team {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String headQuarters;
    private List<String> pilotes;

    Team(){
    }

    public Team(String name, String headQuarters, List<String> pilotes){
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

    public List<String> getPilotes() {
        return pilotes;
    }

    public void setPilotes(List<String> pilotes) {
        this.pilotes = pilotes;
    }
}
