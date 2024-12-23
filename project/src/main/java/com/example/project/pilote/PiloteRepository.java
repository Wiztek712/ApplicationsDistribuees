package com.example.project.pilote;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PiloteRepository extends JpaRepository<Pilote,Long>{
    Pilote findByNumber(int number);
    boolean existsByNumber(int number);
}
