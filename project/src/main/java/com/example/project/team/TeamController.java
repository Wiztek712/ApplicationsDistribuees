package com.example.project.team;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {
    
    @Autowired
    private TeamRepository repository;
    
    public TeamController() {
    }
    
    @GetMapping("/teams")
    List<Team> all() {
        return repository.findAll();
    }
    
    @PostMapping("/teams")
    Team newTeam(@RequestBody Team newTeam) {
        
        return repository.save(newTeam);
    }
    
    @GetMapping("/teams/{id}")
    Team one(@PathVariable Long id) {

        return repository.findById(id).orElseThrow(() -> new TeamNotFoundException(id));
    }
    
    @DeleteMapping("/teams/{id}")
    void deleteTeam(@PathVariable Long id) {
        repository.deleteById(id);
    }
    
    @PutMapping("/teams/{id}")
    Team replaceTeam(@RequestBody Team newTeam, @PathVariable Long id) {

        Team foundTeam = repository.findById(id).orElseThrow(() -> new TeamNotFoundException(id));
        
        if (foundTeam == null)
            throw new TeamNotFoundException(id);
        else {
            foundTeam.setName(newTeam.getName());
            foundTeam.setHeadQuarters(newTeam.getHeadQuarters());
            foundTeam.setPilotes(newTeam.getPilotes());
            
            return repository.save(foundTeam);
        }
    }
}
