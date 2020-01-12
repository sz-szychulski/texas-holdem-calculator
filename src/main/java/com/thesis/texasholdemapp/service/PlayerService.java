package com.thesis.texasholdemapp.service;

import com.thesis.texasholdemapp.model.Player;

public interface PlayerService {
    Player createPlayer(Player player);
    Player getPlayer(Long id);
    void deletePlayer(Player player);
}
