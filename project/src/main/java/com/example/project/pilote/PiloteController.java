package com.example.project.pilote;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("/pilotes")
    List<Pilote> all(){
        return repository.findAll();
    }

    @PostMapping("/pilotes")
    Pilote newPilote(@RequestParam String name, @RequestParam int number, @RequestParam Long team_id) {
        Pilote newPilote = new Pilote();
        Team team = teamRepository.findById(team_id).orElseThrow(() -> new TeamNotFoundException(team_id));
        newPilote.setName(name);
        newPilote.setNumber(number);
        newPilote.setTeam(team);
        return repository.save(newPilote);
    }

    @GetMapping("/pilotes/{id}")
    Pilote one(@PathVariable Long id) {

        return repository.findById(id).orElseThrow(() -> new PiloteNotFoundException(id));
    }
    
    @DeleteMapping("/pilotes/{id}")
    void deletePilote(@PathVariable Long id) {
        repository.deleteById(id);
    }
    
    @PutMapping("/pilotes/{id}")
    Pilote replacePilote(@RequestBody Pilote newPilote, @PathVariable Long id) {

        Pilote foundPilote = repository.findById(id).orElseThrow(() -> new PiloteNotFoundException(id));
        
        if (foundPilote == null)
            throw new PiloteNotFoundException(id);
        else {
            foundPilote.setName(newPilote.getName());
            foundPilote.setNumber(newPilote.getNumber());
            foundPilote.setTeam(newPilote.getTeam());
            
            return repository.save(foundPilote);
        }
    }

}
