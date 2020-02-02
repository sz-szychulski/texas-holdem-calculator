package com.thesis.texasholdemapp.model;

import javax.persistence.*;

@Entity
@Table(name = "player")
public class Player {

    public Player() {
    }

    public Player(String cards, Integer simulationsWon, Integer simulationsSplitted, Simulation simulation) {
        this.cards = cards;
        this.simulationsWon = simulationsWon;
        this.simulationsSplitted = simulationsSplitted;
        this.simulation = simulation;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "player_cards")
    private String cards;

    @Column(name = "simulations_won")
    private Integer simulationsWon;

    @Column(name = "simulations_splitted")
    private Integer simulationsSplitted;

    @ManyToOne
    @JoinColumn(name = "simulation_id", nullable = false)
    private Simulation simulation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCards() {
        return cards;
    }

    public void setCards(String cards) {
        this.cards = cards;
    }

    public Integer getSimulationsWon() {
        return simulationsWon;
    }

    public void setSimulationsWon(Integer simulationsWon) {
        this.simulationsWon = simulationsWon;
    }

    public Integer getSimulationsSplitted() {
        return simulationsSplitted;
    }

    public void setSimulationsSplitted(Integer simulationsSplitted) {
        this.simulationsSplitted = simulationsSplitted;
    }

    public Simulation getSimulation() {
        return simulation;
    }

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }
}
