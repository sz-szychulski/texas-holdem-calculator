package com.thesis.texasholdemapp.service;

import com.thesis.texasholdemapp.model.Simulation;
import com.thesis.texasholdemapp.repository.SimulationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
    public List<Simulation> getAllSimulations() {
        return simulationRepository.findAll();
    }

    @Override
    public void deleteSimulation(Simulation simulation) {
        simulationRepository.delete(simulation);
    }
}
