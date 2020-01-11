package com.thesis.texasholdemapp.repository;

import com.thesis.texasholdemapp.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
