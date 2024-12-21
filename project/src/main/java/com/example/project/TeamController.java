package com.example.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

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

        Team result = repository.findById(id);
        
        if (result == null)
            throw new TeamNotFoundException(id);
        else
            return result;
    }
    
    @DeleteMapping("/teams/{id}")
    void deleteTeam(@PathVariable Long id) {
        repository.deleteById(id);
    }
    
    @PutMapping("/teams/{id}")
    Team replaceTeam(@RequestBody Team newTeam, @PathVariable Long id) {

        Team foundTeam = repository.findById(id);
        
        if (foundTeam == null)
            throw new TeamNotFoundException(id);
        else {
            foundTeam.setName(newTeam.getName());
            foundTeam.setRole(newTeam.getRole());
            
            return repository.save(foundTeam);
        }
    }
}
