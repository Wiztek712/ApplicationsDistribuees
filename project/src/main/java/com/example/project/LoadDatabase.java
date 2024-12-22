package com.example.project;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.project.team.Team;
import com.example.project.team.TeamRepository;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class LoadDatabase {
    private Logger log;
	
	public LoadDatabase() {
		this.log = LoggerFactory.getLogger(LoadDatabase.class);
	}

	@Bean
	CommandLineRunner initDatabase(TeamRepository repository) {
		return args -> {
			
            List<String> ferrariPilotes = new ArrayList<>();
            ferrariPilotes.add("Leclerc");
            ferrariPilotes.add("Hamilton");
			this.log.info("Preloading " + repository.save(new Team("Ferrari", "Maranello", ferrariPilotes)));
			
            List<String> mercedesPilotes = new ArrayList<>();
            mercedesPilotes.add("Russel");
            mercedesPilotes.add("Antonelli");
			this.log.info("Preloading " + repository.save(new Team("Mercedes", "Brakley", mercedesPilotes)));
		};
	}
}

