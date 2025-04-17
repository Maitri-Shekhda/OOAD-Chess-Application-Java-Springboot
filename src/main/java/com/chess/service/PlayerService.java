package com.chess.service;

import com.chess.model.Player;
import com.chess.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    
    @Autowired
    private PlayerRepository playerRepository;
    
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }
    
    public Optional<Player> getPlayerById(Long id) {
        return playerRepository.findById(id);
    }
    
    public Optional<Player> getPlayerByUsername(String username) {
        return playerRepository.findByUsername(username);
    }
    
    public void updatePlayerStats(Player player, boolean won) {
        player.setGamesPlayed(player.getGamesPlayed() + 1);
        if (won) {
            player.setGamesWon(player.getGamesWon() + 1);
            player.setRating(player.getRating() + 10); // Simple rating adjustment
        } else {
            player.setRating(Math.max(1000, player.getRating() - 5)); // Prevent rating from going too low
        }
        playerRepository.save(player);
    }
    
    public void updatePlayer(Player player) {
        playerRepository.save(player);
    }
}