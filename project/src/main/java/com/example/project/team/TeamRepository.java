package com.example.project.team;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long>{
    Team findByName(String name);
    boolean existsByName(String name);
}
