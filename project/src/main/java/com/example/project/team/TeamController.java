package com.example.project.team;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.pilote.Pilote;
import com.example.project.pilote.PiloteNotFoundException;
import com.example.project.pilote.PiloteRepository;

@RestController
public class TeamController {
    
    @Autowired
    private TeamRepository repository;

    @Autowired
    private PiloteRepository piloteRepository;
    
    public TeamController() {
    }
    
    @GetMapping("/teams")
    List<Team> all() {
        return repository.findAll();
    }
    
    @PostMapping("/teams")
    Team newTeam(@RequestParam String name, @RequestParam String headQuarters) {
        Team newTeam = new Team();
        newTeam.setName(name);
        newTeam.setHeadQuarters(headQuarters);
        return repository.save(newTeam);
    }
    
    @GetMapping("/teams/{id}")
    Team one(@PathVariable Long id) {

        return repository.findById(id).orElseThrow(() -> new TeamNotFoundException(id));
    }
    
    @PostMapping("/teams/{id}")
    Team addPiloteToTeam(@PathVariable Long id, @RequestParam Long pilote_id) {
        Pilote pilote = piloteRepository.findById(pilote_id).orElseThrow(() -> new PiloteNotFoundException(pilote_id));
        Team team = repository.findById(id).orElseThrow(() -> new TeamNotFoundException(id));
        team.addPilote(pilote);
        return repository.save(team);
    }

    @DeleteMapping("/teams/{id}")
    void deleteTeam(@PathVariable Long id) {
        repository.deleteById(id);
    }
    
    @PutMapping("/teams/{id}")
    Team replaceTeam(@RequestParam String name, @RequestParam String headQuarters, @PathVariable Long id) {
        Team foundTeam = repository.findById(id).orElseThrow(() -> new TeamNotFoundException(id));
        if (foundTeam == null)
            throw new TeamNotFoundException(id);
        else {
            boolean teamExists = repository.existsByName(name);
            if (teamExists) {
                throw new DuplicateTeamNameException(name);
            }
            else{
                foundTeam.setName(name);
                foundTeam.setHeadQuarters(headQuarters);
            }            
            return repository.save(foundTeam);
        }
    }
}