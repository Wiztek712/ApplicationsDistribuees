package com.example.project.pilote;

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
public class PiloteController {
    
    @Autowired
    private PiloteRepository repository;

    public PiloteController(){}

    @GetMapping("/pilotes")
    List<Pilote> all(){
        return repository.findAll();
    }

    @PostMapping("/pilotes")
    Pilote newPilote(@RequestBody Pilote newPilote){
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
