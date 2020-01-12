package com.thesis.texasholdemapp.service;

import com.thesis.texasholdemapp.model.Simulation;
import com.thesis.texasholdemapp.repository.SimulationRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class SimulationServiceImpl implements SimulationService {

    @Autowired
    private SimulationRepository simulationRepository;

    @Override
    public Simulation createSimulation(Simulation simulation) {
        return simulationRepository.save(simulation);
    }

    @Override
    public Simulation getSimulation(Long id) {
        return simulationRepository.getOne(id);
    }

    @Override
    public void deleteSimulation(Simulation simulation) {
        simulationRepository.delete(simulation);
    }
}
