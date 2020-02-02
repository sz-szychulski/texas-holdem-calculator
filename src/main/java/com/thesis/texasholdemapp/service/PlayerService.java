package com.thesis.texasholdemapp.service;

import com.thesis.texasholdemapp.model.Player;
import com.thesis.texasholdemapp.model.Simulation;

import java.util.List;

public interface PlayerService {
    Player createPlayer(Player player);
    Player getPlayer(Long id);
    List<Player> getAllPlayers();
    void deletePlayer(Player player);
}
