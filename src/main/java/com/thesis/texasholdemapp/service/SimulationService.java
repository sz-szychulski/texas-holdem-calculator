package com.thesis.texasholdemapp.service;

import com.thesis.texasholdemapp.model.Simulation;

public interface SimulationService {
    Simulation createSimulation(Simulation simulation);
    Simulation getSimulation(Long id);
    void deleteSimulation(Simulation simulation);
}
