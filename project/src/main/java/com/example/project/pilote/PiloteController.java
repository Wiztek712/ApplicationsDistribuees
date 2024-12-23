package com.example.project.pilote;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.project.team.Team;
import com.example.project.team.TeamNotFoundException;
import com.example.project.team.TeamRepository;


@RestController
public class PiloteController {
    
    @Autowired
    private PiloteRepository repository;

    @Autowired
    private TeamRepository teamRepository;

    public PiloteController(){}

    // Retrieve all pilotes
    @GetMapping("/pilotes")
    List<Pilote> all(){
        return repository.findAll();
    }

    // Create a new pilote, with or without team
    @PostMapping("/pilotes")
    Pilote newPilote(@RequestParam String name, @RequestParam int number, Long team_id) {
        Pilote newPilote = new Pilote();
        Team team = new Team();
        if (team_id!=null){
            team = teamRepository.findById(team_id).orElseThrow(() -> new TeamNotFoundException(team_id));
            newPilote.setTeam(team);
        }
        newPilote.setName(name);
        newPilote.setNumber(number);
        
        return repository.save(newPilote);
    }

    // Retrieve a particular pilote
    @GetMapping("/pilotes/{id}")
    Pilote one(@PathVariable Long id) {

        return repository.findById(id).orElseThrow(() -> new PiloteNotFoundException(id));
    }
    
    // Delete a particular pilote
    @DeleteMapping("/pilotes/{id}")
    void deletePilote(@PathVariable Long id) {
        repository.deleteById(id);
    }
    
    // Replace a pilote by another.
    // The function handles the case where the new pilote has the same number as another pilote
    // by canceling the operation. Otherwise, name and number are replaced and the new pilote
    // inherites the team and the id of the previous one.
    @PutMapping("/pilotes/{id}")
    Pilote replacePilote(@RequestParam String name, @RequestParam int number, @PathVariable Long id) {
        Pilote foundPilote = repository.findById(id).orElseThrow(() -> new PiloteNotFoundException(id));
        if (foundPilote == null)
            throw new PiloteNotFoundException(id);
        else {
            boolean piloteExists = repository.existsByNumber(number);
            if(piloteExists){
                throw new DuplicatePiloteNumberException(number);
            }
            else{
                foundPilote.setName(name);
                foundPilote.setNumber(number);
            }
            return repository.save(foundPilote);
        }
    }

}
