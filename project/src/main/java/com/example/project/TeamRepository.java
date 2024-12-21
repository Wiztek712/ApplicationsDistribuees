package com.example.project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeamRepository {

    private Map<Long, Team> teams;
	
	public TeamRepository() {
		this.teams = new HashMap<Long, Team>();
	}

    public List<Team> findById(Long id) {
        return this.teams.values().stream().toList();    
    }

    public Team save(Team newTeam) {
        this.teams.put(newTeam.getId(), newTeam);
		
		return newTeam;    
    }

    public List<Team> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

}
