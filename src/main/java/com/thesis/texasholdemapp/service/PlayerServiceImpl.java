package com.thesis.texasholdemapp.service;

import com.thesis.texasholdemapp.model.Player;
import com.thesis.texasholdemapp.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public Player createPlayer(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public Player getPlayer(Long id) {
        return playerRepository.getOne(id);
    }

    @Override
    public void deletePlayer(Player player) {
        playerRepository.delete(player);
    }
}
