package com.thesis.texasholdemapp.repository;

import com.thesis.texasholdemapp.model.Simulation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SimulationRepository extends JpaRepository<Simulation, Long> {
}
