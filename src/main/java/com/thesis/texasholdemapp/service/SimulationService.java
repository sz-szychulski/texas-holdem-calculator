package com.thesis.texasholdemapp.service;

import com.thesis.texasholdemapp.model.Simulation;

import java.util.List;

public interface SimulationService {
    Simulation createSimulation(Simulation simulation);
    Simulation getSimulation(Long id);
    List<Simulation> getAllSimulations();
    void deleteSimulation(Simulation simulation);
}
