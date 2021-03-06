package com.thesis.texasholdemapp.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "simulation")
public class Simulation {

    public Simulation() {
    }

    public Simulation(String simulationName, String userName, String boardCards, Boolean isMonteCarlo, Long iterations, Date date) {
        this.simulationName = simulationName;
        this.userName = userName;
        this.boardCards = boardCards;
        this.isMonteCarlo = isMonteCarlo;
        this.iterations = iterations;
        this.date = date;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "simulation_id")
    private Long id;

    @Column(name = "simulation_name")
    private String simulationName;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "board_cards")
    private String boardCards;

    @Column(name = "monte_carlo")
    private Boolean isMonteCarlo;

    @Column(name = "iterations")
    private Long iterations;

    @Column(name = "date_time")
    private Date date;

    @OneToMany(mappedBy = "simulation")
    private Set<Player> players;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBoardCards() {
        return boardCards;
    }

    public void setBoardCards(String boardCards) {
        this.boardCards = boardCards;
    }

    public Boolean getMonteCarlo() {
        return isMonteCarlo;
    }

    public void setMonteCarlo(Boolean monteCarlo) {
        isMonteCarlo = monteCarlo;
    }

    public Long getIterations() {
        return iterations;
    }

    public void setIterations(Long iterations) {
        this.iterations = iterations;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    public String getSimulationName() {
        return simulationName;
    }

    public void setSimulationName(String simulationName) {
        this.simulationName = simulationName;
    }
}
