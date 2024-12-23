package com.example.project;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.project.pilote.PiloteRepository;
import com.example.project.pilote.Pilote;
import com.example.project.team.Team;
import com.example.project.team.TeamRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class LoadDatabase {
    private Logger log;
	
	public LoadDatabase() {
		this.log = LoggerFactory.getLogger(LoadDatabase.class);
	}

	@Bean
	CommandLineRunner initDatabase(TeamRepository repository, PiloteRepository piloteRepository) {
		return args -> {
			
            if (repository.existsByName("Ferrari")){
                log.info("Team Ferrari already exists. Skipping creation.");
                return;
            }
            else{
                Team ferrari = new Team();
                ferrari.setName("Ferrari");
                repository.save(ferrari);
    
                List<String> ferrariPiloteNames = List.of("Leclerc", "Hamilton");
                List<Integer> ferrariPiloteNumbers = List.of(16, 44);
    
                List<Pilote> ferrariPilotes = IntStream.range(0, ferrariPiloteNames.size())
                    .mapToObj(i -> {
                        Pilote pilote = new Pilote();
                        pilote.setName(ferrariPiloteNames.get(i));
                        pilote.setNumber(ferrariPiloteNumbers.get(i));
                        pilote.setTeam(ferrari);
                        return pilote;
                    })
                    .collect(Collectors.toList());
    
                ferrari.setPilotes(ferrariPilotes);
                ferrari.setHeadQuarters("Maranello");
                this.log.info("Preloading " + repository.save(ferrari));
            }
            
			if (repository.existsByName("Mercedes")){
                log.info("Team Mercedes already exists. Skipping creation.");
                return;
            }
            else{
                Team mercedes = new Team();
                mercedes.setName("Mercedes");
                repository.save(mercedes);

                List<String> mercedesPiloteNames = List.of("Russel", "Antonelli");
                List<Integer> mercedesPiloteNumbers = List.of(63, 7);

                List<Pilote> mercedesPilotes = IntStream.range(0, mercedesPiloteNames.size())
                    .mapToObj(i -> {
                        Pilote pilote = new Pilote();
                        pilote.setName(mercedesPiloteNames.get(i));
                        pilote.setNumber(mercedesPiloteNumbers.get(i));
                        pilote.setTeam(mercedes);
                        return pilote;
                    })
                    .collect(Collectors.toList());

                mercedes.setPilotes(mercedesPilotes);
                mercedes.setHeadQuarters("Brakley");
                this.log.info("Preloading " + repository.save(mercedes));
            }

            // Team redBull = new Team();
            // redBull.setName("Red Bull");
            // redBull.setHeadQuarters("Milton Keynes");
            // this.log.info("Preloading " + repository.save(redBull));
		};
	}
}

